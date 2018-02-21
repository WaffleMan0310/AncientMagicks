package com.waffleman0310.ancientmagicks.common.network;

import com.waffleman0310.ancientmagicks.api.tileentity.IManaMachine;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ManaPacket implements IMessage{

	private BlockPos pos;
	private long manaStored;

	public ManaPacket() {}

	public ManaPacket(BlockPos pos, long stored) {
		this.manaStored = stored;
		this.pos = pos;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		PacketBuffer buffer = new PacketBuffer(buf);
		this.pos = buffer.readBlockPos();
		this.manaStored = buffer.readLong();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer buffer = new PacketBuffer(buf);

		buffer.writeBlockPos(this.pos);
		buffer.writeLong(this.manaStored);
	}

	public static class ManaPacketHandler implements IMessageHandler<ManaPacket, IMessage> {

		public ManaPacketHandler() {}

		@Override
		public IMessage onMessage(ManaPacket message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				IManaMachine machine = (IManaMachine) Minecraft.getMinecraft().world.getTileEntity(message.pos);
				machine.setManaStored(message.manaStored);
			});
			return null;
		}
	}
}
