package com.robertx22.age_of_exile.mmorpg.registers.client;

import com.robertx22.age_of_exile.gui.screens.skill_gems.SkillGemsScreen;
import com.robertx22.age_of_exile.mmorpg.ModRegistry;
import com.robertx22.age_of_exile.player_skills.items.backpacks.BackpackScreen;
import com.robertx22.age_of_exile.vanilla_mc.blocks.alchemy.AlchemyContainer;
import com.robertx22.age_of_exile.vanilla_mc.blocks.alchemy.AlchemyScreen;
import com.robertx22.age_of_exile.vanilla_mc.blocks.buff_station.ScribeBuffContainer;
import com.robertx22.age_of_exile.vanilla_mc.blocks.buff_station.ScribeBuffScreen;
import com.robertx22.age_of_exile.vanilla_mc.blocks.cooking_station.CookingContainer;
import com.robertx22.age_of_exile.vanilla_mc.blocks.cooking_station.CookingScreen;
import com.robertx22.age_of_exile.vanilla_mc.blocks.item_modify_station.ContainerGearModify;
import com.robertx22.age_of_exile.vanilla_mc.blocks.item_modify_station.GuiGearModify;
import com.robertx22.age_of_exile.vanilla_mc.blocks.repair_station.ContainerGearRepair;
import com.robertx22.age_of_exile.vanilla_mc.blocks.repair_station.GuiGearRepair;
import com.robertx22.age_of_exile.vanilla_mc.blocks.salvage_station.ContainerGearSalvage;
import com.robertx22.age_of_exile.vanilla_mc.blocks.salvage_station.GuiGearSalvage;
import com.robertx22.age_of_exile.vanilla_mc.blocks.smithing.SmithingContainer;
import com.robertx22.age_of_exile.vanilla_mc.blocks.smithing.SmithingScreen;
import com.robertx22.age_of_exile.vanilla_mc.blocks.socket_station.SocketStationContainer;
import com.robertx22.age_of_exile.vanilla_mc.blocks.socket_station.SocketStationGui;
import com.robertx22.age_of_exile.vanilla_mc.blocks.tablet.TabletStationContainer;
import com.robertx22.age_of_exile.vanilla_mc.blocks.tablet.TabletStationScreen;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;

public class ContainerGuiRegisters {

    @SuppressWarnings("deprecation")
    public static void reg() {

        ScreenProviderRegistry.INSTANCE.<ContainerGearModify>registerFactory(ModRegistry.CONTAINERS.GEAR_MODIFY, x -> new GuiGearModify(x, MinecraftClient.getInstance().player.inventory, new LiteralText("")));
        ScreenProviderRegistry.INSTANCE.<ContainerGearRepair>registerFactory(ModRegistry.CONTAINERS.GEAR_REPAIR, x -> new GuiGearRepair(x, MinecraftClient.getInstance().player.inventory, new LiteralText("")));
        ScreenProviderRegistry.INSTANCE.<ContainerGearSalvage>registerFactory(ModRegistry.CONTAINERS.GEAR_SALVAGE, x -> new GuiGearSalvage(x, MinecraftClient.getInstance().player.inventory, new LiteralText("")));
        ScreenProviderRegistry.INSTANCE.<SocketStationContainer>registerFactory(ModRegistry.CONTAINERS.GEAR_SOCKET, x -> new SocketStationGui(x, MinecraftClient.getInstance().player.inventory, new LiteralText("")));
        ScreenProviderRegistry.INSTANCE.<ScribeBuffContainer>registerFactory(ModRegistry.CONTAINERS.SCRIBE_BUFF, x -> new ScribeBuffScreen(x, MinecraftClient.getInstance().player.inventory, new LiteralText("")));
        ScreenProviderRegistry.INSTANCE.<CookingContainer>registerFactory(ModRegistry.CONTAINERS.COOKING_STATION, x -> new CookingScreen(x, MinecraftClient.getInstance().player.inventory, new LiteralText("")));
        ScreenProviderRegistry.INSTANCE.<TabletStationContainer>registerFactory(ModRegistry.CONTAINERS.TABLET_STATION, x -> new TabletStationScreen(x, MinecraftClient.getInstance().player.inventory, new LiteralText("")));
        ScreenProviderRegistry.INSTANCE.<AlchemyContainer>registerFactory(ModRegistry.CONTAINERS.ALCHEMY_STATION, x -> new AlchemyScreen(x, MinecraftClient.getInstance().player.inventory, new LiteralText("")));
        ScreenProviderRegistry.INSTANCE.<SmithingContainer>registerFactory(ModRegistry.CONTAINERS.SMITHING_STATION, x -> new SmithingScreen(x, MinecraftClient.getInstance().player.inventory, new LiteralText("")));

        ScreenRegistry.register(ModRegistry.CONTAINERS.BACKPACK_TYPE, BackpackScreen::new);
        ScreenRegistry.register(ModRegistry.CONTAINERS.SKILL_GEMS_TYPE, SkillGemsScreen::new);

    }

}
