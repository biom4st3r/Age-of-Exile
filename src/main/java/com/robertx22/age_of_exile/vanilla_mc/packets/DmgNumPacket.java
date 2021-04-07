package com.robertx22.age_of_exile.vanilla_mc.packets;

import com.robertx22.age_of_exile.a_libraries.dmg_number_particle.DamageParticleAdder;
import com.robertx22.age_of_exile.config.forge.ModConfig;
import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.age_of_exile.uncommon.enumclasses.Elements;
import com.robertx22.age_of_exile.uncommon.utilityclasses.ClientOnly;
import com.robertx22.age_of_exile.uncommon.wrappers.SText;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class DmgNumPacket implements ServerToClientPacket {

    public String element;
    public String string;

    public boolean isExp;
    public float number;
    public int id;

    public DmgNumPacket() {

    }

    public DmgNumPacket(LivingEntity entity, Elements ele, String str, float number) {

        element = ele.toString();
        string = str;
        this.number = number;
        this.id = entity.getEntityId();

    }

    @Override
    public Identifier getIdentifier() {
        return new Identifier(Ref.MODID, "dmgnum");
    }

    @Override
    public void loadFromData(PacketByteBuf tag) {
        element = tag.readString();
        isExp = tag.readBoolean();
        string = tag.readString();
        number = tag.readFloat();
        id = tag.readInt();

    }

    @Override
    public void saveToData(PacketByteBuf tag) {
        tag.writeString(element);
        tag.writeBoolean(isExp);
        tag.writeString(string);
        tag.writeFloat(number);
        tag.writeInt(id);

    }

    @Override
    @Environment(EnvType.CLIENT)
    public void onReceive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketSender responseSender) {
        if (isExp && ModConfig.get().client.dmgParticleConfig.ENABLE_CHAT_EXP_MSG) {
            ClientOnly.getPlayer()
                .sendMessage(new SText(Formatting.GREEN + "" + Formatting.BOLD + "+" + (int) number + " EXP"), false);

        } else if (isExp == false && ModConfig.get().client.dmgParticleConfig.ENABLE_FLOATING_DMG) {
            DamageParticleAdder.displayParticle(client.player.world.getEntityById(id), element, string);
        }
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public DmgNumPacket newInstance() {
        return new DmgNumPacket();
    }
}
