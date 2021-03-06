package com.robertx22.age_of_exile.player_skills.items.tinkering;

import com.robertx22.age_of_exile.database.base.CreativeTabs;
import com.robertx22.age_of_exile.mmorpg.ModRegistry;
import com.robertx22.age_of_exile.player_skills.items.TieredItem;
import com.robertx22.age_of_exile.player_skills.items.foods.SkillItemTier;
import com.robertx22.age_of_exile.player_skills.recipe_types.StationShapelessFactory;
import com.robertx22.age_of_exile.player_skills.recipe_types.base.IStationRecipe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

public class ChestKeyItem extends TieredItem implements IStationRecipe {

    public ChestKeyItem(SkillItemTier tier) {
        super(tier, new Settings().group(CreativeTabs.Professions));

    }

    @Override
    public String locNameForLangFile() {
        return tier.word + " Chest Key";
    }

    @Override
    public String GUID() {
        return "key/" + tier.tier;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {

        try {

            tooltip.add(new LiteralText("Opens locked chest items."));
            tooltip.add(new LiteralText("Needs to be in inventory."));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public StationShapelessFactory getStationRecipe() {
        StationShapelessFactory fac = StationShapelessFactory.create(ModRegistry.RECIPE_SER.SMITHING, this, 1);
        fac.input(ModRegistry.TIERED.STONE_TIER_MAP.get(this.tier), 2);
        fac.input(Items.IRON_INGOT, 1);
        return fac.criterion("player_level", trigger());
    }
}

