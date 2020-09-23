package com.robertx22.age_of_exile.mmorpg.registers.client;

import com.robertx22.age_of_exile.vanilla_mc.packets.*;
import com.robertx22.age_of_exile.vanilla_mc.packets.registry.EfficientRegistryPacket;
import com.robertx22.age_of_exile.vanilla_mc.packets.registry.RegistryPacket;
import com.robertx22.age_of_exile.vanilla_mc.packets.spells.TellClientToCastSpellPacket;
import com.robertx22.age_of_exile.vanilla_mc.packets.sync_cap.SyncCapabilityToClient;
import com.robertx22.library_of_exile.main.Packets;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;

public class S2CPacketRegister {

    public static void register() {

        Packets.registerServerToClient(new DmgNumPacket());
        Packets.registerServerToClient(new EfficientMobUnitPacket());
        Packets.registerServerToClient(new EntityUnitPacket());
        Packets.registerServerToClient(new NoManaPacket());
        Packets.registerServerToClient(new OnLoginClientPacket());
        Packets.registerServerToClient(new OpenGuiPacket());
        Packets.registerServerToClient(new RegistryPacket());
        Packets.registerServerToClient(new EfficientRegistryPacket());
        Packets.registerServerToClient(new SyncConfigToClientPacket());
        Packets.registerServerToClient(new SyncCapabilityToClient());
        Packets.registerServerToClient(new TellClientToCastSpellPacket());

        //   ClientSidePacketRegistry.INSTANCE.register(new Identifier(Ref.MODID, "login_reg"), new MyLoginPacket());

        ClientSidePacketRegistry.INSTANCE.register(EntityPacket.ID, (ctx, buf) -> {
            EntityPacketOnClient.onPacket(ctx, buf);
        });
    }
}
