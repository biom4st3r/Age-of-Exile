package com.robertx22.age_of_exile.mmorpg.registers.client;

import com.robertx22.age_of_exile.vanilla_mc.packets.*;
import com.robertx22.age_of_exile.vanilla_mc.packets.registry.EfficientRegistryPacket;
import com.robertx22.age_of_exile.vanilla_mc.packets.registry.RegistryPacket;
import com.robertx22.age_of_exile.vanilla_mc.packets.spells.TellClientToCastSpellPacket;
import com.robertx22.age_of_exile.vanilla_mc.packets.sync_cap.SyncCapabilityToClient;

public class S2CPacketRegister {

    public static void register() {

        new DmgNumPacket().registerServerPacket();
        new ForceChoosingRace().registerServerPacket();
        new EfficientMobUnitPacket().registerServerPacket();
        new EntityUnitPacket().registerServerPacket();
        new NoManaPacket().registerServerPacket();
        new OnLoginClientPacket().registerServerPacket();
        new OpenGuiPacket().registerServerPacket();
        new RegistryPacket().registerServerPacket();
        new EfficientRegistryPacket().registerServerPacket();
        new SyncCapabilityToClient().registerServerPacket();
        new TellClientToCastSpellPacket().registerServerPacket();
        new SyncAreaLevelPacket().registerServerPacket();
        new SkillLevelUpToClient().registerServerPacket();

    }
}
