package com.waffleman0310.ancientmagicks.common.blocks.base;

import com.waffleman0310.ancientmagicks.api.block.IMultiBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;

public abstract class AncientMagicksMultiBlock extends AncientMagicksBlock implements IMultiBlock{

	public static final PropertyBool FORMED = PropertyBool.create("formed");

	public AncientMagicksMultiBlock(String name, Material material) {
		super(name, material);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FORMED, false));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(FORMED, meta > 0);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FORMED) ? 1 : 0;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FORMED);
	}
}
