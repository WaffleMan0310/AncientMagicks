package com.waffleman0310.ancientmagicks.handler;

import com.waffleman0310.ancientmagicks.api.school.School;
import com.waffleman0310.ancientmagicks.api.util.AncientMagicksUtil;
import com.waffleman0310.ancientmagicks.api.util.AncientMagicksUtil.EnumResourcePrefix;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.RegistryBuilder;

@Mod.EventBusSubscriber
public class RegistryHandler {

	@SubscribeEvent
	public static void newRegistry(RegistryEvent.NewRegistry event) {
		addSchoolRegistry();

	}

	public static void addSchoolRegistry() {
		RegistryBuilder<School> builder = new RegistryBuilder<>();
		builder
				.setName(new ResourceLocation(AncientMagicksUtil.modId, "schools"))
				.setType(School.class)
				.setIDRange(0, 10)
				.create();
	}
}
