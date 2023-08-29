package br.dc.compiladores.linguagem.noita;

import java.util.HashMap;
import java.util.Map;

// Tabela de símbolos de spells para interpretador

public class TabelaDeSpells {
    
    class EntradaTabelaDeSpells {
        String nome, type;
        int mana, uses, damage;
        float radius, spread, speed, lifetime, delay, recharge, crit;

        private EntradaTabelaDeSpells(String nome, String type, int mana, int uses, int damage,
                                        float radius, float spread, float speed, float lifetime,
                                        float delay, float recharge, float crit) {
            
            this.nome = nome;
            this.type = type;
            this.mana = mana;
            this.uses = uses;
            this.damage = damage;
            this.radius = radius;
            this.spread = spread;
            this.speed = speed;
            this.lifetime = lifetime;
            this.delay = delay;
            this.recharge = recharge;
            this.crit = crit;
        }
    }

    private final Map<String, EntradaTabelaDeSpells> tabela;

    public TabelaDeSpells(){
        this.tabela = new HashMap<>();
    }

    public void adicionar(String nome, String type, int mana, int uses, int damage,
                                        float radius, float spread, float speed, float lifetime,
                                        float delay, float recharge, float crit){
            
        tabela.put(nome, new EntradaTabelaDeSpells(nome,type,mana,uses,damage,radius,spread,speed,
                                            lifetime, delay, recharge, crit));
    }
}
