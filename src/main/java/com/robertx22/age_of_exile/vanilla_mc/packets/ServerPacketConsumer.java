package com.robertx22.age_of_exile.vanilla_mc.packets;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.PlayChannelHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public interface ServerPacketConsumer extends PlayChannelHandler,PacketHandler {

    @Override // THIS IS EFFECTILY STATIC
    default void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
        PacketByteBuf buf, PacketSender responseSender) {
        ServerPacketConsumer ph = this.newInstance(); // Because this is effectively static a new instance must be made
        ph.loadFromData(buf);
        server.submit(()->ph.onReceive(server, player, handler, responseSender));
    }
    
    void onReceive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketSender responseSender);
    
    default void send() {
        PacketByteBuf buf = new PacketByteBuf(new PacketByteBuf(Unpooled.buffer()));

        this.saveToData(buf);
        ClientPlayNetworking.send(this.getIdentifier(), buf);
    }
    default void register() {
        ServerPlayNetworking.registerGlobalReceiver(this.getIdentifier(), this);
    }
}
