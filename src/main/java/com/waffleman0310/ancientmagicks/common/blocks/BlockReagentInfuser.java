package com.waffleman0310.ancientmagicks.common.blocks;

import com.waffleman0310.ancientmagicks.api.research.IResearchable;
import com.waffleman0310.ancientmagicks.common.blocks.base.AncientMagicksBlock;
import com.waffleman0310.ancientmagicks.schools.EnumSchool;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockReagentInfuser extends AncientMagicksBlock implements IResearchable {

	public BlockReagentInfuser(String name) {
		super(name, Material.ROCK);
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public NodeType getNodeType(ItemStack stack) {
		return null;
	}

	@Override
	public EnumSchool getSchool(ItemStack stack) {
		return null;
	}

	@Override
	public int getKnowledgeLevels(ItemStack stack) {
		return 0;
	}

	@Override
	public Item getItem() {
		return null;
	}
}
