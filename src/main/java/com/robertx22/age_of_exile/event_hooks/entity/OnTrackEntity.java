package com.robertx22.age_of_exile.event_hooks.entity;

import com.robertx22.library_of_exile.main.Packets;
import com.robertx22.age_of_exile.saveclasses.unit.Unit;
import com.robertx22.age_of_exile.uncommon.datasaving.Load;
import nerdhub.cardinal.components.api.event.TrackingStartCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public class OnTrackEntity implements TrackingStartCallback {

    @Override
    public void onPlayerStartTracking(ServerPlayerEntity serverPlayerEntity, Entity entity) {

        try {
            if (entity instanceof LivingEntity) {

                if (!Unit.shouldSendUpdatePackets((LivingEntity) entity)) {
                    return;
                }

                if (entity.isPartOf(serverPlayerEntity) == false) {

                    Unit.getUpdatePacketFor((LivingEntity) entity, Load.Unit(entity)).send(serverPlayerEntity);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
