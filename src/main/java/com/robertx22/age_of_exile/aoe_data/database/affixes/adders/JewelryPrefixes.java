package com.robertx22.age_of_exile.aoe_data.database.affixes.adders;

import com.robertx22.age_of_exile.aoe_data.database.affixes.AffixBuilder;
import com.robertx22.age_of_exile.aoe_data.database.affixes.ElementalAffixBuilder;
import com.robertx22.age_of_exile.database.data.StatModifier;
import com.robertx22.age_of_exile.database.data.gear_types.bases.BaseGearType.SlotTag;
import com.robertx22.age_of_exile.database.data.stats.types.generated.AttackDamage;
import com.robertx22.age_of_exile.database.data.stats.types.reduced_req.ReducedAllStatReqOnItem;
import com.robertx22.age_of_exile.database.data.stats.types.resources.health.HealthRegen;
import com.robertx22.age_of_exile.database.data.stats.types.resources.magic_shield.MagicShieldRegen;
import com.robertx22.age_of_exile.database.registry.ISlashRegistryInit;
import com.robertx22.age_of_exile.uncommon.enumclasses.Elements;
import com.robertx22.age_of_exile.uncommon.enumclasses.ModType;

import java.util.Arrays;

public class JewelryPrefixes implements ISlashRegistryInit {
    @Override
    public void registerAll() {

        ElementalAffixBuilder.start()
            .guid(x -> x.guidName + "_wep_dmg_jewelry")
            .add(Elements.Fire, "Smoking")
            .add(Elements.Water, "Freezing")
            .add(Elements.Thunder, "Shocking")
            .add(Elements.Nature, "Dripping")
            .tier(1, x -> Arrays.asList(new StatModifier(1, 2, 1, 2, new AttackDamage(x), ModType.FLAT)))
            .tier(2, x -> Arrays.asList(new StatModifier(0.75F, 1, 0.75F, 1, new AttackDamage(x), ModType.FLAT)))
            .tier(3, x -> Arrays.asList(new StatModifier(0.5F, 0.75F, 0.5F, 0.75F, new AttackDamage(x), ModType.FLAT)))
            .includesTags(SlotTag.jewelry_family)
            .Weight(100)
            .Prefix()
            .Build();

        AffixBuilder.Normal("of_the_troll")
            .Named("Of The Troll")
            .tier(1, new StatModifier(1, 2, HealthRegen.getInstance(), ModType.FLAT))
            .tier(2, new StatModifier(0.5F, 1, HealthRegen.getInstance(), ModType.FLAT))
            .tier(3, new StatModifier(0.3F, 0.5F, HealthRegen.getInstance(), ModType.FLAT))
            .includesTags(SlotTag.jewelry_family, SlotTag.plate)
            .Weight(200)
            .Suffix()
            .Build();

        AffixBuilder.Normal("of_spirit_markings")
            .Named("Of Spirit Markings")
            .tier(1, new StatModifier(1, 2, MagicShieldRegen.getInstance(), ModType.FLAT))
            .tier(2, new StatModifier(0.5F, 1, MagicShieldRegen.getInstance(), ModType.FLAT))
            .tier(3, new StatModifier(0.3F, 0.5F, MagicShieldRegen.getInstance(), ModType.FLAT))
            .includesTags(SlotTag.jewelry_family, SlotTag.cloth)
            .Weight(200)
            .Suffix()
            .Build();

        AffixBuilder.Normal("of_ease")
            .Named("Of Ease")
            .tier(1, new StatModifier(20, 30, new ReducedAllStatReqOnItem(), ModType.FLAT))
            .tier(2, new StatModifier(15, 20, new ReducedAllStatReqOnItem(), ModType.FLAT))
            .tier(3, new StatModifier(10, 15, new ReducedAllStatReqOnItem(), ModType.FLAT))
            .includesTags(SlotTag.intelligence, SlotTag.dexterity, SlotTag.strength)
            .Weight(200)
            .Suffix()
            .Build();

    }
}
