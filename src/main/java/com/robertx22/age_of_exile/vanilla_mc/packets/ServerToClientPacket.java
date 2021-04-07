package com.robertx22.age_of_exile.vanilla_mc.packets;

import com.robertx22.age_of_exile.mmorpg.MMORPG;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.PlayChannelHandler;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public interface ServerToClientPacket extends PlayChannelHandler, PacketHandler {

    default void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf,
            PacketSender responseSender) { // THIS IS EFFECTILY STATIC
        ServerToClientPacket ph = this.newInstance(); // Because this is effectively static a new instance must be made
        ph.loadFromData(buf);
        client.submit(()->ph.onReceive(client,handler,responseSender));
    }
    
    void onReceive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketSender responseSender);

    default void send(PlayerEntity target) {
        if(!target.world.isClient) {
            this.sendToClient((ServerPlayerEntity)target);
        }
        else {
            MMORPG.logError("Attempted to send S2C Packet from Client! This should not happen");
        }
    }

    default void sendToClient(ServerPlayerEntity spe) {
        PacketByteBuf buf = new PacketByteBuf(new PacketByteBuf(Unpooled.buffer()));
        this.saveToData(buf);
        ServerPlayNetworking.send(spe, this.getIdentifier(), buf);
    }

    default void registerServerPacket() {
        ClientPlayNetworking.registerGlobalReceiver(this.getIdentifier(), this);
    }

    default void sendToTracking(Entity entity) {
        PlayerLookup.tracking(entity).forEach((player)-> {
            this.sendToClient(player);
        });
    }
}
