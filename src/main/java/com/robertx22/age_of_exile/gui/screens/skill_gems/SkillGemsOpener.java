package com.robertx22.age_of_exile.gui.screens.skill_gems;

import com.robertx22.age_of_exile.gui.bases.IContainerNamedScreen;
import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.age_of_exile.uncommon.localization.Words;
import com.robertx22.age_of_exile.vanilla_mc.packets.OpenGuiPacketv2;

import net.minecraft.util.Identifier;

public class SkillGemsOpener implements IContainerNamedScreen {

    @Override
    public void openContainer() {

        new OpenGuiPacketv2(OpenGuiPacketv2.GuiType.SKILL_GEMS).sendToServer();
    }

    @Override
    public Identifier iconLocation() {
        return new Identifier(Ref.MODID, "textures/gui/main_hub/icons/skill_tree.png");
    }

    @Override
    public Words screenName() {
        return Words.Spells;
    }
}
