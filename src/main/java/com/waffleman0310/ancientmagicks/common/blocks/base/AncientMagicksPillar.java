package com.waffleman0310.ancientmagicks.common.blocks.base;

import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class AncientMagicksPillar extends AncientMagicksBlock {

	public static final PropertyEnum<BlockLog.EnumAxis> AXIS = PropertyEnum.create("axis", BlockLog.EnumAxis.class);

	public AncientMagicksPillar(String name, Material material) {
		super(name, material);
	}

	@Override
	public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) {
		IBlockState state = world.getBlockState(pos);
		for (IProperty<?> property : state.getProperties().keySet()) {
			if (property.getName().equals("axis")) {
				world.setBlockState(pos, state.cycleProperty(property));
				return true;
			}
		}
		return false;
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return getStateFromMeta(meta).withProperty(AXIS, BlockLog.EnumAxis.fromFacingAxis(facing.getAxis()));
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		IBlockState rotState = state;
		if (rot == Rotation.CLOCKWISE_90 || rot == Rotation.COUNTERCLOCKWISE_90) {
			switch (state.getValue(AXIS)) {
				case X:
					rotState = rotState.withProperty(AXIS, BlockLog.EnumAxis.Z);
					break;
				case Z:
					rotState = rotState.withProperty(AXIS, BlockLog.EnumAxis.X);
					break;
			}
		}
		return rotState;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return null;
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, AXIS);
	}
}
