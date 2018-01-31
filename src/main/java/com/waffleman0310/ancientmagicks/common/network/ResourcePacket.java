package com.waffleman0310.ancientmagicks.common.network;

import com.waffleman0310.ancientmagicks.common.tileentity.base.TileEntitySchoolMachine;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ResourcePacket implements IMessage {

    long resource;
    BlockPos pos;

    public ResourcePacket() {

    }

    public ResourcePacket(long resource, BlockPos pos) {
        this.resource = resource;
        this.pos = pos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.resource = buf.readLong();
        this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(this.resource);
        buf.writeInt(this.pos.getX());
        buf.writeInt(this.pos.getY());
        buf.writeInt(this.pos.getZ());
    }

    public static class ResourcePacketHandler implements IMessageHandler<ResourcePacket, IMessage> {

        @Override
        public IMessage onMessage(ResourcePacket message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                TileEntitySchoolMachine schoolMachine = (TileEntitySchoolMachine) Minecraft.getMinecraft().world.getTileEntity(message.pos);
                schoolMachine.setResourceStored(message.resource);
            });

            return null;
        }
    }
}
