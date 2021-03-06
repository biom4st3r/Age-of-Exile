package com.robertx22.age_of_exile.player_skills.items.tinkering;

import com.robertx22.age_of_exile.database.base.CreativeTabs;
import com.robertx22.age_of_exile.database.data.currency.base.IShapelessRecipe;
import com.robertx22.age_of_exile.mmorpg.ModRegistry;
import com.robertx22.age_of_exile.player_skills.items.TieredItem;
import com.robertx22.age_of_exile.player_skills.items.backpacks.IGatheringMat;
import com.robertx22.age_of_exile.player_skills.items.foods.SkillItemTier;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory;
import net.minecraft.item.Items;

public class MysteriousLeatherItem extends TieredItem implements IGatheringMat, IShapelessRecipe {

    public MysteriousLeatherItem(SkillItemTier tier) {
        super(tier, new Settings().group(CreativeTabs.Professions));

    }

    @Override
    public String locNameForLangFile() {
        return tier.word + " Leather";
    }

    @Override
    public String GUID() {
        return "leather/" + tier.tier;
    }

    @Override
    public ShapelessRecipeJsonFactory getRecipe() {
        ShapelessRecipeJsonFactory fac = ShapelessRecipeJsonFactory.create(this, 1);
        fac.input(ModRegistry.TIERED.ESSENCE_INK.get(this.tier));
        fac.input(Items.LEATHER);
        return fac.criterion("player_level", trigger());
    }
}
