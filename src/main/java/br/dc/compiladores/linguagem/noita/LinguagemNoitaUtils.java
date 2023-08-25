package br.dc.compiladores.linguagem.noita;

import java.util.List;
import java.util.ArrayList;
import org.antlr.v4.runtime.Token;

public class LinguagemNoitaUtils {
    public static List<String> errosSemanticos = new ArrayList<String>();

    // Método para adicionar erro semântico
    public static void addErroSemantico(Token t, String erroMensagem){
        int linha = t.getLine();
        errosSemanticos.add("Erro linha "+linha+": "+erroMensagem);
    }
}
