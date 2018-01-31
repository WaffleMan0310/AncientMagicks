package com.waffleman0310.ancientmagicks.init;

import com.waffleman0310.ancientmagicks.common.items.ItemMetal;
import com.waffleman0310.ancientmagicks.common.items.base.AncientMagicksItem;
import com.waffleman0310.ancientmagicks.common.items.ItemMortarPestle;
import com.waffleman0310.ancientmagicks.common.items.ItemReagent;
import com.waffleman0310.ancientmagicks.util.AncientMagicksUtil;
import com.waffleman0310.ancientmagicks.variant.EnumMetalType;
import com.waffleman0310.ancientmagicks.variant.EnumReagentType;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Items {

	public static final ItemReagent REAGENTS;
	public static final ItemMortarPestle MORTAR_AND_PESTLE;
	public static final ItemMetal METAL;

	public static void registerAllItems() {
		registerItem(REAGENTS);
		registerItem(MORTAR_AND_PESTLE);
		registerItem(METAL);
	}

	public static void registerItem(AncientMagicksItem item) {
		//GameRegistry.register(item.setRegistryName(item.getName()));
		ForgeRegistries.ITEMS.register(item.setRegistryName(item.getName()));
	}

	public static void registerRender() {
		for (EnumReagentType type : EnumReagentType.values()) {
			ModelLoader.setCustomModelResourceLocation(
					REAGENTS,
					type.getMetadata(),
					createVariantMRL(REAGENTS, type)
			);
		}

		for (EnumMetalType type : EnumMetalType.values()) {
			ModelLoader.setCustomModelResourceLocation(
					METAL,
					type.getMetadata(),
					createVariantMRL(METAL, type)
			);

			ModelLoader.setCustomModelResourceLocation(
					METAL,
					type.getMetadata() + EnumMetalType.values().length,
					createVariantMRL(METAL, type)
			);
		}

		registerRender(MORTAR_AND_PESTLE, 0);
	}

	public static void registerRender(Item item, int meta) {
		ModelLoader.setCustomModelResourceLocation(
				item,
				meta,
				new ModelResourceLocation(item.getRegistryName(), "inventory")
		);
	}

	private static ModelResourceLocation createVariantMRL(AncientMagicksItem item, IStringSerializable type) {
		return new ModelResourceLocation(
				new ResourceLocation(
						AncientMagicksUtil.modId,
						String.format("%s_%s", type.getName(), item.getName())
				),
				"inventory"
		);
	}

	static {
		REAGENTS = new ItemReagent("reagent");
		MORTAR_AND_PESTLE = new ItemMortarPestle("mortar_and_pestle");
		METAL = new ItemMetal("metal");
	}
}
