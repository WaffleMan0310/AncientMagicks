package com.waffleman0310.ancientmagicks.init;

import com.waffleman0310.ancientmagicks.client.renderer.tileentity.TileEntityArcanistSmelteryRender;
import com.waffleman0310.ancientmagicks.common.blocks.*;
import com.waffleman0310.ancientmagicks.common.blocks.base.AncientMagicksBlock;
import com.waffleman0310.ancientmagicks.common.items.base.AncientMagicksItemBlock;
import com.waffleman0310.ancientmagicks.common.items.itemblock.*;
import com.waffleman0310.ancientmagicks.common.tileentity.TileEntityArcanistSmeltery;
import com.waffleman0310.ancientmagicks.util.AncientMagicksUtil;
import com.waffleman0310.ancientmagicks.variant.EnumMetalType;
import com.waffleman0310.ancientmagicks.variant.EnumOreType;
import com.waffleman0310.ancientmagicks.variant.EnumTreeType;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class Blocks {

	public static final BlockPlanks PLANKS;
	public static final BlockLog LOG;
	public static final BlockLeaves LEAVES;
	public static final BlockSapling SAPLING;
	public static final BlockOre ORE;
	public static final BlockMetal METAL;
	public static final BlockColoredBrick BRICK;
	public static final BlockArcanistSmeltery ARCANIST_SMELTERY;
	public static final BlockReagentInfuser REAGENT_INFUSER;

	public static void registerAllBlocks() {
		registerBlockWithItemBlock(PLANKS, new ItemBlockPlanks(PLANKS));
		registerBlockWithItemBlock(LOG, new ItemBlockLog(LOG));
		registerBlockWithItemBlock(LEAVES, new ItemBlockLeaves(LEAVES));
		registerBlockWithItemBlock(SAPLING, new ItemBlockSapling(SAPLING));
		registerBlockWithItemBlock(ORE, new ItemBlockOre(ORE));
		registerBlockWithItemBlock(METAL, new ItemBlockMetal(METAL));
		registerBlockWithItemBlock(BRICK, new ItemBlockColoredBrick(BRICK));

		registerBlockWithGenericItemBlock(ARCANIST_SMELTERY);
		registerBlockWithGenericItemBlock(REAGENT_INFUSER);

		registerBlockStateMapper(PLANKS, new StateMap.Builder().withName(BlockLog.VARIANT).withSuffix("_planks").build());
		registerBlockStateMapper(LOG, new StateMap.Builder().withName(BlockLog.VARIANT).withSuffix("_log").build());
		registerBlockStateMapper(LEAVES, new StateMap.Builder().withName(BlockLog.VARIANT).withSuffix("_leaves").build());
		registerBlockStateMapper(SAPLING, new StateMap.Builder().withName(BlockLog.VARIANT).withSuffix("_sapling").build());
		registerBlockStateMapper(ORE, new StateMap.Builder().withName(BlockOre.VARIANT).withSuffix("_ore").build());
		registerBlockStateMapper(METAL, new StateMap.Builder().withName(BlockMetal.VARIANT).withSuffix("_block").build());
		registerBlockStateMapper(BRICK, new StateMap.Builder().withName(BlockColoredBrick.COLOR).withSuffix("_colored_brick").build());
	}

	public static void registerRender() {
		registerRender(ARCANIST_SMELTERY);
		registerRender(REAGENT_INFUSER);

		registerTileEntitySpecialRender(TileEntityArcanistSmeltery.class, new TileEntityArcanistSmelteryRender());

		for (EnumDyeColor color : EnumDyeColor.values()) {
			ModelLoader.setCustomModelResourceLocation(
					Item.getItemFromBlock(BRICK),
					color.getMetadata(),
					createVariantMRL(BRICK, color)
			);
		}

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

	public static void registerBlockWithItemBlock(AncientMagicksBlock block, AncientMagicksItemBlock itemBlock) {
		ForgeRegistries.BLOCKS.register(block.setRegistryName(block.getName()));
		ForgeRegistries.ITEMS.register(itemBlock.setRegistryName(block.getName()));
	}

	public static void registerBlockWithGenericItemBlock(AncientMagicksBlock block) {
		ForgeRegistries.BLOCKS.register(block.setRegistryName(block.getName()));
		ForgeRegistries.ITEMS.register(new AncientMagicksItemBlock(block).setRegistryName(block.getName()));
	}

	public static void registerBlockStateMapper(AncientMagicksBlock block, IStateMapper stateMapper) {
		ModelLoader.setCustomStateMapper(block, stateMapper);
	}

	public static void registerRender(AncientMagicksBlock block) {
		registerRender(block, 0);
	}

	public static void registerRender(AncientMagicksBlock block, int meta) {
		ModelLoader.setCustomModelResourceLocation(
				Item.getItemFromBlock(block),
				meta,
				createMRL(block)
		);
	}

	public static void registerTileEntitySpecialRender(Class<? extends TileEntity> tileEntity, TileEntitySpecialRenderer renderer) {
		ClientRegistry.bindTileEntitySpecialRenderer(tileEntity, renderer);
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

	public static ModelResourceLocation createMRL(AncientMagicksBlock block) {
		return new ModelResourceLocation(
				new ResourceLocation(
						AncientMagicksUtil.modId,
						block.getName()
				),
				"inventory"
		);
	}

	static {
		PLANKS = new BlockPlanks("planks");
		LOG = new BlockLog("log");
		LEAVES = new BlockLeaves("leaves");
		SAPLING = new BlockSapling("sapling");
		ORE = new BlockOre("ore");
		METAL = new BlockMetal("metal_block"); // better name
		BRICK = new BlockColoredBrick("colored_brick");
		ARCANIST_SMELTERY = new BlockArcanistSmeltery("arcanists_smeltery");
		REAGENT_INFUSER = new BlockReagentInfuser("reagent_infuser");
	}
}
