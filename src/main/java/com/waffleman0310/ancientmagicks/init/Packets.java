package com.waffleman0310.ancientmagicks.init;


import com.waffleman0310.ancientmagicks.common.network.ManaPacket;
import com.waffleman0310.ancientmagicks.common.network.ResourcePacket;
import com.waffleman0310.ancientmagicks.handler.PacketHandler;
import net.minecraftforge.fml.relauncher.Side;

public class Packets {
	public static void registerPackets() {
		PacketHandler.INSTANCE.registerMessage(ManaPacket.ManaPacketHandler.class, ManaPacket.class, 0, Side.CLIENT);
		PacketHandler.INSTANCE.registerMessage(ResourcePacket.ResourcePacketHandler.class, ResourcePacket.class, 1, Side.CLIENT);
	}
}
