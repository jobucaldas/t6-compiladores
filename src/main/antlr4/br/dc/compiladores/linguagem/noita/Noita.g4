grammar Noita;

NUM
        :       '-'?('0'..'9')+ ('.' ('0'..'9')+)?
        ;

CADEIA : ('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ;

COMENTARIO
	:	'###' ~('\n'|'\r')* {skip();}
	;

WS 	:	( ' ' |'\t' | '\r' | '\n') {skip();}
	;

programa: definicaoSpells definicaoWands;
definicaoSpells: 'spells' (spell)* 'end-spells';
spell: 'spell' s=CADEIA (tipos_spell)* 'end-spell';
tipos_spell: tipo_obrigatorio
        | tipo_extra;
tipo_obrigatorio: 'type:' stype=CADEIA
        | 'mana:' smana=NUM;
tipo_extra: 'uses:' suses=NUM
        | 'damage:' sdamage=NUM
        | 'radius:' sradius=NUM
        | 'spread:' sspread=NUM 'DEG'
        | 'speed:' sspeed=NUM
        | 'lifetime:' slifetime=NUM 's'
        | 'delay:' sdelay=NUM 's'
        | 'recharge:' srecharge=NUM 's'
        | 'crit:' scrit=NUM '%';
definicaoWands: 'wands' (wand)* 'end-wands';
wand: 'wand' w=CADEIA (tipo_wand)* 'end-wand';
tipo_wand: 'shuffle:' wshuffle=tipo_bool
        | 'spells/cast:' wcast=NUM
        | 'delay:' wdelay=NUM 's'
        | 'recharge:' wrecharge=NUM 's'
        | 'mana:' wmana=NUM
        | 'regen:' wregen=NUM
        | 'capacity:' numslots=NUM
        | 'spread:' wspread=NUM 'DEG'
        | 'slots:' nslots+=slot (',' nslots+=slot)* 'end-slots';
slot: CADEIA;
tipo_bool: 'Yes' | 'No';