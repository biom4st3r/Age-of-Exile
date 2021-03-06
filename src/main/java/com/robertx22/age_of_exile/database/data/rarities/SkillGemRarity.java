package com.robertx22.age_of_exile.database.data.rarities;

import com.robertx22.age_of_exile.database.data.IAutoGson;
import com.robertx22.age_of_exile.database.data.MinMax;
import com.robertx22.age_of_exile.database.registry.SlashRegistryType;
import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.age_of_exile.saveclasses.gearitem.gear_bases.Rarity;

public class SkillGemRarity extends BaseRarity implements Rarity, IAutoGson<SkillGemRarity> {

    public static SkillGemRarity SERIALIZER = new SkillGemRarity();

    public MinMax stat_percents = new MinMax(0, 100);

    public SkillGemRarity() {
        super(RarityType.SKILL_GEM);
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.SKILL_GEM_RARITY;
    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".skill_gem_rarity." + formattedGUID();
    }

    @Override
    public Class<SkillGemRarity> getClassForSerialization() {
        return SkillGemRarity.class;
    }
}
