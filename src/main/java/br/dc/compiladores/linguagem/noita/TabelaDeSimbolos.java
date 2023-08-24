package br.dc.compiladores.linguagem.noita;

import java.util.HashMap;
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

        private EntradaTabelaDeSimbolos(String nome, TipoObjeto tipo) {
            this.nome = nome;
            this.tipo = tipo;
        }
    }

    private final Map<String, EntradaTabelaDeSimbolos> tabela;

    public TabelaDeSimbolos() {
        this.tabela = new HashMap<>();
    }

    public void adicionar(String nome, TipoObjeto tipo) {
        tabela.put(nome, new EntradaTabelaDeSimbolos(nome, tipo));
    }

    public boolean existe(String nome) {
        return tabela.containsKey(nome);
    }

    public TipoObjeto verificar(String nome) {
        return tabela.get(nome).tipo;
    }
}
