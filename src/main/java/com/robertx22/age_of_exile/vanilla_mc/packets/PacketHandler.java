package com.robertx22.age_of_exile.vanilla_mc.packets;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public interface PacketHandler {
    void loadFromData(PacketByteBuf buf);
    void saveToData(PacketByteBuf buf);
    Identifier getIdentifier();
    <T extends PacketHandler> T newInstance();
}
