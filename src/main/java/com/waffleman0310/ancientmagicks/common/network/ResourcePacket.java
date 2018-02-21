package com.waffleman0310.ancientmagicks.common.network;

import com.waffleman0310.ancientmagicks.api.school.School;
import com.waffleman0310.ancientmagicks.api.school.resource.ResourceStorage;
import com.waffleman0310.ancientmagicks.api.tileentity.ISchoolMachine;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ResourcePacket implements IMessage {

	public ResourcePacket() {}

	public ResourcePacket(BlockPos pos, School school, long stored) {

	}

	@Override
	public void fromBytes(ByteBuf buf) {

	}

	@Override
	public void toBytes(ByteBuf buf) {

	}

	public static class ResourcePacketHandler implements IMessageHandler<ResourcePacket, IMessage> {

		public ResourcePacketHandler() {}

		@Override
		public IMessage onMessage(ResourcePacket message, MessageContext ctx) {
			return null;
		}
	}
}
