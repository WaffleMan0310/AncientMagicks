package com.waffleman0310.ancientmagicks.proxy;

import com.waffleman0310.ancientmagicks.AncientMagicks;
import com.waffleman0310.ancientmagicks.api.mana.CapabilityMana;
import com.waffleman0310.ancientmagicks.api.school.resource.CapabilityResource;
import com.waffleman0310.ancientmagicks.handler.CapabilityHandler;
import com.waffleman0310.ancientmagicks.handler.EventHandler;
import com.waffleman0310.ancientmagicks.handler.GuiHandler;
import com.waffleman0310.ancientmagicks.handler.PacketHandler;
import com.waffleman0310.ancientmagicks.init.*;
import net.minecraft.network.NetworkManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {

    public void preInitialization(FMLPreInitializationEvent event) {
        Blocks.registerAllBlocks();
        Items.registerAllItems();
    }

    public void initialization(FMLInitializationEvent event) {
        World.registerAllGenerators();
        Recipes.registerAllRecipes();
        OreDict.registerAllOreDictEntries();
        TileEntites.registerAllTileEntities();

        CapabilityMana.register();
        CapabilityResource.register();
        CapabilityHandler.registerCapabilityHandler();


        NetworkRegistry.INSTANCE.registerGuiHandler(AncientMagicks.instance, new GuiHandler());
        Packets.registerPackets();
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }

    public void postInitialization(FMLPostInitializationEvent event) {

    }
}
