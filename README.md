# t6-compiladores

João Victor Bueno de Caldas - 769657

Marcus Vinicius Soares de Oliveira - 770026

Compilador de linguagem para se construir builds para o jogo [Noita](https://noitagame.com/).

## Compilando o compilador

A extensão "Maven for Java" e o pacote "Extension Pack for Java" foram utilizadas na execução do compilador, utilizando Java 16.

Para compilar o programa deve-ser executar dentro do repositório:

```bash
mvn package
```

Para a execução do programa:

```bash
java -jar PATH-TO-REPO\target\linguagem-noita-1.0-SNAPSHOT-jar-with-dependencies.jar [ENTRADA] [SAIDA]
```

Sendo a **[ENTRADA]** o endereço do programa a ser analisado e **[SAIDA]** o endereço do arquivo ao qual será impressa a saída.

Por exemplo, com o comando

```bash
java -jar PATH-TO-REPO\alguma-semantico\target\linguagem-noita-1.0-SNAPSHOT-jar-with-dependencies.jar CAMINHO-ESCOLHIDO\entrada\programa.txt CAMINHO-ESCOLHIDO\saida.txt
```

O compilador analisa a semântica do programa escrito no arquivo *programa.txt* localizado no *CAMINHO-ESCOLHIDO\entrada* e imprime o resultado da análise (erros semânticos, se existirem) no arquivo *saida.txt* localizado no *CAMINHO-ESCOLHIDO*.

## Exemplos

```noita
;spells
spells
  spell fireball
    type spellType[TipoSpell]
    mana 10
    damage 500 ;optional
    speed 12   ;optional
  end-spell
end-spells

;wands
wands
  wand mageStaff
    shuffle FALSE
    spells/cast 2
    delay 0.3 s
    recharge 1.2 s
    mana 830
    regen 10
    capacity 3
    spread 3.0 DEG
    
    slots
      fireball
    end-slots
  end-wand
end-wands
```

As tags opcionais para spell são:

```noita
uses limitedUses[int]

damage spellDamage[int]

radius spellRadius[int]

spread spellSpread[float] DEG

speed spellSpeed[int]

lifetime spellLifetime[float] s

delay spellDelay[float] s

recharge spellRechargeTime[float] s

crit %spellCritChance[int]
```
