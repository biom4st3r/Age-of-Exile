package com.robertx22.age_of_exile.mmorpg.registers.common;

import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.age_of_exile.player_skills.recipe_types.recipe_types.*;
import com.robertx22.age_of_exile.repair_kits.FillRepairKitRecipe;
import com.robertx22.age_of_exile.repair_kits.RepairWithKitRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModRecipeSerializers {

    public SpecialRecipeSerializer<FillRepairKitRecipe> FILL_REPAIR_KIT = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Ref.MODID, "repair_gear"), new SpecialRecipeSerializer<FillRepairKitRecipe>(t -> new FillRepairKitRecipe(t)));
    public SpecialRecipeSerializer<RepairWithKitRecipe> REPAIR_WITH_KIT = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Ref.MODID, "repair_with_kit"), new SpecialRecipeSerializer<RepairWithKitRecipe>(t -> new RepairWithKitRecipe(t)));

    public RecipeSerializer<FoodShapeless> FOOD = register("food_shapeless", new FoodSerializer());
    public RecipeSerializer<TabletShapeless> TABLET = register("tablet_shapeless", new TabletSerializer());
    public RecipeSerializer<AlchemyShapeless> ALCHEMY = register("alchemy_shapeless", new AlchemySerializer());
    public RecipeSerializer<SmithingShapeless> SMITHING = register("smithing_shapeless", new SmithingSerializer());

    static <S extends RecipeSerializer<T>, T extends Recipe<?>> S register(String id, S serializer) {
        return Registry.register(Registry.RECIPE_SERIALIZER, Ref.id(id), serializer);
    }
}
