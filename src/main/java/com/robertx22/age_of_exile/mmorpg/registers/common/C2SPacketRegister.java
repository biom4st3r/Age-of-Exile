package com.robertx22.age_of_exile.mmorpg.registers.common;

import com.robertx22.age_of_exile.vanilla_mc.packets.AllocateStatPacket;
import com.robertx22.age_of_exile.vanilla_mc.packets.CharSelectPackets;
import com.robertx22.age_of_exile.vanilla_mc.packets.ChooseRacePacket;
import com.robertx22.age_of_exile.vanilla_mc.packets.ModifyItemPacket;
import com.robertx22.age_of_exile.vanilla_mc.packets.OpenGuiPacketv2;
import com.robertx22.age_of_exile.vanilla_mc.packets.perks.PerkChangePacket;
import com.robertx22.age_of_exile.vanilla_mc.packets.registry.RequestRegistriesPacket;
import com.robertx22.age_of_exile.vanilla_mc.packets.spells.TellServerToCancelSpellCast;
import com.robertx22.age_of_exile.vanilla_mc.packets.spells.TellServerToCastSpellPacket;
import com.robertx22.age_of_exile.vanilla_mc.packets.sync_cap.RequestSyncCapToClient;

public class C2SPacketRegister {

    public static void register() {
        new ModifyItemPacket().registerClientPacket();
        // Packets.registerClientToServerPacket(new ModifyItemPacket());
        new CharSelectPackets().registerClientPacket();
        // Packets.registerClientToServerPacket(new CharSelectPackets());
        new ChooseRacePacket().registerClientPacket();
        // Packets.registerClientToServerPacket(new ChooseRacePacket());
        new RequestSyncCapToClient().registerClientPacket();
        // Packets.registerClientToServerPacket(new RequestSyncCapToClient());
        new TellServerToCastSpellPacket().registerClientPacket();
        // Packets.registerClientToServerPacket(new TellServerToCastSpellPacket());
        new PerkChangePacket().registerClientPacket();
        // Packets.registerClientToServerPacket(new PerkChangePacket());
        new AllocateStatPacket().registerClientPacket();
        // Packets.registerClientToServerPacket(new AllocateStatPacket());
        new RequestRegistriesPacket().registerClientPacket(); // Not used?
        // Packets.registerClientToServerPacket(new RequestRegistriesPacket());
        new OpenGuiPacketv2().registerClientPacket();
        // Packets.registerClientToServerPacket(new OpenGuiPacket());
        new TellServerToCancelSpellCast().registerClientPacket();
        // Packets.registerClientToServerPacket(new TellServerToCancelSpellCast());

    }

}


