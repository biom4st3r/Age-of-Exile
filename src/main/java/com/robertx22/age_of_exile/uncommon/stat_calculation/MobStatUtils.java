package com.robertx22.age_of_exile.uncommon.stat_calculation;

import com.robertx22.age_of_exile.capability.entity.EntityCap.UnitData;
import com.robertx22.age_of_exile.config.forge.ModConfig;
import com.robertx22.age_of_exile.database.data.EntityConfig;
import com.robertx22.age_of_exile.database.data.rarities.MobRarity;
import com.robertx22.age_of_exile.database.data.stats.types.defense.Armor;
import com.robertx22.age_of_exile.database.data.stats.types.defense.DodgeRating;
import com.robertx22.age_of_exile.database.data.stats.types.defense.SpellDodge;
import com.robertx22.age_of_exile.database.data.stats.types.generated.ElementalResist;
import com.robertx22.age_of_exile.database.data.stats.types.offense.Accuracy;
import com.robertx22.age_of_exile.database.data.stats.types.offense.SpellDamage;
import com.robertx22.age_of_exile.database.data.stats.types.offense.TotalDamage;
import com.robertx22.age_of_exile.database.data.stats.types.offense.crit.CriticalHit;
import com.robertx22.age_of_exile.database.data.stats.types.resources.health.Health;
import com.robertx22.age_of_exile.database.registry.Database;
import com.robertx22.age_of_exile.saveclasses.ExactStatData;
import com.robertx22.age_of_exile.saveclasses.unit.InCalcStatData;
import com.robertx22.age_of_exile.saveclasses.unit.Unit;
import com.robertx22.age_of_exile.saveclasses.unit.stat_ctx.MiscStatCtx;
import com.robertx22.age_of_exile.saveclasses.unit.stat_ctx.StatContext;
import com.robertx22.age_of_exile.uncommon.datasaving.Load;
import com.robertx22.age_of_exile.uncommon.enumclasses.ModType;
import com.robertx22.library_of_exile.utils.EntityUtils;
import net.minecraft.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MobStatUtils {

    public static void increaseMobStatsPerTier(LivingEntity en, UnitData mobdata, Unit unit) {

        for (InCalcStatData data : unit.getStats().statsInCalc
            .values()
            .stream()
            .filter(x -> {
                return x.GetStat()
                    .IsPercent() == false;
            })
            .collect(Collectors.toList())) {

            data.multiplyFlat(mobdata.getMapTier().mob_stat_multi);
        }

    }

    public static List<StatContext> getAffixStats(LivingEntity en) {
        List<StatContext> list = new ArrayList<>();
        Load.Unit(en)
            .getAffixData()
            .getAffixes()
            .forEach(x -> list.addAll(x.getStatAndContext(en)));
        return list;

    }

    public static List<StatContext> getWorldMultiplierStats(LivingEntity en) {

        List<StatContext> list = new ArrayList<>();

        List<ExactStatData> stats = new ArrayList<>();

        float val = (1F - Database.getDimensionConfig(en.world).mob_strength_multi) * 100F;

        stats.add(ExactStatData.noScaling(val, val, ModType.GLOBAL_INCREASE, Health.getInstance()
            .GUID()));
        stats.add(ExactStatData.noScaling(val, val, ModType.GLOBAL_INCREASE, TotalDamage.getInstance()
            .GUID()));

        list.add(new MiscStatCtx(stats));

        return list;

    }

    public static List<StatContext> getMobConfigStats(LivingEntity entity, UnitData unitdata) {
        List<StatContext> list = new ArrayList<>();
        List<ExactStatData> stats = new ArrayList<>();

        EntityConfig config = Database.getEntityConfig(entity, unitdata);

        config.stats.stats.forEach(x -> stats.add(x.toExactStat(unitdata.getLevel())));

        float hp = (float) ((1F - config.hp_multi) * 100F);
        float dmg = (float) ((1F - config.dmg_multi) * 100F);
        float stat = (float) ((1F - config.stat_multi) * 100F);

        stats.add(ExactStatData.noScaling(hp, hp, ModType.GLOBAL_INCREASE, Health.getInstance()
            .GUID()));
        stats.add(ExactStatData.noScaling(dmg, dmg, ModType.FLAT, TotalDamage.getInstance()
            .GUID()));

        stats.add(ExactStatData.noScaling(stat, stat, ModType.GLOBAL_INCREASE, DodgeRating.getInstance()
            .GUID()));
        stats.add(ExactStatData.noScaling(stat, stat, ModType.GLOBAL_INCREASE, Armor.getInstance()
            .GUID()));
        stats.add(ExactStatData.noScaling(stat, stat, ModType.GLOBAL_INCREASE, Health.getInstance()
            .GUID()));

        list.add(new MiscStatCtx(stats));

        return list;
    }

    public static List<StatContext> getMobBaseStats(UnitData unitdata, LivingEntity en) {
        List<StatContext> list = new ArrayList<>();
        List<ExactStatData> stats = new ArrayList<>();

        MobRarity rar = Database.MobRarities()
            .get(unitdata.getRarity());
        int lvl = unitdata.getLevel();

        float hpToAdd = EntityUtils.getVanillaMaxHealth(en) * rar.ExtraHealthMulti();

        hpToAdd += (ModConfig.get().Server.EXTRA_MOB_STATS_PER_LEVEL * lvl) * hpToAdd;

        if (hpToAdd < 0) {
            hpToAdd = 0;
        }

        stats.add(ExactStatData.scaleTo(hpToAdd, ModType.FLAT, Health.getInstance()
            .GUID(), lvl));

        stats.add(ExactStatData.scaleTo(20, ModType.FLAT, DodgeRating.getInstance()
            .GUID(), lvl));
        stats.add(ExactStatData.scaleTo(9, ModType.FLAT, SpellDodge.getInstance()
            .GUID(), lvl));
        stats.add(ExactStatData.scaleTo(2, ModType.FLAT, Accuracy.getInstance()
            .GUID(), lvl));
        stats.add(ExactStatData.scaleTo(10 * rar.StatMultiplier(), ModType.FLAT, Armor.getInstance()
            .GUID(), lvl));

        stats.add(ExactStatData.scaleTo(5 * rar.DamageMultiplier(), ModType.FLAT, CriticalHit.getInstance()
            .GUID(), lvl));

        stats.add(ExactStatData.scaleTo(-25, ModType.FLAT, SpellDamage.getInstance()
            .GUID(), lvl));

        ElementalResist.MAP.getList()
            .forEach(x -> {
                stats.add(ExactStatData.scaleTo(5 * rar.StatMultiplier(), ModType.FLAT, x
                    .GUID(), lvl));
            });

        list.add(new MiscStatCtx(stats));

        return list;

    }

}
