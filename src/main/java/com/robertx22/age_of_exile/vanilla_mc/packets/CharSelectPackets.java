package com.robertx22.age_of_exile.vanilla_mc.packets;

import com.robertx22.age_of_exile.capability.player.data.OnePlayerCharData;
import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.age_of_exile.uncommon.datasaving.Load;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;

public class CharSelectPackets implements ClientToServerPacket {

    public enum Action {DELETE, LOAD}

    public int num;
    public Action action;

    public CharSelectPackets() {

    }

    public CharSelectPackets(int charNumber, Action act) {
        this.num = charNumber;
        this.action = act;

    }

    @Override
    public void loadFromData(PacketByteBuf buf) {
        num = buf.readInt();
        action = buf.readEnumConstant(Action.class);
    }

    @Override
    public void saveToData(PacketByteBuf buf) {
        buf.writeInt(num);
        buf.writeEnumConstant(action);
    }

    @Override
    public Identifier getIdentifier() {
        return new Identifier(Ref.MODID, "char_select");
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public CharSelectPackets newInstance() {
        return new CharSelectPackets();
    }

    @Override
    public void onReceive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
            PacketSender responseSender) {

        if (action == Action.DELETE) {
            OnePlayerCharData data = Load.characters(player).data.characters.get(num);
            if (data.gearIsEmpty()) {
                Load.characters(player).data.characters.remove(num);
            } else {
                player
                    .sendMessage(new LiteralText("You can't delete a character that is wearing gear."), false);
            }
        } else if (action == Action.LOAD) {
            Load.characters(player).data.load(num, player);
        }
    }

    public static Identifier getId() {
        return null;
    }
    
}
