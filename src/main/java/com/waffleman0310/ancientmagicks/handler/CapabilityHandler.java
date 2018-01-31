package com.waffleman0310.ancientmagicks.handler;

import com.waffleman0310.ancientmagicks.api.research.player.CapabilityResearch;
import com.waffleman0310.ancientmagicks.api.research.player.IPlayerResearch;
import com.waffleman0310.ancientmagicks.util.AncientMagicksUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler {

	public static final ResourceLocation MANA_CAPABILITY = new ResourceLocation(AncientMagicksUtil.modId, "mana");

	public static void registerCapabilityHandler() {
		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
	}

	@SubscribeEvent
	public void attachTileEntityCapabilities(AttachCapabilitiesEvent<TileEntity> event) {
		// Attach tile entity capabilities
	}

	@SubscribeEvent
	public void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> entity) {
		if (entity.getObject() instanceof EntityPlayer) {
			entity.addCapability(new ResourceLocation(AncientMagicksUtil.modId), new CapabilityResearch());
		}
	}

	@SubscribeEvent
	public void attachItemCapabilities(AttachCapabilitiesEvent<Item> event) {
		// Attach itemstack capabilities
	}

	@SubscribeEvent
	public void attachWorldCapabilities(AttachCapabilitiesEvent<World> event) {
		// Attach world capabilities
	}

	@SubscribeEvent
	public void onPlayerDeath(PlayerEvent.Clone clone) {
		if (clone.isWasDeath()) {
			// Player died, attach a copy of the old research information
			IPlayerResearch originalResearch = clone.getOriginal().getCapability(CapabilityResearch.RESEARCH, null);
			IPlayerResearch cloneResearch = clone.getEntityPlayer().getCapability(CapabilityResearch.RESEARCH, null);

			cloneResearch.setResearchDiscovered(originalResearch.getResearchDiscovered());
		}
	}
}
