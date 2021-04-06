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
        new ModifyItemPacket().register();
        // Packets.registerClientToServerPacket(new ModifyItemPacket());
        new CharSelectPackets().register();
        // Packets.registerClientToServerPacket(new CharSelectPackets());
        new ChooseRacePacket().register();
        // Packets.registerClientToServerPacket(new ChooseRacePacket());
        new RequestSyncCapToClient().register();
        // Packets.registerClientToServerPacket(new RequestSyncCapToClient());
        new TellServerToCastSpellPacket().register();
        // Packets.registerClientToServerPacket(new TellServerToCastSpellPacket());
        new PerkChangePacket().register();
        // Packets.registerClientToServerPacket(new PerkChangePacket());
        new AllocateStatPacket().register();
        // Packets.registerClientToServerPacket(new AllocateStatPacket());
        new RequestRegistriesPacket().register(); // Not used?
        // Packets.registerClientToServerPacket(new RequestRegistriesPacket());
        new OpenGuiPacketv2().register();
        // Packets.registerClientToServerPacket(new OpenGuiPacket());
        new TellServerToCancelSpellCast().register();
        // Packets.registerClientToServerPacket(new TellServerToCancelSpellCast());

    }

}


