package com.waffleman0310.ancientmagicks.common.network;

import com.waffleman0310.ancientmagicks.api.research.player.CapabilityResearch;
import com.waffleman0310.ancientmagicks.api.research.player.IPlayerResearch;
import com.waffleman0310.ancientmagicks.api.research.player.PlayerResearch;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ResearchPacket implements IMessage {

	private PlayerResearch playerResearch;

	public ResearchPacket() {
		playerResearch = new PlayerResearch();
	}

	public ResearchPacket(PlayerResearch playerResearch) {
		this.playerResearch = playerResearch;
	}

	@Override
	public void fromBytes(ByteBuf buf) {

	}

	@Override
	public void toBytes(ByteBuf buf) {

	}

	public static class ResearchPacketHandler implements IMessageHandler<ResearchPacket, IMessage> {

		public ResearchPacketHandler() {}

		@Override
		public IMessage onMessage(ResearchPacket message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				IPlayerResearch playerResearch = Minecraft.getMinecraft().player.getCapability(CapabilityResearch.RESEARCH, null);

			});

			return null;
		}
	}
}
