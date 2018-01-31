package com.waffleman0310.ancientmagicks.common.network;

import com.waffleman0310.ancientmagicks.common.tileentity.base.TileEntityManaMachine;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ManaPacket implements IMessage {

	private long mana;
	private BlockPos pos;

	public ManaPacket() {
	}

	public ManaPacket(long mana, BlockPos pos) {
		this.mana = mana;
		this.pos = pos;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.mana = buf.readLong();
		this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(this.mana);
		buf.writeInt(this.pos.getX());
		buf.writeInt(this.pos.getY());
		buf.writeInt(this.pos.getZ());
	}

	public static class ManaPacketHandler implements IMessageHandler<ManaPacket, IMessage> {

		public ManaPacketHandler() {
		}

		@Override
		public IMessage onMessage(ManaPacket message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				TileEntityManaMachine tileEntity = (TileEntityManaMachine) Minecraft.getMinecraft().world.getTileEntity(message.pos);
				tileEntity.setManaStored(message.mana);
			});
			return null;
		}
	}
}
