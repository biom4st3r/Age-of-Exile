package com.robertx22.age_of_exile.vanilla_mc.packets;

import com.robertx22.age_of_exile.capability.entity.EntityCap;
import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.age_of_exile.uncommon.datasaving.Load;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class EfficientMobUnitPacket implements ServerToClientPacket {

    public int id;
    public CompoundTag nbt;

    public EfficientMobUnitPacket() {

    }

    public EfficientMobUnitPacket(Entity entity, EntityCap.UnitData data) {
        this.id = entity.getEntityId();
        CompoundTag nbt = new CompoundTag();
        data.addClientNBT(nbt);
        this.nbt = nbt;

    }

    @Override
    public Identifier getIdentifier() {
        return new Identifier(Ref.MODID, "effmob");
    }

    @Override
    public void loadFromData(PacketByteBuf tag) {
        id = tag.readInt();
        nbt = tag.readCompoundTag();
    }

    @Override
    public void saveToData(PacketByteBuf tag) {
        tag.writeInt(id);
        tag.writeCompoundTag(nbt);
    }

    @Override
    public void onReceive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketSender responseSender) {
        Entity entity = client.world.getEntityById(id);

        if (entity instanceof LivingEntity) {

            LivingEntity en = (LivingEntity) entity;

            Load.Unit(en)
                .loadFromClientNBT(nbt);
        }
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public EfficientMobUnitPacket newInstance() {
        return new EfficientMobUnitPacket();
    }
}
