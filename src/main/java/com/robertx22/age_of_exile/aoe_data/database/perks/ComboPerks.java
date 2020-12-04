package com.robertx22.age_of_exile.aoe_data.database.perks;

import com.robertx22.age_of_exile.database.OptScaleExactStat;
import com.robertx22.age_of_exile.database.data.stats.types.defense.Armor;
import com.robertx22.age_of_exile.database.data.stats.types.defense.DodgeRating;
import com.robertx22.age_of_exile.database.data.stats.types.generated.AttackDamage;
import com.robertx22.age_of_exile.database.data.stats.types.generated.ElementalResist;
import com.robertx22.age_of_exile.database.data.stats.types.offense.Accuracy;
import com.robertx22.age_of_exile.database.data.stats.types.offense.AttackSpeed;
import com.robertx22.age_of_exile.database.data.stats.types.offense.AttackStyleDamage;
import com.robertx22.age_of_exile.database.data.stats.types.offense.SpellDamage;
import com.robertx22.age_of_exile.database.data.stats.types.resources.health.Health;
import com.robertx22.age_of_exile.database.data.stats.types.resources.magic_shield.MagicShield;
import com.robertx22.age_of_exile.database.data.stats.types.resources.mana.Mana;
import com.robertx22.age_of_exile.database.data.stats.types.resources.mana.ManaRegen;
import com.robertx22.age_of_exile.database.data.stats.types.spell_calc.CastSpeed;
import com.robertx22.age_of_exile.database.data.stats.types.spell_calc.ManaCost;
import com.robertx22.age_of_exile.database.registry.ISlashRegistryInit;
import com.robertx22.age_of_exile.uncommon.enumclasses.Elements;
import com.robertx22.age_of_exile.uncommon.enumclasses.ModType;

public class ComboPerks implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        PerkBuilder.stat("hp_mana_cost",
            new OptScaleExactStat(-2, ManaCost.getInstance(), ModType.FLAT),
            new OptScaleExactStat(4, Health.getInstance(), ModType.LOCAL_INCREASE)
        );
        PerkBuilder.stat("atk_cast_speed",
            new OptScaleExactStat(2, AttackSpeed.getInstance(), ModType.FLAT),
            new OptScaleExactStat(2, CastSpeed.getInstance(), ModType.FLAT)
        );

        PerkBuilder.stat("hp_res",
            new OptScaleExactStat(5, Health.getInstance(), ModType.LOCAL_INCREASE),
            new OptScaleExactStat(2, new ElementalResist(Elements.Elemental), ModType.FLAT)
        );

        PerkBuilder.stat("spell_dmg_flat_mana",
            new OptScaleExactStat(3, SpellDamage.getInstance(), ModType.FLAT),
            new OptScaleExactStat(5, Mana.getInstance(), ModType.FLAT)
        );

        PerkBuilder.stat("mana_and_health",
            new OptScaleExactStat(3, Health.getInstance(), ModType.LOCAL_INCREASE),
            new OptScaleExactStat(5, Mana.getInstance(), ModType.LOCAL_INCREASE)
        );

        PerkBuilder.stat("mana_and_regen",
            new OptScaleExactStat(5, Mana.getInstance(), ModType.LOCAL_INCREASE),
            new OptScaleExactStat(3, ManaRegen.getInstance(), ModType.LOCAL_INCREASE)
        );

        PerkBuilder.stat("flat_ms_mana_regen",
            new OptScaleExactStat(10, MagicShield.getInstance(), ModType.FLAT),
            new OptScaleExactStat(5, ManaRegen.getInstance(), ModType.LOCAL_INCREASE)
        );

        PerkBuilder.stat("flat_health_mana",
            new OptScaleExactStat(10, Health.getInstance(), ModType.FLAT),
            new OptScaleExactStat(10, Mana.getInstance(), ModType.FLAT)
        );

        PerkBuilder.stat("flat_health_phys_dmg",
            new OptScaleExactStat(10, Health.getInstance(), ModType.FLAT),
            new OptScaleExactStat(2, new AttackDamage(Elements.Physical), ModType.FLAT)
        );
        PerkBuilder.stat("phys_acc",
            new OptScaleExactStat(3, Accuracy.getInstance(), ModType.LOCAL_INCREASE),
            new OptScaleExactStat(2, new AttackDamage(Elements.Physical), ModType.LOCAL_INCREASE)
        );
        PerkBuilder.stat("flat_mana_reg_melee_dmg",
            new OptScaleExactStat(1, ManaRegen.getInstance(), ModType.FLAT),
            new OptScaleExactStat(2, AttackStyleDamage.MELEE, ModType.FLAT)
        );

        PerkBuilder.stat("armor_and_ms",
            new OptScaleExactStat(5, Armor.getInstance(), ModType.LOCAL_INCREASE),
            new OptScaleExactStat(2, MagicShield.getInstance(), ModType.LOCAL_INCREASE)
        );
        PerkBuilder.stat("armor_and_dodge",
            new OptScaleExactStat(5, Armor.getInstance(), ModType.LOCAL_INCREASE),
            new OptScaleExactStat(5, DodgeRating.getInstance(), ModType.LOCAL_INCREASE)
        );
        PerkBuilder.stat("armor_and_health",
            new OptScaleExactStat(5, Armor.getInstance(), ModType.LOCAL_INCREASE),
            new OptScaleExactStat(4, Health.getInstance(), ModType.LOCAL_INCREASE)
        );

        PerkBuilder.stat("dodge_and_ms",
            new OptScaleExactStat(5, DodgeRating.getInstance(), ModType.LOCAL_INCREASE),
            new OptScaleExactStat(2, MagicShield.getInstance(), ModType.LOCAL_INCREASE)
        );

        PerkBuilder.stat("dodge_and_hp",
            new OptScaleExactStat(10, DodgeRating.getInstance(), ModType.LOCAL_INCREASE),
            new OptScaleExactStat(3, Health.getInstance(), ModType.LOCAL_INCREASE)
        );

    }
}
