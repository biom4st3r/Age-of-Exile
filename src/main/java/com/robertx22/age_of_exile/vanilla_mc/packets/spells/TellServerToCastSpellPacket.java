package com.robertx22.age_of_exile.vanilla_mc.packets.spells;

import com.robertx22.age_of_exile.capability.player.PlayerSpellCap;
import com.robertx22.age_of_exile.database.data.skill_gem.SkillGemData;
import com.robertx22.age_of_exile.database.data.spells.SpellCastType;
import com.robertx22.age_of_exile.database.data.spells.components.Spell;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.age_of_exile.database.registry.Database;
import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.age_of_exile.uncommon.datasaving.Load;
import com.robertx22.age_of_exile.vanilla_mc.packets.ClientToServerPacket;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class TellServerToCastSpellPacket implements ClientToServerPacket {

    int number;

    public TellServerToCastSpellPacket(int number) {
        this.number = number;
    }

    public TellServerToCastSpellPacket() {
    }

    @Override
    public void loadFromData(PacketByteBuf buf) {
        this.number = buf.readInt();
    }

    @Override
    public void saveToData(PacketByteBuf buf) {
        buf.writeInt(number);
    }

    @Override
    public Identifier getIdentifier() {
        return new Identifier(Ref.MODID, "castspell");
    }
    public static Identifier getId() {
        return new Identifier(Ref.MODID, "castspell");
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public TellServerToCastSpellPacket newInstance() {
        return new TellServerToCastSpellPacket();
    }

    @Override
    public void onReceive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
            PacketSender responseSender) {      

            PlayerSpellCap.ISpellsCap spells = Load.spells(player);
    
            SkillGemData data = spells.getSkillGemData()
                .getSkillGemOf(number);
    
            if (data == null) {
                return;
            }
    
            if (player.isBlocking()) {
                return;
            }
    
            Spell spell = Database.Spells()
                .get(data.getSkillGem().spell_id);
    
            if (spell != null) {
    
                if (spells.getCastingData()
                    .canCast(spell, player)) {
    
                    spells.getCastingData()
                        .setToCast(spell, player);
                    SpellCastContext c = new SpellCastContext(player, 0, spell);
    
                    if (spell.config.cast_type == SpellCastType.USE_ITEM) {
    
                    } else {
                        spell.spendResources(c);
                    }
                }
                spells.syncToClient(player);
            }
        
    }
    
}
