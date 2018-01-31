package com.waffleman0310.ancientmagicks.handler;

import com.waffleman0310.ancientmagicks.util.AncientMagicksUtil;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class PacketHandler {
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(AncientMagicksUtil.modId);
}
