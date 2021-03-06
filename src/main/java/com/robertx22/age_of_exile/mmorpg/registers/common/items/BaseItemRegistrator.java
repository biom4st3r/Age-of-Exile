package com.robertx22.age_of_exile.mmorpg.registers.common.items;

import com.robertx22.age_of_exile.database.data.IGUID;
import com.robertx22.age_of_exile.database.data.gear_types.bases.BaseGearType;
import com.robertx22.age_of_exile.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Locale;

public abstract class BaseItemRegistrator {

    <T extends Item & IGUID> T item(T c) {
        Registry.register(Registry.ITEM, new Identifier(Ref.MODID, c.GUID()), c);
        return c;

    }

    <T extends Item> T item(T c, String id) {
        Registry.register(Registry.ITEM, new Identifier(Ref.MODID, id), c);
        return c;
    }

    Item gearType(Item c, BaseGearType slot) {

        if (slot.getTags()
            .contains(BaseGearType.SlotTag.shield) || slot.getTags()
            .contains(BaseGearType.SlotTag.bow) || slot.getTags()
            .contains(BaseGearType.SlotTag.crossbow)) {
            // TODO , this is for shields and other items i'm temporarily using vanilla items for.
            return c;
        }

        String type = slot.getSlotType()
            .name()
            .toLowerCase(Locale.ROOT);

        String armortype = "";

        if (slot.getTags()
            .contains(BaseGearType.SlotTag.leather)) {
            armortype = "leather/";
        }
        if (slot.getTags()
            .contains(BaseGearType.SlotTag.plate)) {
            armortype = "plate/";
        }
        if (slot.getTags()
            .contains(BaseGearType.SlotTag.cloth)) {
            armortype = "cloth/";
        }

        Identifier id = new Identifier(Ref.MODID, slot.family()
            .name()
            .toLowerCase(Locale.ROOT) + "/" + armortype + type + "/" + slot.GUID());

        Registry.register(Registry.ITEM, id, c);

        return c;

    }

}
