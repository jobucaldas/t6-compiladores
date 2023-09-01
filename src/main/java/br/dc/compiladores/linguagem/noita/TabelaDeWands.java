package br.dc.compiladores.linguagem.noita;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Tabela de símbolos de wands para interpretador

public class TabelaDeWands {

    class EntradaTabelaDeWands {
        String nome;
        boolean shuffle;
        int cast, mana, regen, capacity;
        float delay, recharge, spread;
        List<String> spellsDaWand;

        private EntradaTabelaDeWands(String nome, boolean shuffle, int cast, float delay, 
                                        float recharge, int mana, int regen, int capacity, 
                                        float spread, List<String> spellsDaWand) {
            
            this.nome = nome;
            this.shuffle = shuffle;
            this.cast = cast;
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

    public void adicionar(String nome, boolean shuffle, int cast, float delay, 
                                        float recharge, int mana, int regen, int capacity, 
                                        float spread, List<String> spellsDaWand) {

        tabela.put(nome, new EntradaTabelaDeWands(nome,shuffle,cast,delay,recharge,mana,regen,
                                                    capacity,spread,spellsDaWand));
    }

    // Não inclui obtenção de lista de spells da wand
    public Object getAttr(String nome, String key){
        switch(key){
            case "shuffle":
                return tabela.get(nome).shuffle;
            case "cast":
                return tabela.get(nome).cast;
            case "delay":
                return tabela.get(nome).delay;
            case "recharge":
                return tabela.get(nome).recharge;
            case "mana":
                return tabela.get(nome).mana;
            case "regen":
                return tabela.get(nome).regen;
            case "capacity":
                return tabela.get(nome).capacity;
            case "spread":
                return tabela.get(nome).spread;
            default:
                // Nunca deve chegar aqui!
                return null;
        }
    }

    public List<String> verificarSpellsEmWand(String nome){
        return tabela.get(nome).spellsDaWand;
    }
}
