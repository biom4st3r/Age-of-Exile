package com.robertx22.age_of_exile.database.data.stats.name_regex;

import com.robertx22.age_of_exile.database.data.stats.Stat;
import com.robertx22.age_of_exile.uncommon.enumclasses.ModType;
import net.minecraft.util.Formatting;

public class BasicLocalStatRegex extends StatNameRegex {

    @Override
    public Formatting statColor(Stat stat) {
        return stat.textFormat;
    }

    @Override
    public Formatting numberColor(Stat stat) {
        return stat.textFormat;
    }

    @Override
    public String getStatNameRegex(ModType type, Stat stat, float v1, float v2) {

        if (stat.UsesSecondValue()) {
            return NAME + ": " + MIN_VALUE + "-" + MAX_VALUE;
        } else {
            return NAME + ": " + VALUE;
        }

    }
}
