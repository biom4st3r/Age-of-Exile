package com.robertx22.age_of_exile.aoe_data.database.unique_gears.registrators.jewelry;

import com.robertx22.age_of_exile.aoe_data.database.base_gear_types.adders.BaseGearJewelry;
import com.robertx22.age_of_exile.aoe_data.database.unique_gears.UniqueGearBuilder;
import com.robertx22.age_of_exile.database.data.StatModifier;
import com.robertx22.age_of_exile.database.data.stats.types.generated.ElementalResist;
import com.robertx22.age_of_exile.database.data.stats.types.generated.ElementalSpellDamage;
import com.robertx22.age_of_exile.database.data.stats.types.loot.TreasureQuality;
import com.robertx22.age_of_exile.database.data.stats.types.loot.TreasureQuantity;
import com.robertx22.age_of_exile.database.data.stats.types.offense.DayDamage;
import com.robertx22.age_of_exile.database.data.stats.types.resources.RegeneratePercentStat;
import com.robertx22.age_of_exile.database.data.stats.types.resources.health.Health;
import com.robertx22.age_of_exile.database.data.stats.types.resources.health.HealthRegen;
import com.robertx22.age_of_exile.database.data.stats.types.resources.mana.Mana;
import com.robertx22.age_of_exile.database.data.stats.types.resources.mana.ManaRegen;
import com.robertx22.age_of_exile.database.registrators.LevelRanges;
import com.robertx22.age_of_exile.database.registry.ISlashRegistryInit;
import com.robertx22.age_of_exile.mmorpg.ModRegistry;
import com.robertx22.age_of_exile.saveclasses.gearitem.gear_bases.StatRequirement;
import com.robertx22.age_of_exile.uncommon.enumclasses.Elements;
import com.robertx22.age_of_exile.uncommon.enumclasses.ModType;

import java.util.Arrays;

public class UniqueRings implements ISlashRegistryInit {
    @Override
    public void registerAll() {

        UniqueGearBuilder.of(
            ModRegistry.UNIQUE_GEARS.GREED_PERSIST_RING,
            "greed_persist",
            "Greed's Persistence",
            "When desire for perfection overtakes your sanity, you too will be blessed.",
            BaseGearJewelry.THUNDER_RES_RING.get(LevelRanges.MID_TO_END))
            .stats(Arrays.asList(
                new StatModifier(-30, 25, TreasureQuantity.getInstance(), ModType.FLAT),
                new StatModifier(-30, 25, TreasureQuality.getInstance(), ModType.FLAT),
                new StatModifier(5, 10, new ElementalResist(Elements.Elemental), ModType.FLAT),
                new StatModifier(-10, -5, Health.getInstance(), ModType.FLAT)

            ))
            .req(new StatRequirement().setInt(0.5F)
                .setAgi(0.75F))
            .build();

        UniqueGearBuilder.of(
            ModRegistry.UNIQUE_GEARS.LOOP_OF_INFINITY_RING,
            "loop_of_infinity",
            "Loop of Infinity",
            "Is it truly an end if everything just starts all over again? Maybe it really is just a loop.",
            BaseGearJewelry.RING_MANA_REG.get(LevelRanges.MID_TO_END))
            .stats(Arrays.asList(
                new StatModifier(5, 10, Mana.getInstance(), ModType.FLAT),
                new StatModifier(1, 4, RegeneratePercentStat.MANA, ModType.FLAT)
            ))
            .req(new StatRequirement().setWis(0.5F)
                .setInt(0.75F))
            .build();

        UniqueGearBuilder.of(
            ModRegistry.UNIQUE_GEARS.SOLAR_RING,
            "solar_ring",
            "Solaris",
            "Mirror, mirror on the wall, who is the brightest of them all?",
            BaseGearJewelry.FIRE_RES_RING.get(LevelRanges.START_TO_LOW))
            .stats(Arrays.asList(
                new StatModifier(10, 20, new ElementalSpellDamage(Elements.Fire), ModType.FLAT),
                new StatModifier(10, 20, DayDamage.getInstance(), ModType.FLAT)
            ))
            .req(new StatRequirement().setWis(0.5F)
                .setAgi(0.75F))
            .build();

        UniqueGearBuilder.of(
            ModRegistry.UNIQUE_GEARS.LUNAR_RING,
            "lunar_ring",
            "Lunaris",
            "There is no moonless night.",
            BaseGearJewelry.COLD_RES_RING.get(LevelRanges.START_TO_LOW))
            .stats(Arrays.asList(
                new StatModifier(10, 20, new ElementalSpellDamage(Elements.Water), ModType.FLAT),
                new StatModifier(10, 20, DayDamage.getInstance(), ModType.FLAT)
            ))
            .req(new StatRequirement().setDex(0.5F)
                .setAgi(0.75F))
            .build();

        UniqueGearBuilder.of(
            ModRegistry.UNIQUE_GEARS.TOUCH_OF_ETERNITY_RING,
            "touch_of_eternity",
            "Touch of Eternity",
            "Worn by Kings, Beggars, Barbarians, Queens, Prophets and now ... you.",
            BaseGearJewelry.RING_MANA_REG.get(LevelRanges.START_TO_LOW))
            .stats(Arrays.asList(
                new StatModifier(0.5F, 2, HealthRegen.getInstance(), ModType.FLAT),
                new StatModifier(0.5F, 2, ManaRegen.getInstance(), ModType.FLAT)
            ))
            .req(new StatRequirement().setWis(0.5F)
                .setVit(0.5F))
            .build();

    }
}
