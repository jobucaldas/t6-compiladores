# t6-compiladores

João Victor Bueno de Caldas - 769657

Marcus Vinicius Soares de Oliveira - 770026

Compilador de linguagem para se construir builds para o jogo [Noita](https://noitagame.com/).

O vídeo de apresentação da linguagem se encontra [aqui](https://drive.google.com/file/d/104JGyn-0Hfhs5hDq8ll0k0yX6SkTRoA2/view?usp=drive_link)

## Compilando o compilador

A extensão "Maven for Java" e o pacote "Extension Pack for Java" foram utilizadas na execução do compilador, utilizando Java 16.

Para compilar o programa deve-ser executar dentro do repositório:

```bash
mvn package
```

Para a execução do programa:

```bash
java -jar PATH-TO-REPO\target\linguagem-noita-1.0-SNAPSHOT-jar-with-dependencies.jar [ENTRADA]
```

Sendo a **[ENTRADA]** o endereço do programa a ser analisado. A saída é impressa no terminal.

Por exemplo, com o comando

```bash
java -jar PATH-TO-REPO\alguma-semantico\target\linguagem-noita-1.0-SNAPSHOT-jar-with-dependencies.jar CAMINHO-ESCOLHIDO\entrada\programa.txt
```

O compilador analisa a semântica do programa escrito no arquivo *programa.txt* localizado no *CAMINHO-ESCOLHIDO\entrada* e imprime o resultado da análise (erros semânticos, se existirem) no terminal.

## Gramatica

Todo programa deve ter spells e wands definidos, entre *spells* | *end-spells* e *wands* | *end-wands*.

### Comentários

Definidos como texto após a sequencia '###'.

### Spells

Spells são definidas entre *spell* **NOME-SPELL** e *end-spell* com seus atributos definidos internamente entre estes.

Os atributos obrigatórios para spell são:

```noita
type: [tipo]
mana: [num]
```

Os tipos para o atributo *type* são:

- projectile
- aura
- object

Os atributos opcionais são:

```noita
uses: [num]

damage: [num]

radius: [num]

spread: [num] DEG

speed: [num]

lifetime: [num] s

delay: [num] s

recharge: [num] s

crit: % [num]
```

### Wands

Wands são definidas entre *wand* **NOME-WAND** e *end-wand* com seus atributos definidos internamente entre estes.

Os atributos obrigatórios para wand são:

```noita
shuffle: [boolean]
spells/cast: [num]
delay: [num] s
recharge: [num] s
mana: [num]
regen: [num]
capacity: [num]
spread: [num] DEG

slots: [spells] end-slots
```

## Exemplo de código nessa linguagem

### Input 1

```noita
### Declaração de Spells
spells
  spell fireball
    type: projectile
    mana: 10
    damage: 500 ### opcional
    speed: 12   ### opcional
  end-spell
end-spells

### Declaração de Wands
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

### Output esperado

```noita
mageStaff:
        Damage per second: 1666.6666
        Free mana: 820
        Free slots: 2

        Equipped spells:
                fireball:
                        Type: projectile
                        Mana: 10
                        Uses: 999
                        Damage: 500
                        Radius: 1.0
                        Spread: 0.0 DEG
                        Speed: 12.0
                        Lifetime: 1.0 s
                        Delay: 0.0 s
                        Recharge: 0.0 s
                        Crit: 0.0%
```

## Problemas de compilação

### Input Erro

```noita
### Declaração de Spells
spells
  spell hadouken
    type: projectile
    mana: 500
    damage: 250 ### opcional
    speed: 6    ### opcional
  end-spell
end-spells

### Declaração de Wands
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

### Output com Erro

```noita
Erro na linha 24: Spell 'fireball' não declarado
```
