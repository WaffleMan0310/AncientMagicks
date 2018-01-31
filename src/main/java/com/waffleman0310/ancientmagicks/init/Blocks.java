package com.waffleman0310.ancientmagicks.init;

import com.waffleman0310.ancientmagicks.common.blocks.*;
import com.waffleman0310.ancientmagicks.common.blocks.base.AncientMagicksBlock;
import com.waffleman0310.ancientmagicks.common.items.base.AncientMagicksItemBlock;
import com.waffleman0310.ancientmagicks.common.items.itemblock.*;
import com.waffleman0310.ancientmagicks.util.AncientMagicksUtil;
import com.waffleman0310.ancientmagicks.variant.EnumMetalType;
import com.waffleman0310.ancientmagicks.variant.EnumOreType;
import com.waffleman0310.ancientmagicks.variant.EnumTreeType;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Blocks {

	public static final BlockLog LOG;
	public static final BlockLeaves LEAVES;
	public static final BlockSapling SAPLING;
	public static final BlockOre ORE;
	public static final BlockMetal METAL;
	public static final BlockArcanistSmeltery ARCANIST_SMELTERY;

	public static void registerAllBlocks() {
		registerBlockWithItemBlock(LOG, new ItemBlockLog(LOG));
		registerBlockWithItemBlock(LEAVES, new ItemBlockLeaves(LEAVES));
		registerBlockWithItemBlock(SAPLING, new ItemBlockSapling(SAPLING));
		registerBlockWithItemBlock(ORE, new ItemBlockOre(ORE));
		registerBlockWithItemBlock(METAL, new ItemBlockMetal(METAL));

		registerBlockWithGenericItemBlock(ARCANIST_SMELTERY);

		registerBlockStateMapper(LOG, new StateMap.Builder().withName(BlockLog.VARIANT).withSuffix("_log").build());
		registerBlockStateMapper(LEAVES, new StateMap.Builder().withName(BlockLog.VARIANT).withSuffix("_leaves").build());
		registerBlockStateMapper(SAPLING, new StateMap.Builder().withName(BlockLog.VARIANT).withSuffix("_sapling").build());
		registerBlockStateMapper(ORE, new StateMap.Builder().withName(BlockOre.VARIANT).withSuffix("_ore").build());
		registerBlockStateMapper(METAL, new StateMap.Builder().withName(BlockMetal.VARIANT).withSuffix("_block").build());
	}

	public static void registerRender() {
		registerRender(ARCANIST_SMELTERY, 0);

		for (EnumTreeType type : EnumTreeType.values()) {
			ModelLoader.setCustomModelResourceLocation(
					Item.getItemFromBlock(LOG),
					type.getMetadata(),
					createVariantMRL(LOG, type, "axis=none")

			);
			ModelLoader.setCustomModelResourceLocation(
					Item.getItemFromBlock(LEAVES),
					type.getMetadata(),
					createVariantMRL(LEAVES, type)
			);

			ModelLoader.setCustomModelResourceLocation(
					Item.getItemFromBlock(SAPLING),
					type.getMetadata(),
					createVariantMRL(SAPLING, type)
			);
		}

		for (EnumOreType type : EnumOreType.values()) {
			ModelLoader.setCustomModelResourceLocation(
					Item.getItemFromBlock(ORE),
					type.getMetadata(),
					createVariantMRL(ORE, type)
			);
		}

		for (EnumMetalType type : EnumMetalType.values()) {
			ModelLoader.setCustomModelResourceLocation(
					Item.getItemFromBlock(METAL),
					type.getMetadata(),
					createVariantMRL(METAL, type)
			);
		}
	}


	public static void registerBlock(AncientMagicksBlock block) {
		//GameRegistry.register(block.setRegistryName(block.getName()));
		ForgeRegistries.BLOCKS.register(block.setRegistryName(block.getName()));
	}

	public static void registerBlockWithItemBlock(AncientMagicksBlock block, AncientMagicksItemBlock itemBlock) {
		//GameRegistry.register(block.setRegistryName(block.getName()));
		//GameRegistry.register(itemBlock.setRegistryName(block.getName()));
		ForgeRegistries.BLOCKS.register(block.setRegistryName(block.getName()));
		ForgeRegistries.ITEMS.register(itemBlock.setRegistryName(block.getName()));
	}

	public static void registerBlockWithGenericItemBlock(AncientMagicksBlock block) {
		//GameRegistry.register(block.setRegistryName(block.getName()));
		//GameRegistry.register(new AncientMagicksItemBlock(block).setRegistryName(block.getName()));
		ForgeRegistries.BLOCKS.register(block.setRegistryName(block.getName()));
		ForgeRegistries.ITEMS.register(new AncientMagicksItemBlock(block).setRegistryName(block.getName()));
	}

	public static void registerBlockStateMapper(Block block, IStateMapper stateMapper) {
		ModelLoader.setCustomStateMapper(block, stateMapper);
	}

	public static void registerRender(Block block, int meta) {
		ModelLoader.setCustomModelResourceLocation(
				Item.getItemFromBlock(block),
				meta,
				new ModelResourceLocation(block.getRegistryName(), "inventory")
		);
	}

	private static ModelResourceLocation createVariantMRL(AncientMagicksBlock block, IStringSerializable type, String variant) {
		return new ModelResourceLocation(
				new ResourceLocation(
						AncientMagicksUtil.modId,
						String.format("%s_%s", type.getName(), block.getName())
				),
				variant
		);
	}

	private static ModelResourceLocation createVariantMRL(AncientMagicksBlock block, IStringSerializable type) {
		return createVariantMRL(block, type, "inventory");
	}

	static {
		LOG = new BlockLog("log");
		LEAVES = new BlockLeaves("leaves");
		SAPLING = new BlockSapling("sapling");
		ORE = new BlockOre("ore");
		METAL = new BlockMetal("metal_block"); // better name
		ARCANIST_SMELTERY = new BlockArcanistSmeltery("arcanists_smeltery");
	}
}
