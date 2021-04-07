package com.robertx22.age_of_exile.vanilla_mc.packets;

import com.robertx22.age_of_exile.config.forge.ModConfig;
import com.robertx22.age_of_exile.event_hooks.ontick.OnClientTick;
import com.robertx22.age_of_exile.mmorpg.Ref;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class NoManaPacket implements ServerToClientPacket {

    @Override
    public Identifier getIdentifier() {
        return new Identifier(Ref.MODID, "nomana");
    }

    @Override
    public void loadFromData(PacketByteBuf tag) {

    }

    @Override
    public void saveToData(PacketByteBuf tag) {

    }

    @Override
    @Environment(EnvType.CLIENT)
    public void onReceive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketSender responseSender) {
        if (ModConfig.get().client.SHOW_LOW_ENERGY_MANA_WARNING) {
            if (OnClientTick.canSoundNoMana()) {
                OnClientTick.setNoManaSoundCooldown();
                client.player.playSound(SoundEvents.BLOCK_REDSTONE_TORCH_BURNOUT, 0.5F, 0);
            }
        }
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public NoManaPacket newInstance() {
        return new NoManaPacket();
    }

    public enum MessageTypes {
        NoEnergy,
        NoMana
    }

    public NoManaPacket() {
    }

}