package com.robertx22.age_of_exile.database.data.spells.components;

import com.robertx22.age_of_exile.database.data.StatModifier;
import com.robertx22.age_of_exile.saveclasses.ExactStatData;
import com.robertx22.age_of_exile.uncommon.utilityclasses.LevelUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuraSpellData {

    public float mana_reserved = 0.25F;

    public List<StatModifier> stats = new ArrayList<>();

    public AuraSpellData() {
    }

    public AuraSpellData(float mana_reserved, List<StatModifier> stats) {
        this.mana_reserved = mana_reserved;
        this.stats = stats;
    }

    public List<ExactStatData> getStats(int lvl) {
        int perc = (int) (LevelUtils.getMaxLevelMultiplier(lvl) * 100);
        return stats.stream()
            .map(x -> x.ToExactStat(100, perc))
            .collect(Collectors.toList());

    }

}