package com.robertx22.age_of_exile.vanilla_mc.packets.registry;

import com.robertx22.age_of_exile.database.registry.Database;
import com.robertx22.age_of_exile.database.registry.SyncTime;
import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.age_of_exile.vanilla_mc.packets.OnLoginClientPacket;
import com.robertx22.age_of_exile.vanilla_mc.packets.ServerPacketConsumer;
import com.robertx22.library_of_exile.main.Packets;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class RequestRegistriesPacket implements ServerPacketConsumer {

    SyncTime sync;

    public RequestRegistriesPacket(SyncTime sync) {
        this.sync = sync;
    }

    public RequestRegistriesPacket() {

    }

    @Override
    public void loadFromData(PacketByteBuf buf) {
        sync = SyncTime.valueOf(buf.readString(30));
    }

    @Override
    public void saveToData(PacketByteBuf buf) {
        buf.writeString(sync.name(), 30);
    }

    @Override
    public Identifier getIdentifier() {
        return new Identifier(Ref.MODID, "req_reqs");
    }
    public static Identifier getId() {
        return new Identifier(Ref.MODID, "req_reqs");
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public RequestRegistriesPacket newInstance() {
        return new RequestRegistriesPacket();
    }

    @Override
    public void onReceive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
            PacketSender responseSender) {        
        Database.sendPacketsToClient(player, sync);
        new OnLoginClientPacket(sync, OnLoginClientPacket.When.AFTER).send(player);
    }
    
}
