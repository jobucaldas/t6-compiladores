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

    public enum TipoObjeto {
        SPELL,
        WAND,
        INVALIDO
    }

    class EntradaTabelaDeSimbolos {
        String nome;
        TipoObjeto tipo;
        List<String> spellsDaWand;

        private EntradaTabelaDeSimbolos(String nome, TipoObjeto tipo, List<String> spellsDaWand) {
            this.nome = nome;
            this.tipo = tipo;
            this.spellsDaWand = spellsDaWand;
        }
    }

    private final Map<String, EntradaTabelaDeSimbolos> tabela;

    public TabelaDeSimbolos() {
        this.tabela = new HashMap<>();
    }

    public void adicionar(String nome, TipoObjeto tipo, List<String> spellsDaWand) {
        // Spells não devem ter lista de spells
        if (tipo == TipoObjeto.SPELL){
            tabela.put(nome, new EntradaTabelaDeSimbolos(nome, tipo, null));
        } else{
            tabela.put(nome, new EntradaTabelaDeSimbolos(nome, tipo, spellsDaWand));
        }
    }

    public boolean existe(String nome) {
        return tabela.containsKey(nome);
    }

    public TipoObjeto verificar(String nome) {
        return tabela.get(nome).tipo;
    }

    public List<String> verificarSpellsEmWand(String nome){
        return tabela.get(nome).spellsDaWand;
    }
}
