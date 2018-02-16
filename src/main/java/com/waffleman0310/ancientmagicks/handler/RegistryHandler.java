package com.waffleman0310.ancientmagicks.handler;

import com.waffleman0310.ancientmagicks.api.school.School;
import com.waffleman0310.ancientmagicks.api.util.AncientMagicksUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.RegistryBuilder;

public class RegistryHandler {

	@SubscribeEvent
	public void createNewEvent(RegistryEvent.NewRegistry event) {
		addSchoolRegistry();

	}

	public static  void addSchoolRegistry() {
		RegistryBuilder<School> builder = new RegistryBuilder<>();
		builder
				.setName(new ResourceLocation(AncientMagicksUtil.modId, "school"))
				.setType(School.class)
				.setIDRange(0, 10)
				.create();
	}
}
