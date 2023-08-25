package br.dc.compiladores.linguagem.noita;

import br.dc.compiladores.linguagem.noita.NoitaParser.ProgramaContext;
import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Principal {
    public static void main(String args[]) throws IOException{
        CharStream cs = CharStreams.fromFileName(args[0]);
        NoitaLexer lexer = new NoitaLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        NoitaParser parser = new NoitaParser(tokens);
        ProgramaContext arvore = parser.programa();
        LinguagemNoita ln = new LinguagemNoita();
        ln.visitPrograma(arvore);
        LinguagemNoitaUtils.errosSemanticos.forEach((s) -> System.out.println(s));
        System.out.println("Fim da compilação!");
    }
}
