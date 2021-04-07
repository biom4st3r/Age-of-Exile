package com.robertx22.age_of_exile.vanilla_mc.packets;

import com.robertx22.age_of_exile.database.registry.Database;
import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.age_of_exile.mmorpg.SyncedToClientValues;
import com.robertx22.age_of_exile.saveclasses.player_skills.PlayerSkillEnum;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class SkillLevelUpToClient implements ServerToClientPacket {

    public String skill;

    public SkillLevelUpToClient() {

    }

    public SkillLevelUpToClient(PlayerSkillEnum skill) {
        this.skill = skill.id;
    }

    @Override
    public Identifier getIdentifier() {
        return new Identifier(Ref.MODID, "skill_lvl");
    }

    @Override
    public void loadFromData(PacketByteBuf tag) {
        skill = tag.readString();
    }

    @Override
    public void saveToData(PacketByteBuf tag) {
        tag.writeString(skill, 500);
    }

    @Override
    public void onReceive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketSender responseSender) {        try {
            SyncedToClientValues.skillJustLeveled = Database.PlayerSkills()
                .get(skill).type_enum;
            SyncedToClientValues.ticksToShowSkillLvled = 120;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    @SuppressWarnings({"unchecked"})
    public SkillLevelUpToClient newInstance() {
        return new SkillLevelUpToClient();
    }
}

