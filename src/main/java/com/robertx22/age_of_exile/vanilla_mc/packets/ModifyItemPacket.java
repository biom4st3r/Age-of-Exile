package com.robertx22.age_of_exile.vanilla_mc.packets;

import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.age_of_exile.vanilla_mc.blocks.bases.BaseModificationStation;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModifyItemPacket implements ServerPacketConsumer {

    public static ModifyItemPacket EMPTY = new ModifyItemPacket();

    private BlockPos pos;

    private int num = 0;

    public ModifyItemPacket() {
    }

    public ModifyItemPacket(BlockPos pos, int num) {
        this.pos = pos;
        this.num = num;
    }

    public ModifyItemPacket(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public void onReceive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
            PacketSender responseSender) {
        BlockEntity be = player.world.getBlockEntity(pos);
        if(be instanceof BaseModificationStation) {
            ((BaseModificationStation)be).modifyItem(num, player);
        }
    }

    @Override
    public void loadFromData(PacketByteBuf buf) {        
        this.pos = buf.readBlockPos();
        this.num = buf.readInt();
    }

    @Override
    public void saveToData(PacketByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeInt(num);
    }

    @Override
    public Identifier getIdentifier() {
        return getId();
    }

    public static Identifier getId() {
        return new Identifier(Ref.MODID, "modifyitem");
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public ModifyItemPacket newInstance() {
        return new ModifyItemPacket();
    }
    
}
