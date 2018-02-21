package com.waffleman0310.ancientmagicks.common.blocks;

import com.waffleman0310.ancientmagicks.common.blocks.base.AncientMagicksBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockReagentInfuser extends AncientMagicksBlock {

	public static final AxisAlignedBB AXIS_ALIGNED_BB = new AxisAlignedBB(0.2d, 0.15d, 0.2d, 0.8d, 1.9d, 0.8d);

	public BlockReagentInfuser(String name) {
		super(name, Material.ROCK);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AXIS_ALIGNED_BB;
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
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return worldIn.isAirBlock(pos.up()) && super.canPlaceBlockAt(worldIn, pos);
	}
}
