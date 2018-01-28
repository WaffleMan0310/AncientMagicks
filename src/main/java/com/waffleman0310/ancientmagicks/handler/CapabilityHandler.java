package com.waffleman0310.ancientmagicks.handler;

import com.waffleman0310.ancientmagicks.util.AncientMagicksUtil;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
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
    public void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
        // Attach entity capabilities
    }

    @SubscribeEvent
    public void attachItemCapabilities(AttachCapabilitiesEvent<Item> event) {
        // Attach itemstack capabilities
    }

    @SubscribeEvent
    public void attachWorldCapabilities(AttachCapabilitiesEvent<World> event) {
        // Attach world capabilities
    }
}
