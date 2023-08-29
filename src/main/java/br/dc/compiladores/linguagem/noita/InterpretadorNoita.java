package br.dc.compiladores.linguagem.noita;

import br.dc.compiladores.linguagem.noita.NoitaParser.ProgramaContext;

public class InterpretadorNoita extends NoitaBaseVisitor<Void> {
    TabelaDeSimbolos tabela = new TabelaDeSimbolos();

    // Criar TAbelaDeSpells e TabelaDeWands
    

    @Override
    public Void visitPrograma(ProgramaContext ctx){
        
        super.visitPrograma(ctx);
        return null;
    }
}
