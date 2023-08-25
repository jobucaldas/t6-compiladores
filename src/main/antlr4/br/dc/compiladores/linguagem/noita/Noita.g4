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
tipo_obrigatorio: 'type:' to1=CADEIA
        | 'mana:' to2=NUM;
tipo_extra: 'uses:' NUM
        | 'damage:' NUM
        | 'radius:' NUM
        | 'spread:' NUM 'DEG'
        | 'speed:' NUM
        | 'lifetime:' NUM 's'
        | 'delay:' NUM 's'
        | 'recharge:' NUM 's'
        | 'crit:' NUM '%';
definicaoWands: 'wands' (wand)* 'end-wands';
wand: 'wand' w=CADEIA (tipo_wand)* 'end-wand';
tipo_wand: 'shuffle:' tipo_bool
        | 'spells/cast:' NUM
        | 'delay:' NUM 's'
        | 'recharge:' NUM 's'
        | 'mana:' NUM
        | 'regen:' NUM
        | 'capacity:' numslots=NUM
        | 'spread:' NUM 'DEG'
        | 'slots:' nslots+=slot (',' nslots+=slot)* 'end-slots';
slot: CADEIA;
tipo_bool: 'Yes' | 'No';