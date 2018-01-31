package com.waffleman0310.ancientmagicks.common.network;

import com.waffleman0310.ancientmagicks.api.research.ResearchableMeta;
import com.waffleman0310.ancientmagicks.api.research.player.CapabilityResearch;
import com.waffleman0310.ancientmagicks.api.research.player.IPlayerResearch;
import com.waffleman0310.ancientmagicks.api.research.player.PlayerResearch;
import com.waffleman0310.ancientmagicks.research.ResearchMap;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.HashMap;
import java.util.Map.Entry;

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
        HashMap<ResearchableMeta, Boolean> readResearch = new HashMap<>(buf.readInt());
        for (int i = 0; i < readResearch.size() * 2; i++) {
            readResearch.put(ResearchMap.getResearchForId(buf.readInt()), buf.readInt() > 0);
        }

        this.playerResearch.setResearchDiscovered(readResearch);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(playerResearch.getResearchDiscovered().size());
        for (Entry<ResearchableMeta, Boolean> entry : this.playerResearch.getResearchDiscovered().entrySet()) {
            buf.writeInt(ResearchMap.getIdForResearch(entry.getKey()));
            buf.writeInt(entry.getValue() ? 1 : 0);
        }
    }

    public static class ResearchPacketHandler implements IMessageHandler<ResearchPacket, IMessage> {

        @Override
        public IMessage onMessage(ResearchPacket message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                IPlayerResearch playerResearch = Minecraft.getMinecraft().player.getCapability(CapabilityResearch.RESEARCH, null);
                playerResearch.setResearchDiscovered(message.playerResearch.getResearchDiscovered());
            });

            return null;
        }
    }
}
