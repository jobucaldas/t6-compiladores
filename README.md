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

## Gramatica

Todo programa deve ter spells e wands definidos, entre *spells* | *end-spells* e *wands* | *end-wands*.

### Comentários

Definidos ao se iniciar uma linha com ;

### Spells

Spells são definidas entre *spell* e *end_spell* com seus atributos definidos internamente entre estes.

Os atributos obrigatórios para spell são:

```noita
type: [tipo]
mana: [inteiro]
```

Os tipos para o atributo *type* são:

- projectile
- aura
- object

Os atributos opcionais são:

```noita
uses: [int]

damage: [int]

radius: [int]

spread: [float] DEG

speed: [int]

lifetime: [float] s

delay: [float] s

recharge: [float] s

crit: % [int]
```

## Exemplo de código nessa linguagem

```noita
;spells
spells
  spell fireball
    type: projectile
    mana: 10
    damage: 500 ;optional
    speed: 12   ;optional
  end-spell
end-spells

;wands
wands
  wand mageStaff
    shuffle: No
    spells/cast: 2
    delay: 0.3 s
    recharge: 1.2 s
    mana: 830
    regen: 10
    capacity: 3
    spread: 3.0 DEG
    
    slots:
      fireball
    end-slots
  end-wand
end-wands
```
