package com.robertx22.age_of_exile.vanilla_mc.packets;

import com.robertx22.age_of_exile.gui.screens.skill_gems.SkillGemsContainer;
import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.age_of_exile.uncommon.datasaving.Load;
import com.robertx22.age_of_exile.vanilla_mc.packets.proxies.OpenGuiWrapper;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class OpenGuiPacketv2 implements ServerPacketConsumer {

    public static OpenGuiPacketv2 EMPTY = new OpenGuiPacketv2();

    public enum GuiType {
        TALENTS,
        PICK_STATS,
        SKILL_GEMS,
        SPELLS,
        MAIN_HUB
    }

    GuiType type;

    public OpenGuiPacketv2() {

    }

    public OpenGuiPacketv2(GuiType type) {
        this.type = type;
    }

    @Override
    public void loadFromData(PacketByteBuf buf) {
        type = GuiType.valueOf(buf.readString(44));
    }

    @Override
    public void saveToData(PacketByteBuf buf) {
        buf.writeString(type.name(), 44);
    }

    @Override
    public Identifier getIdentifier() {
        return new Identifier(Ref.MODID, "opengui");
    }
    public static Identifier getId() {
        return new Identifier(Ref.MODID, "opengui");
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public OpenGuiPacketv2 newInstance() {
        return new OpenGuiPacketv2();
    }

    @Override
    public void onReceive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
            PacketSender responseSender) {
        if (type == GuiType.MAIN_HUB) {
            OpenGuiWrapper.openMainHub();
        }
        if (type == GuiType.SKILL_GEMS) {
            player
                .openHandledScreen(new ExtendedScreenHandlerFactory() {
                    @Override
                    public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {

                    }

                    @Override
                    public Text getDisplayName() {
                        return new LiteralText("");
                    }

                    @Override
                    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
                        return new SkillGemsContainer(syncId, inv, Load.spells(player)
                            .getSkillGemData());
                    }
                });
        }
        
    }
    
}
