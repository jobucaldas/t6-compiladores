package br.dc.compiladores.linguagem.noita;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Tabela de símbolos de wands para interpretador

public class TabelaDeWands {

    class EntradaTabelaDeWands {
        String nome;
        boolean shuffle;
        int spellsCast, mana, regen, capacity;
        float delay, recharge, spread;
        List<String> spellsDaWand;

        private EntradaTabelaDeWands(String nome, boolean shuffle, int spellsCast, float delay, 
                                        float recharge, int mana, int regen, int capacity, 
                                        float spread, List<String> spellsDaWand) {
            
            this.nome = nome;
            this.shuffle = shuffle;
            this.spellsCast = spellsCast;
            this.delay = delay;
            this.recharge = recharge;
            this.mana = mana;
            this.regen = regen;
            this.capacity = capacity;
            this.spread = spread;
            this.spellsDaWand = spellsDaWand;
        }
    }

    private final Map<String, EntradaTabelaDeWands> tabela;

    public TabelaDeWands() {
        this.tabela = new HashMap<>();
    }

    public void adicionar(String nome, boolean shuffle, int spellsCast, float delay, 
                                        float recharge, int mana, int regen, int capacity, 
                                        float spread, List<String> spellsDaWand) {

        tabela.put(nome, new EntradaTabelaDeWands(nome,shuffle,spellsCast,delay,recharge,mana,regen,
                                                    capacity,spread,spellsDaWand));
    }

    public List<String> verificarSpellsEmWand(String nome){
        return tabela.get(nome).spellsDaWand;
    }
}
