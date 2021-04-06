package com.robertx22.age_of_exile.vanilla_mc.packets;

import com.robertx22.age_of_exile.database.data.races.PlayerRace;
import com.robertx22.age_of_exile.database.registry.Database;
import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.age_of_exile.uncommon.datasaving.Load;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class ChooseRacePacket implements ServerPacketConsumer {
    
    public String race = "";

    public ChooseRacePacket() {

    }

    public ChooseRacePacket(PlayerRace race) {
        this.race = race.GUID();
    }


    @Override
    public void loadFromData(PacketByteBuf buf) {
        race = buf.readString(500);
    }

    @Override
    public void saveToData(PacketByteBuf buf) {
        buf.writeString(race, 500);
    }

    @Override
    public Identifier getIdentifier() {
        return new Identifier(Ref.MODID, "choose_race");
    }

    public static Identifier getId() {
        return new Identifier(Ref.MODID, "choose_race");
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public ChooseRacePacket newInstance() {
        return new ChooseRacePacket();
    }

    @Override
    public void onReceive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
            PacketSender responseSender) {

        if (!Load.Unit(player)
            .hasRace()) {
            if (Database.Races()
                .isRegistered(race)) {
                Load.Unit(player)
                    .setRace(race);
            }
        }
    }
    
}
