package com.robertx22.age_of_exile.mmorpg.registers.client;

import com.robertx22.age_of_exile.vanilla_mc.packets.*;
import com.robertx22.age_of_exile.vanilla_mc.packets.registry.EfficientRegistryPacket;
import com.robertx22.age_of_exile.vanilla_mc.packets.registry.RegistryPacket;
import com.robertx22.age_of_exile.vanilla_mc.packets.spells.TellClientToCastSpellPacket;
import com.robertx22.age_of_exile.vanilla_mc.packets.sync_cap.SyncCapabilityToClient;

public class S2CPacketRegister {

    public static void register() {

        new DmgNumPacket().register();
        new ForceChoosingRace().register();
        new EfficientMobUnitPacket().register();
        new EntityUnitPacket().register();
        new NoManaPacket().register();
        new OnLoginClientPacket().register();
        new OpenGuiPacket().register();
        new RegistryPacket().register();
        new EfficientRegistryPacket().register();
        new SyncCapabilityToClient().register();
        new TellClientToCastSpellPacket().register();
        new SyncAreaLevelPacket().register();
        new SkillLevelUpToClient().register();

    }
}
