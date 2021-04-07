package com.robertx22.age_of_exile.vanilla_mc.packets.registry;

import java.util.List;

import com.google.common.collect.Lists;
import com.robertx22.age_of_exile.aoe_data.datapacks.bases.ISerializedRegistryEntry;
import com.robertx22.age_of_exile.database.IByteBuf;
import com.robertx22.age_of_exile.database.registry.Database;
import com.robertx22.age_of_exile.database.registry.SlashRegistryContainer;
import com.robertx22.age_of_exile.database.registry.SlashRegistryType;
import com.robertx22.age_of_exile.mmorpg.MMORPG;
import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.age_of_exile.uncommon.testing.Watch;
import com.robertx22.age_of_exile.vanilla_mc.packets.ServerToClientPacket;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class EfficientRegistryPacket<T extends IByteBuf & ISerializedRegistryEntry> implements ServerToClientPacket {
    public static Identifier ID = new Identifier(Ref.MODID, "eff_reg");
    private List<T> items;

    SlashRegistryType type;

    public EfficientRegistryPacket() {

    }

    public EfficientRegistryPacket(SlashRegistryType type, List<T> list) {
        this.type = type;
        this.items = list;
    }

    @Override
    public Identifier getIdentifier() {
        return ID;
    }

    @Override
    public void loadFromData(PacketByteBuf buf) {

        this.type = SlashRegistryType.valueOf(buf.readString(30));

        if (MMORPG.RUN_DEV_TOOLS) {
            //System.out.print("\n Eff packet " + type.name() + " is " + buf.readableBytes() + " bytes big \n");
        }

        IByteBuf<T> serializer = (IByteBuf<T>) type.getSerializer();

        this.items = Lists.newArrayList();

        int i = buf.readVarInt();

        for (int j = 0; j < i; ++j) {
            this.items.add(serializer.getFromBuf(buf));
        }

    }

    @Override
    public void saveToData(PacketByteBuf buf) {
        Watch watch = null;
        if (MMORPG.RUN_DEV_TOOLS) {
            watch = new Watch();
        }

        buf.writeString(type.name(), 30);
        buf.writeVarInt(this.items.size());
        items.forEach(x -> x.toBuf(buf));

        if (MMORPG.RUN_DEV_TOOLS) {
            //watch.print("Writing efficient direct bytebuf packet for " + this.type.name() + " ");
        }
    }

    @Override
    public void onReceive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketSender responseSender) {
        SlashRegistryContainer reg = Database.getRegistry(type);

        reg.unregisterAllEntriesFromDatapacks();

        items.forEach(x -> x.registerToSlashRegistry());

        System.out.println("Efficient " + type.name() + " reg load on client success with: " + reg.getSize() + " entries.");

    }

    @Override
    @SuppressWarnings({"unchecked"})
    public EfficientRegistryPacket newInstance() {
        return new EfficientRegistryPacket();
    }
}