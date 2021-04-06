package com.robertx22.age_of_exile.capability.player;

import com.robertx22.age_of_exile.capability.bases.ICommonPlayerCap;
import com.robertx22.age_of_exile.database.data.stats.types.professions.all.BonusSkillExp;
import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.age_of_exile.saveclasses.gearitem.gear_bases.IApplyableStats;
import com.robertx22.age_of_exile.saveclasses.player_skills.PlayerSkillData;
import com.robertx22.age_of_exile.saveclasses.player_skills.PlayerSkillEnum;
import com.robertx22.age_of_exile.saveclasses.player_skills.PlayerSkillsData;
import com.robertx22.age_of_exile.saveclasses.unit.stat_ctx.StatContext;
import com.robertx22.age_of_exile.uncommon.datasaving.Load;
import com.robertx22.age_of_exile.uncommon.utilityclasses.LevelUtils;
import com.robertx22.age_of_exile.vanilla_mc.packets.SkillLevelUpToClient;
import com.robertx22.age_of_exile.vanilla_mc.packets.sync_cap.PlayerCaps;
import com.robertx22.age_of_exile.vanilla_mc.packets.sync_cap.SyncCapabilityToClient;
import com.robertx22.library_of_exile.main.Packets;
import com.robertx22.library_of_exile.utils.LoadSave;
import com.robertx22.library_of_exile.utils.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class PlayerSkills implements ICommonPlayerCap, IApplyableStats {

    static Identifier LOW_LVL_TEX = Ref.id("textures/gui/skills/skill_level/low.png");
    static Identifier MID_LVL_TEX = Ref.id("textures/gui/skills/skill_level/mid.png");
    static Identifier HIGH_LVL_TEX = Ref.id("textures/gui/skills/skill_level/high.png");

    public static final Identifier RESOURCE = new Identifier(Ref.MODID, "player_skills");
    private static final String LOC = "data";

    public PlayerEntity player;

    PlayerSkillsData data = new PlayerSkillsData();

    public void addExp(PlayerSkillEnum skill, int exp) {

        exp *= Load.Unit(player)
            .getUnit()
            .getCalculatedStat(new BonusSkillExp(skill))
            .getMultiplier(); // bonus exp needs to happen here because otherwise it counts towards droprates

        PlayerSkillData sd = data.getDataFor(skill);

        if (sd.addExp(player, exp)) {
            onLevelUp(skill);
        }
    }

    public PlayerSkillData getDataFor(PlayerSkillEnum skill) {
        return this.data.getDataFor(skill);
    }

    public PlayerSkillData getDataFor(String id) {
        return this.data.getDataFor(id);
    }

    public Identifier getBackGroundTextureFor(PlayerSkillEnum se) {
        int lvl = getLevel(se);
        float multi = LevelUtils.getMaxLevelMultiplier(lvl);

        if (multi < 0.25F) {
            return LOW_LVL_TEX;
        } else if (multi < 0.75F) {
            return MID_LVL_TEX;
        }
        return HIGH_LVL_TEX;

    }

    public int getLevel(PlayerSkillEnum se) {
        return data.getDataFor(se)
            .getLvl();
    }

    public float getExpDividedByNeededToLevelMulti(PlayerSkillEnum skill) {

        int exp = this.getDataFor(skill)
            .getExp();
        int need = getDataFor(skill).getExpNeededToLevel();

        return MathHelper.clamp((float) exp / (float) need, 0F, 1F);
    }

    public void onLevelUp(PlayerSkillEnum skill) {

        new SyncCapabilityToClient(player, PlayerCaps.PLAYER_SKILLS).send(player);

        SoundUtils.ding(player.world, player.getBlockPos());

        player.sendMessage(skill.word.locName()
            .append(" leveled up!")
            .formatted(skill.format), false);

        new SkillLevelUpToClient(skill).send(player);
    }

    public PlayerSkills(PlayerEntity player) {
        this.player = player;
    }

    @Override
    public CompoundTag toTag(CompoundTag nbt) {
        LoadSave.Save(data, nbt, LOC);
        return nbt;
    }

    @Override
    public void fromTag(CompoundTag nbt) {
        this.data = LoadSave.Load(PlayerSkillsData.class, new PlayerSkillsData(), nbt, LOC);
    }

    @Override
    public PlayerCaps getCapType() {
        return PlayerCaps.PLAYER_SKILLS;
    }

    @Override
    public List<StatContext> getStatAndContext(LivingEntity en) {
        return data.getStatAndContext(en);
    }

}