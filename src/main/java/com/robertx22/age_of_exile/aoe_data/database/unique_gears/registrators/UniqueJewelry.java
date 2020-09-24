package com.robertx22.age_of_exile.aoe_data.database.unique_gears.registrators;

import com.robertx22.age_of_exile.aoe_data.database.unique_gears.registrators.jewelry.UniqueNecklaces;
import com.robertx22.age_of_exile.aoe_data.database.unique_gears.registrators.jewelry.UniqueRings;
import com.robertx22.age_of_exile.database.registry.ISlashRegistryInit;

public class UniqueJewelry implements ISlashRegistryInit {

    @Override
    public void registerAll() {
        new UniqueNecklaces().registerAll();
        new UniqueRings().registerAll();
    }

}

