package com.waffleman0310.ancientmagicks.proxy;

import com.waffleman0310.ancientmagicks.init.Blocks;
import com.waffleman0310.ancientmagicks.init.Items;
import com.waffleman0310.ancientmagicks.util.AncientMagicksUtil;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInitialization(FMLPreInitializationEvent event) {
		super.preInitialization(event);
		OBJLoader.INSTANCE.addDomain(AncientMagicksUtil.modId);
		Blocks.registerRender();
		Items.registerRender();
	}

	@Override
	public void initialization(FMLInitializationEvent event) {
		super.initialization(event);
	}

	@Override
	public void postInitialization(FMLPostInitializationEvent event) {
		super.postInitialization(event);
	}
}
