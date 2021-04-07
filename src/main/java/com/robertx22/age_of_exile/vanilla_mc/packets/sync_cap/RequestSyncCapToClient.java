package com.robertx22.age_of_exile.vanilla_mc.packets.sync_cap;

import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.age_of_exile.vanilla_mc.packets.ClientToServerPacket;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class RequestSyncCapToClient implements ClientToServerPacket {

    public RequestSyncCapToClient() {

    }

    private PlayerCaps type;

    public RequestSyncCapToClient(PlayerCaps type) {
        this.type = type;
    }

    @Override
    public void loadFromData(PacketByteBuf buf) {
        type = buf.readEnumConstant(PlayerCaps.class);
    }

    @Override
    public void saveToData(PacketByteBuf buf) {
        buf.writeEnumConstant(type);
    }

    @Override
    public Identifier getIdentifier() {
        return new Identifier(Ref.MODID, "reqdata");
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public RequestSyncCapToClient newInstance() {
        return new RequestSyncCapToClient();
    }

    @Override
    public void onReceive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
            PacketSender responseSender) {        
        new SyncCapabilityToClient(player, type).sendToClient(player);
    }

    public static Identifier getId() {
        return new Identifier(Ref.MODID, "reqdata");
    }
    
}
