package com.robertx22.age_of_exile.vanilla_mc.packets;

import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.age_of_exile.uncommon.utilityclasses.ClientOnly;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class ForceChoosingRace implements ClientPacketHandler {

    @Override
    public Identifier getIdentifier() {
        return new Identifier(Ref.MODID, "force_c_r");
    }

    @Override
    public void loadFromData(PacketByteBuf tag) {
    }

    @Override
    public void saveToData(PacketByteBuf tag) {
    }

    @Override
    public void onReceive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketSender responseSender) {

        ClientOnly.openRaceSelection();
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public ForceChoosingRace newInstance() {
        return new ForceChoosingRace();
    }

    public ForceChoosingRace() {

    }
}