package com.robertx22.age_of_exile.vanilla_mc.packets.perks;

import com.robertx22.age_of_exile.capability.player.EntityPerks;
import com.robertx22.age_of_exile.database.data.spell_schools.SpellSchool;
import com.robertx22.age_of_exile.database.registry.Database;
import com.robertx22.age_of_exile.mmorpg.MMORPG;
import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.age_of_exile.saveclasses.PointData;
import com.robertx22.age_of_exile.uncommon.datasaving.Load;
import com.robertx22.age_of_exile.vanilla_mc.packets.ClientToServerPacket;
import com.robertx22.age_of_exile.vanilla_mc.packets.sync_cap.PlayerCaps;
import com.robertx22.age_of_exile.vanilla_mc.packets.sync_cap.SyncCapabilityToClient;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class PerkChangePacket implements ClientToServerPacket {

    public String school;
    public int x;
    public int y;
    ACTION action;

    public enum ACTION {
        ALLOCATE, REMOVE
    }

    public PerkChangePacket() {

    }

    public PerkChangePacket(SpellSchool school, PointData point, ACTION action) {
        this.school = school.identifier;
        this.x = point.x;
        this.y = point.y;
        this.action = action;
    }

    @Override
    public void loadFromData(PacketByteBuf buf) {
        school = buf.readString(30);
        x = buf.readInt();
        y = buf.readInt();
        action = buf.readEnumConstant(ACTION.class);

    }

    @Override
    public void saveToData(PacketByteBuf buf) {
        buf.writeString(school, 30);
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeEnumConstant(action);
    }

    @Override
    public Identifier getIdentifier() {
        return new Identifier(Ref.MODID, "perk_change");
    }
    public static Identifier getId() {
        return new Identifier(Ref.MODID, "perk_change");
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public PerkChangePacket newInstance() {
        return new PerkChangePacket();
    }

    @Override
    public void onReceive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
            PacketSender responseSender) {
        EntityPerks perks = Load.perks(player);
        SpellSchool sc = Database.SpellSchools()
                .get(school);

        if (sc == null) {
            MMORPG.logError("school is null: " + this.school);
            return;
        }

        PointData point = new PointData(x, y);
        if (action == ACTION.ALLOCATE) {
            if (perks.data.canAllocate(sc, point, Load.Unit(player), player)) {
                perks.data.allocate(player, sc, new PointData(x, y));
            }
        } else if (action == ACTION.REMOVE) {
            if (perks.data.canRemove(sc, point)) {
                perks.data.remove(sc, new PointData(x, y));
                perks.data.reset_points--;
            }
        }

        Load.Unit(player)
                .setEquipsChanged(true);
        Load.Unit(player)
                .tryRecalculateStats();

        new SyncCapabilityToClient(player, PlayerCaps.ENTITY_PERKS).sendToClient(player);

        
    }
    
}
