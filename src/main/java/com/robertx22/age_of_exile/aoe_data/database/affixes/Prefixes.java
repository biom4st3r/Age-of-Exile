package com.robertx22.age_of_exile.aoe_data.database.affixes;

import com.robertx22.age_of_exile.aoe_data.database.affixes.adders.ArmorPrefixes;
import com.robertx22.age_of_exile.aoe_data.database.affixes.adders.EvasionPrefixes;
import com.robertx22.age_of_exile.aoe_data.database.affixes.adders.MagicShieldPrefixes;
import com.robertx22.age_of_exile.aoe_data.database.affixes.adders.WeaponPrefixes;
import com.robertx22.age_of_exile.aoe_data.database.affixes.adders.jewelry.JewelryPrefixes;
import com.robertx22.age_of_exile.database.base.IRandomDefault;
import com.robertx22.age_of_exile.database.data.affixes.Affix;
import com.robertx22.age_of_exile.database.registry.Database;
import com.robertx22.age_of_exile.database.registry.ISlashRegistryInit;

import java.util.List;

public class Prefixes implements IRandomDefault<Affix>, ISlashRegistryInit {

    @Override
    public void registerAll() {

        new WeaponPrefixes().registerAll();
        new ArmorPrefixes().registerAll();
        new JewelryPrefixes().registerAll();
        new EvasionPrefixes().registerAll();
        new MagicShieldPrefixes().registerAll();

    }

    public static final Prefixes INSTANCE = new Prefixes();

    @Override
    public List<Affix> All() {
        return Database.Affixes()
            .getWrapped()
            .ofAffixType(Affix.Type.prefix).list;
    }

}
