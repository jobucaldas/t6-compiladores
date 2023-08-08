package br.dc.compiladores.linguagem.noita;

import br.dc.compiladores.linguagem.noita.NoitaParser.ProgramaContext;
import br.dc.compiladores.linguagem.noita.NoitaParser.SpellContext;
import br.dc.compiladores.linguagem.noita.NoitaParser.Tipo_wandContext;
import br.dc.compiladores.linguagem.noita.NoitaParser.WandContext;
import br.dc.compiladores.linguagem.noita.TabelaDeSimbolos.TipoObjeto;

public class LinguagemNoita extends NoitaBaseVisitor<Void> {
    
    TabelaDeSimbolos tabela = new TabelaDeSimbolos();

    @Override
    public Void visitPrograma(ProgramaContext ctx) {
        return super.visitPrograma(ctx);
    }

    @Override
    public Void visitSpell(SpellContext ctx) {
        if (ctx.s != null) {
            String strNomeSpell = ctx.s.getText();
            if (!tabela.existe(strNomeSpell)){
                tabela.adicionar(strNomeSpell, TipoObjeto.SPELL);
            } else{
                // Reportar erro de spell já declarada 
            }
        }

        for (var spell : ctx.tipos_spell()) {
            visitTipos_spell(spell);
        }

        return null;
    }

    public Void visitWand(WandContext ctx) {
        if (ctx.w != null) {
            String strNomeWand = ctx.w.getText();
            if (!tabela.existe(strNomeWand)){
                tabela.adicionar(strNomeWand, TipoObjeto.WAND);
            } else{
                // Reportar erro de wand já declarada 
            }
        }

        for (var wand : ctx.tipo_wand()) {
            visitTipo_wand(wand);
        }

        return null;
    }

    @Override
    public Void visitTipo_wand(Tipo_wandContext ctx){

        // WIP
        
        return super.visitTipo_wand(ctx);
    }
}
