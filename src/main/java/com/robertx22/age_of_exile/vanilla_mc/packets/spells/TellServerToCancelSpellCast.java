package com.robertx22.age_of_exile.vanilla_mc.packets.spells;

import com.robertx22.age_of_exile.capability.player.PlayerSpellCap;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.age_of_exile.uncommon.datasaving.Load;
import com.robertx22.age_of_exile.vanilla_mc.packets.ServerPacketHandler;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class TellServerToCancelSpellCast implements ServerPacketHandler {

    @Override
    public void loadFromData(PacketByteBuf buf) {
        
    }

    @Override
    public void saveToData(PacketByteBuf buf) {
        
    }

    @Override
    public Identifier getIdentifier() {
        return new Identifier(Ref.MODID, "cancelspell");
    }
    public static Identifier getId() {
        return new Identifier(Ref.MODID, "cancelspell");
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public TellServerToCancelSpellCast newInstance() { 
        return new TellServerToCancelSpellCast();
    }

    @Override
    public void onReceive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
            PacketSender responseSender) {
    
            PlayerSpellCap.ISpellsCap spells = Load.spells(player);
            if (spells.getCastingData()
                .getSpellBeingCast() != null) {
                SpellCastContext sctx = new SpellCastContext(player, spells.getCastingData().castingTicksDone, spells.getCastingData()
                    .getSpellBeingCast());
    
                spells.getCastingData()
                    .tryCast(player, spells, sctx);
                spells.getCastingData()
                    .cancelCast(player);
    
                spells.syncToClient(player);
                }
        
    }
    
}
