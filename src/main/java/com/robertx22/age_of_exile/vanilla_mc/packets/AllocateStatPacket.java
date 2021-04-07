package com.robertx22.age_of_exile.vanilla_mc.packets;

import com.robertx22.age_of_exile.capability.player.PlayerStatPointsCap;
import com.robertx22.age_of_exile.database.data.stats.Stat;
import com.robertx22.age_of_exile.database.data.stats.types.core_stats.base.BaseCoreStat;
import com.robertx22.age_of_exile.database.registry.Database;
import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.age_of_exile.uncommon.datasaving.Load;
import com.robertx22.age_of_exile.vanilla_mc.packets.sync_cap.PlayerCaps;
import com.robertx22.age_of_exile.vanilla_mc.packets.sync_cap.SyncCapabilityToClient;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class AllocateStatPacket implements ServerPacketHandler {

    public String stat;
    AllocateStatPacket.ACTION action;

    public enum ACTION {
        ALLOCATE, REMOVE
    }

    public AllocateStatPacket() {

    }

    public AllocateStatPacket(Stat stat) {
        this.stat = stat.GUID();
        this.action = ACTION.ALLOCATE;
    }

    @Override
    public void loadFromData(PacketByteBuf buf) {
        stat = buf.readString(30);
        action = buf.readEnumConstant(AllocateStatPacket.ACTION.class);
    }

    @Override
    public void saveToData(PacketByteBuf buf) {
        buf.writeString(stat, 30);
        buf.writeEnumConstant(action);
        
    }

    @Override
    public Identifier getIdentifier() {
        return new Identifier(Ref.MODID, "stat_alloc");
    }
    public static Identifier getId() {
        return new Identifier(Ref.MODID, "stat_alloc");
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public AllocateStatPacket newInstance() {
        return new AllocateStatPacket();
    }

    @Override
    public void onReceive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
            PacketSender responseSender) {

        Load.Unit(player)
            .setEquipsChanged(true);
        Load.Unit(player)
            .tryRecalculateStats();

        PlayerStatPointsCap cap = Load.statPoints(player);

        if (cap.getFreePoints() > 0) {
            if (Database.Stats()
                .get(stat) instanceof BaseCoreStat) {
                cap.data.map.put(stat, 1 + cap.data.map.getOrDefault(stat, 0));
                new SyncCapabilityToClient(player, PlayerCaps.STAT_POINTS).send(player);
            }
        }
        
    }
    
}
