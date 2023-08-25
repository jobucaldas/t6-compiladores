package br.dc.compiladores.linguagem.noita;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabelaDeSimbolos {

    public enum TipoNoita {
        INTEIRO,
        REAL,
        LITERAL,
        LOGICO,
        INVALIDO
    }

    class EntradaTabelaDeSimbolos {
        String nome;
        List<String> spellsDaWand;

        private EntradaTabelaDeSimbolos(String nome, List<String> spellsDaWand) {
            this.nome = nome;
            this.spellsDaWand = spellsDaWand;
        }
    }

    private final Map<String, EntradaTabelaDeSimbolos> tabela;

    public TabelaDeSimbolos() {
        this.tabela = new HashMap<>();
    }

    public void adicionar(String nome, List<String> spellsDaWand) {
        tabela.put(nome, new EntradaTabelaDeSimbolos(nome, spellsDaWand));
    }

    public boolean existe(String nome) {
        return tabela.containsKey(nome);
    }

    public List<String> verificarSpellsEmWand(String nome){
        return tabela.get(nome).spellsDaWand;
    }
}
