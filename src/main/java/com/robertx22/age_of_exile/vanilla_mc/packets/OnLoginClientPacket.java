package com.robertx22.age_of_exile.vanilla_mc.packets;

import com.robertx22.age_of_exile.database.registry.RegistryPackets;
import com.robertx22.age_of_exile.database.registry.SyncTime;
import com.robertx22.age_of_exile.mmorpg.Ref;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class OnLoginClientPacket implements ClientPacketHandler {

    @Override
    public Identifier getIdentifier() {
        return new Identifier(Ref.MODID, "onlogin");
    }

    @Override
    public void loadFromData(PacketByteBuf tag) {
        when = When.valueOf(tag.readString(10));
        sync = SyncTime.valueOf(tag.readString(30));
    }

    @Override
    public void saveToData(PacketByteBuf tag) {
        tag.writeString(when.name(), 10);
        tag.writeString(sync.name(), 30);
    }

    @Override
    public void onReceive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketSender responseSender) {

        if (when == When.BEFORE) {
        }
        if (when == When.AFTER) {
            RegistryPackets.registerAll(sync);
        }

    }

    @Override
    @SuppressWarnings({"unchecked"})
    public OnLoginClientPacket newInstance() {
        return new OnLoginClientPacket();
    }

    public enum When {
        BEFORE, AFTER
    }

    public When when;
    SyncTime sync;

    public OnLoginClientPacket(SyncTime sync, When when) {
        this.when = when;
        this.sync = sync;
    }

    public OnLoginClientPacket() {

    }
}