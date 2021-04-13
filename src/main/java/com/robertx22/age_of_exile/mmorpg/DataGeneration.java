package com.robertx22.age_of_exile.mmorpg;

import com.robertx22.age_of_exile.aoe_data.datapacks.curio_tags.GenerateCurioDataJsons;
import com.robertx22.age_of_exile.aoe_data.datapacks.generators.LootTableGenerator;
import com.robertx22.age_of_exile.aoe_data.datapacks.generators.RecipeGenerator;
import com.robertx22.age_of_exile.aoe_data.datapacks.lang_file.CreateLangFile;
import com.robertx22.age_of_exile.aoe_data.datapacks.models.ItemModelManager;
import com.robertx22.age_of_exile.aoe_data.datapacks.modpack_helper_lists.ModpackerHelperLists;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

public class DataGeneration {

    public static void generateAll() {

        new RecipeGenerator().run();
        new LootTableGenerator().run();

        ModpackerHelperLists.generate();

        if(FabricLoader.getInstance().getEnvironmentType() != EnvType.SERVER) {
            CreateLangFile.create();
        }
        GenerateCurioDataJsons.generate();
        ItemModelManager.INSTANCE.generateModels();

    }

}
