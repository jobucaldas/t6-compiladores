grammar Noita;

NUM_INT
	:	('0'..'9')+
	;

NUM_REAL
	:	('0'..'9')+ ('.' ('0'..'9')+)?
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
tipo_obrigatorio: 'type:' to1=CADEIA
        | 'mana:' to2=NUM_INT;
tipo_extra: 'uses:' NUM_INT
        | 'damage:' NUM_INT
        | 'radius:' NUM_INT
        | 'spread:' NUM_REAL 'DEG'
        | 'speed:' NUM_INT
        | 'lifetime:' NUM_REAL 's'
        | 'delay:' NUM_REAL 's'
        | 'recharge:' NUM_REAL 's'
        | 'crit:' '%' NUM_INT;
definicaoWands: 'wands' (wand)* 'end-wands';
wand: 'wand' w=CADEIA (tipo_wand)* 'end-wand';
tipo_wand: 'shuffle:' tipo_bool
        | 'spells/cast:' NUM_INT
        | 'delay:' NUM_REAL 's'
        | 'recharge:' NUM_REAL 's'
        | 'mana:' NUM_INT
        | 'regen:' NUM_INT
        | 'capacity:' numslots=NUM_INT
        | 'spread:' NUM_REAL 'DEG'
        | 'slots:' nslots+=slot (',' nslots+=slot)* 'end-slots';
slot: CADEIA;
tipo_bool: 'Yes' | 'No';