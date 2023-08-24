package br.dc.compiladores.linguagem.noita;

import java.util.ArrayList;
import java.util.List;

import br.dc.compiladores.linguagem.noita.NoitaParser.ProgramaContext;
import br.dc.compiladores.linguagem.noita.NoitaParser.SpellContext;
import br.dc.compiladores.linguagem.noita.NoitaParser.Tipo_obrigatorioContext;
import br.dc.compiladores.linguagem.noita.NoitaParser.Tipo_wandContext;
import br.dc.compiladores.linguagem.noita.NoitaParser.Tipos_spellContext;
import br.dc.compiladores.linguagem.noita.NoitaParser.WandContext;
import br.dc.compiladores.linguagem.noita.TabelaDeSimbolos.TipoObjeto;

public class LinguagemNoita extends NoitaBaseVisitor<Void> {
    
    public enum TipoSpell{
        PROJECTILE,
        AURA,
        OBJECT,
        INVALIDO
    }

    TabelaDeSimbolos tabela = new TabelaDeSimbolos();
    
    // Arrays contendo todos os atributos obrigatórios de spells 
    // e wands, respectivamente
    String[] tipoObrigtSpell = {"type", "mana"};
    String[] tipoObrigtWand = {"shuffle", "spells/cast", "delay", "recharge",
                                 "mana", "regen", "spread", "slots"};
    List<String> tipoFaltaSpell = new ArrayList<String>();
    List<String> tipoFaltaWand = new ArrayList<String>();
    int numSlots = 0;
    // Lista temporária de slots ocupados em wand
    List<String> slotsEmWand = new ArrayList<String>();


    @Override
    public Void visitPrograma(ProgramaContext ctx) {
        return super.visitPrograma(ctx);
    }
    
    @Override
    public Void visitSpell(SpellContext ctx) {
        if (ctx.s != null) {
            String strNomeSpell = ctx.s.getText();
            if (!tabela.existe(strNomeSpell)){
                tabela.adicionar(strNomeSpell, TipoObjeto.SPELL, null);
            } else{
                // Reportar erro de spell já declarada 
            }
        }

        // Loop para esvaziar lista de atributos obrigatórios
        // antes de ser reinicializada
        for (var attr : tipoFaltaSpell){
            tipoFaltaSpell.remove(attr);
        }

        // Inicializa lista com atributos obrigatórios
        for (var attr : tipoObrigtSpell){
            tipoFaltaSpell.add(attr);
        }
        
        for (var spell : ctx.tipos_spell()) {
            visitTipos_spell(spell);
        }
        
        // Se spell possui atributos obrigatórios não declarados, reporta erro
        // para cada atributo obrigatório ausente
        for (var attr : tipoFaltaSpell){
            // Reporta erro de atributo obrigatório não declarado
        }
        
        return null;
    }

    @Override
    public Void visitTipo_obrigatorio(Tipo_obrigatorioContext ctx){
        int posDoispontos = ctx.getText().indexOf(':');

        if (posDoispontos != -1){
            String atributoSpell = ctx.getText().substring(0, posDoispontos);

            if (tipoFaltaSpell.contains(atributoSpell)){
                tipoFaltaSpell.remove(atributoSpell);
            }
        }
        
        super.visitTipo_obrigatorio(ctx);

        return null;
    } 

    @Override
    public Void visitWand(WandContext ctx) {
        boolean adicionaWand = false;
        String strNomeWand;
        if (ctx.w != null) {
            strNomeWand = ctx.w.getText();
            if (!tabela.existe(strNomeWand)){
                adicionaWand = true; // Wand será adicionada na tabela mais tarde
            } else{
                // Reportar erro de wand já declarada 
            }
        }

        // Loop para esvaziar lista de atributos obrigatórios
        // antes de ser reinicializada
        for (var attr : tipoFaltaWand){
            tipoFaltaWand.remove(attr);
        }

        // Inicializa lista com atributos obrigatórios
        for (var attr : tipoObrigtWand){
            tipoFaltaWand.add(attr);
        }

        // Capacidade padrão de slots em wand
        numSlots = 10;

        // Remove todas as spells de wand antes de verificar os slots de wand
        for (var slot : slotsEmWand){
            slotsEmWand.remove(slot);
        }

        for (var wand : ctx.tipo_wand()) {
            visitTipo_wand(wand);
        }

        // Adiciona wand com a lista de slots à tabela
        if (adicionaWand){
            strNomeWand = ctx.w.getText();
            tabela.adicionar(strNomeWand, TipoObjeto.WAND, slotsEmWand);
        }

        // Se wand possui atributos obrigatórios não declarados, reporta erro
        // para cada atributo obrigatório ausente
        for (var attr : tipoFaltaWand){
            // Reporta erro de atributo obrigatório não declarado
        }

        return null;
    }

    @Override
    public Void visitTipo_wand(Tipo_wandContext ctx){
        int posDoispontos = ctx.getText().indexOf(':');

        if (posDoispontos != -1){
            String atributoSpell = ctx.getText().substring(0, posDoispontos);

            if (tipoFaltaWand.contains(atributoSpell)){
                tipoFaltaWand.remove(atributoSpell);
            }

            if (atributoSpell == "capacity"){
                // Limita o número de spells no atributo slots
                numSlots = Integer.parseInt(ctx.numslots.getText());
            }

            if (atributoSpell == "slots"){
                int numSlotsTemp = 0;
                // Verifica o número de slots sendo ocupados
                for (var slot : ctx.nslots){
                    numSlotsTemp++;
                    // Verifica se spell foi declarada
                    if (tabela.existe(slot.getText())){
                        if (numSlotsTemp > numSlots){
                            // Reporta erro de número de slots insuficiente
                        } else{
                            slotsEmWand.add(slot.getText());
                        }
                    } else{  // Spell na lista de slots não existe
                        // Reporta erro de spell não declarada
                    }
                }
            }
        }
        
        super.visitTipo_wand(ctx);

        return null;
    }
}
