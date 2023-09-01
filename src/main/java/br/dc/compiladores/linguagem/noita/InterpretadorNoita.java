package br.dc.compiladores.linguagem.noita;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.dc.compiladores.linguagem.noita.NoitaParser.ProgramaContext;
import br.dc.compiladores.linguagem.noita.NoitaParser.SpellContext;
import br.dc.compiladores.linguagem.noita.NoitaParser.Tipo_extraContext;
import br.dc.compiladores.linguagem.noita.NoitaParser.Tipo_obrigatorioContext;
import br.dc.compiladores.linguagem.noita.NoitaParser.Tipo_wandContext;
import br.dc.compiladores.linguagem.noita.NoitaParser.WandContext;

public class InterpretadorNoita extends NoitaBaseVisitor<Void> {
    TabelaDeSpells tabelaSpells = new TabelaDeSpells();
    TabelaDeWands tabelaWands = new TabelaDeWands();

    // HashMaps contendo valores temporários dos atributos de spells e wands, respectivamente
    Map<String, String> listAttrSpell = new HashMap<String, String>();
    Map<String, String> listAttrWand = new HashMap<String, String>();

    List<String> slotsEmWand = new ArrayList<String>();
    

    @Override
    public Void visitPrograma(ProgramaContext ctx){
        
        super.visitPrograma(ctx);
        return null;
    }

    @Override
    public Void visitSpell(SpellContext ctx){

        // Esvazia o HashMap antes do armazenamento de atributos de uma nova spell
        listAttrSpell.clear();

        listAttrSpell.put("nome", ctx.s.getText());

        for (var spell : ctx.tipos_spell()) {
            visitTipos_spell(spell);
        }

        // Para atributos não-obrigatórios verificação é necessária
        // Valores padrões:
        // - uses: 999
        // - damage: 0
        // - radius: 1.0f
        // - spread: 0.0f
        // - speed: 0.0f
        // - lifetime: 1.0f
        // - delay: 1.0f
        // - recharge: 1.0f
        // - crit: 0.0f

        String tempNome=(String)listAttrSpell.get("nome"),
             tempType=(String)listAttrSpell.get("type");
        int tempMana=Integer.parseInt(listAttrSpell.get("mana")),
             tempUses=(listAttrSpell.containsKey("uses") ? Integer.parseInt(listAttrSpell.get("uses")) : 999),
             tempDamage=(listAttrSpell.containsKey("damage") ? Integer.parseInt(listAttrSpell.get("damage")) : 0);
        float tempRadius=(listAttrSpell.containsKey("radius") ? Float.parseFloat(listAttrSpell.get("radius")) : 1.0f),
             tempSpread=(listAttrSpell.containsKey("spread") ? Float.parseFloat(listAttrSpell.get("spread")) : 0.0f),
             tempSpeed=(listAttrSpell.containsKey("speed") ? Float.parseFloat(listAttrSpell.get("speed")) : 0.0f), 
             tempLifetime=(listAttrSpell.containsKey("lifetime") ? Float.parseFloat(listAttrSpell.get("lifetime")) : 1.0f), 
             tempDelay=(listAttrSpell.containsKey("delay") ? Float.parseFloat(listAttrSpell.get("delay")) : 1.0f), 
             tempRecharge=(listAttrSpell.containsKey("recharge") ? Float.parseFloat(listAttrSpell.get("recharge")) : 1.0f), 
             tempCrit=(listAttrSpell.containsKey("crit") ? Float.parseFloat(listAttrSpell.get("crit")) : 0.0f);
        
        tabelaSpells.adicionar(tempNome, tempType, tempMana, tempUses, tempDamage, 
                                tempRadius, tempSpread, tempSpeed, tempLifetime, tempDelay, 
                                tempRecharge, tempCrit);

        return null;
    }

    @Override
    public Void visitTipo_obrigatorio(Tipo_obrigatorioContext ctx){
        if (ctx.stype != null){
            listAttrSpell.put("type", ctx.stype.getText());
        }
        if (ctx.smana != null){
            listAttrSpell.put("mana", ctx.smana.getText());
        }
        
        return null;
    }

    @Override
    public Void visitTipo_extra(Tipo_extraContext ctx){
        
        if (ctx.suses != null){
            listAttrSpell.put("uses", ctx.suses.getText());
        }
        if (ctx.sdamage != null){
            listAttrSpell.put("damage", ctx.sdamage.getText());
        }
        if (ctx.sradius != null){
            listAttrSpell.put("radius", ctx.sradius.getText());
        }
        if (ctx.sspread != null){
            listAttrSpell.put("spread", ctx.sspread.getText());
        }
        if (ctx.sspeed != null){
            listAttrSpell.put("speed", ctx.sspeed.getText());
        }
        if (ctx.slifetime != null){
            listAttrSpell.put("lifetime", ctx.slifetime.getText());
        }
        if (ctx.sdelay != null){
            listAttrSpell.put("delay", ctx.sdelay.getText());
        }
        if (ctx.srecharge != null){
            listAttrSpell.put("recharge", ctx.srecharge.getText());
        }
        if (ctx.scrit != null){
            listAttrSpell.put("crit", ctx.scrit.getText());
        }

        return null;
    }

    @Override
    public Void visitWand(WandContext ctx){
        
        // Esvazia o HashMap antes do armazenamento dos atributos de uma nova wand
        listAttrWand.clear();

        listAttrWand.put("nome", ctx.w.getText());

        // Remove todas as spells de wand antes de verificar os slots de wand
        for (var slot : slotsEmWand){
            slotsEmWand.remove(slot);
        }

        for (var wand : ctx.tipo_wand()) {
            visitTipo_wand(wand);
        }

        // Para atributos não-obrigatórios verificação é necessária
        // Valores padrões:
        // - capacity: 10

        String tempNome=listAttrWand.get("nome");
        boolean tempShuffle=(boolean)(listAttrWand.get("shuffle").equals("Yes") ? true : false);
        int tempCast=Integer.parseInt(listAttrWand.get("cast")),
             tempMana=Integer.parseInt(listAttrWand.get("mana")), 
             tempRegen=Integer.parseInt(listAttrWand.get("regen")), 
             tempCapacity=(listAttrWand.containsKey("capacity") ? Integer.parseInt(listAttrWand.get("capacity")) : 10);
        float tempDelay=Float.parseFloat(listAttrWand.get("delay")),
             tempRecharge=Float.parseFloat(listAttrWand.get("recharge")), 
             tempSpread=Float.parseFloat(listAttrWand.get("spread"));

        
        tabelaWands.adicionar(tempNome, tempShuffle, tempCast, tempDelay, tempRecharge, 
                            tempMana, tempRegen, tempCapacity, tempSpread, slotsEmWand);

        // WIP: Printar detalhes de wand e de suas spells
        int manaRest=tempMana, numSpells=0;
        float dps=0.0f, tempDps=0.0f;

        for (var spell : tabelaWands.verificarSpellsEmWand(tempNome)){
            numSpells++;
            manaRest -= (Integer)tabelaSpells.getAttr(spell, "mana");
            tempDps += (Integer)tabelaSpells.getAttr(spell, "damage")
                         * (Float)tabelaSpells.getAttr(spell, "delay")
                         * (Float)tabelaSpells.getAttr(spell, "lifetime");
        } 
        
        int slotsRest = tempCapacity - numSpells;
        // Calcula o dano por segundo por spell
        if (numSpells > 0){
            dps = tempDps / numSpells;
        }

        System.out.println(ctx.w.getText()+":");
        System.out.println("\tDamage per second: "+dps);
        System.out.println("\tFree mana: "+manaRest);
        System.out.println("\tFree slots: "+slotsRest+"\n");
        System.out.println("\tEquipped spells:");

        for (var spell : tabelaWands.verificarSpellsEmWand(tempNome)){
            System.out.println("\t\t"+spell+":");
            System.out.println("\t\t\tType: "+tabelaSpells.getAttr(spell, "type"));
            System.out.println("\t\t\tMana: "+tabelaSpells.getAttr(spell, "mana"));
            System.out.println("\t\t\tUses: "+tabelaSpells.getAttr(spell, "uses"));
            System.out.println("\t\t\tDamage: "+tabelaSpells.getAttr(spell, "damage"));
            System.out.println("\t\t\tRadius: "+tabelaSpells.getAttr(spell, "radius"));
            System.out.println("\t\t\tSpread: "+tabelaSpells.getAttr(spell, "spread")+" DEG");
            System.out.println("\t\t\tSpeed: "+tabelaSpells.getAttr(spell, "speed"));
            System.out.println("\t\t\tLifetime: "+tabelaSpells.getAttr(spell, "lifetime")+" s");
            System.out.println("\t\t\tDelay: "+tabelaSpells.getAttr(spell, "delay")+" s");
            System.out.println("\t\t\tRecharge: "+tabelaSpells.getAttr(spell, "recharge")+" s");
            System.out.println("\t\t\tCrit: "+tabelaSpells.getAttr(spell, "crit")+"%");
        }

        return null;
    }

    @Override
    public Void visitTipo_wand(Tipo_wandContext ctx){

        if (ctx.wshuffle != null){
            listAttrWand.put("shuffle", ctx.wshuffle.getText());
        }
        if (ctx.wcast != null){
            listAttrWand.put("cast", ctx.wcast.getText());
        }
        if (ctx.wdelay != null){
            listAttrWand.put("delay", ctx.wdelay.getText());
        }
        if (ctx.wrecharge != null){
            listAttrWand.put("recharge", ctx.wrecharge.getText());
        }
        if (ctx.wmana != null){
            listAttrWand.put("mana", ctx.wmana.getText());
        }
        if (ctx.wregen != null){
            listAttrWand.put("regen", ctx.wregen.getText());
        }
        if (ctx.numslots != null){
            listAttrWand.put("capacity", ctx.numslots.getText());
        }
        if (ctx.wspread != null){
            listAttrWand.put("spread", ctx.wspread.getText());
        }
        // Lista de slots
        if (ctx.nslots != null){
            for (var slot : ctx.nslots){
                slotsEmWand.add(slot.getText());
            }
        }
        
        return null;
    }
}
