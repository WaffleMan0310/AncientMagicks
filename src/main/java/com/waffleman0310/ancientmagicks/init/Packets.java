package com.waffleman0310.ancientmagicks.init;

import com.waffleman0310.ancientmagicks.common.network.ManaPacket;
import com.waffleman0310.ancientmagicks.common.network.ManaPacket.ManaPacketHandler;
import com.waffleman0310.ancientmagicks.handler.PacketHandler;
import net.minecraftforge.fml.relauncher.Side;


public class Packets {


    public static void registerPackets() {
        PacketHandler.INSTANCE.registerMessage(ManaPacketHandler.class, ManaPacket.class, 0, Side.CLIENT);
    }
}
