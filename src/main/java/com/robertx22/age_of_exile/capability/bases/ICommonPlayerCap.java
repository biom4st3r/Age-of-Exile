package com.robertx22.age_of_exile.capability.bases;

import com.robertx22.age_of_exile.vanilla_mc.packets.sync_cap.PlayerCaps;
import com.robertx22.age_of_exile.vanilla_mc.packets.sync_cap.SyncCapabilityToClient;

import net.minecraft.entity.player.PlayerEntity;

public interface ICommonPlayerCap extends ICommonCap {

    PlayerCaps getCapType();

    default void syncToClient(PlayerEntity player) {
        if (!player.world.isClient) {
            new SyncCapabilityToClient(player, getCapType()).sendToClient(player);
        }
    }

}
