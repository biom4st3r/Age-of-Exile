package com.robertx22.age_of_exile.vanilla_mc.packets;

import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.age_of_exile.mmorpg.SyncedToClientValues;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class SyncAreaLevelPacket implements ServerToClientPacket {

    public int lvl;

    public SyncAreaLevelPacket() {

    }

    public SyncAreaLevelPacket(int lvl) {
        this.lvl = lvl;
    }

    @Override
    public Identifier getIdentifier() {
        return new Identifier(Ref.MODID, "arealvl");
    }

    @Override
    public void loadFromData(PacketByteBuf tag) {
        lvl = tag.readInt();
    }

    @Override
    public void saveToData(PacketByteBuf tag) {
        tag.writeInt(lvl);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void onReceive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketSender responseSender) {        SyncedToClientValues.areaLevel = lvl;
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public SyncAreaLevelPacket newInstance() {
        return new SyncAreaLevelPacket();
    }
}

