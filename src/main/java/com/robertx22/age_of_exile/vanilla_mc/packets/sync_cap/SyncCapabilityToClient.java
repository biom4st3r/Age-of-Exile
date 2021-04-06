package com.robertx22.age_of_exile.vanilla_mc.packets.sync_cap;

import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.age_of_exile.vanilla_mc.packets.ClientPacketHandler;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class SyncCapabilityToClient implements ClientPacketHandler {

    public SyncCapabilityToClient() {

    }

    private CompoundTag nbt;
    private PlayerCaps type;

    public SyncCapabilityToClient(PlayerEntity p, PlayerCaps type) {
        this.nbt = type.getCap(p)
            .toTag(new CompoundTag());
        this.type = type;
    }

    @Override
    public Identifier getIdentifier() {
        return new Identifier(Ref.MODID, "synccaptoc");
    }

    @Override
    public void loadFromData(PacketByteBuf tag) {
        nbt = tag.readCompoundTag();
        type = tag.readEnumConstant(PlayerCaps.class);

    }

    @Override
    public void saveToData(PacketByteBuf tag) {
        try {
            tag.writeCompoundTag(nbt);
            tag.writeEnumConstant(type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onReceive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketSender responseSender) {
        final PlayerEntity player = client.player;

        if (player != null) {
            type.getCap(player)
                .fromTag(nbt);

        }
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public SyncCapabilityToClient newInstance() {
        return new SyncCapabilityToClient();
    }
}

