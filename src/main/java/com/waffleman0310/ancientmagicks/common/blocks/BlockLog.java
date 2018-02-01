package com.waffleman0310.ancientmagicks.common.blocks;

import com.waffleman0310.ancientmagicks.common.blocks.base.AncientMagicksPillar;
import com.waffleman0310.ancientmagicks.variant.EnumTreeType;
import net.minecraft.block.BlockLog.EnumAxis;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockLog extends AncientMagicksPillar {

	public static final PropertyEnum<EnumTreeType> VARIANT = PropertyEnum.create("variant", EnumTreeType.class, input -> input.getMetadata() < 4);

	public BlockLog(String name) {
		super(name, Material.WOOD);
		setDefaultState(blockState.getBaseState()
				.withProperty(VARIANT, EnumTreeType.TIME_TWISTED)
				.withProperty(AXIS, EnumAxis.Y));
	}

	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(VARIANT).getMetadata();
	}

	@Override
	public void getSubBlocks(CreativeTabs tabs, NonNullList<ItemStack> list) {
		for (int t = 0; t < EnumTreeType.values().length; t++) {
			if (t <= 4) {
				list.add(new ItemStack(this, 1, EnumTreeType.values()[t].getMetadata()));
			} else {
				list.add(new ItemStack(this, 1, EnumTreeType.values()[t].getMetadata() - 4));
			}
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = getDefaultState().withProperty(VARIANT, EnumTreeType.byMetadata(meta & 3));
        /*
        if (meta < 4) {
            state = state.withProperty(VARIANT, EnumTreeType.byMetadata(meta & 3));
        } else {
            state = state.withProperty(VARIANT, EnumTreeType.byMetadata((meta & 3) + 4));
        }
        */
		switch (meta & 12) {
			case 0:
				state = state.withProperty(AXIS, EnumAxis.Y);
				break;
			case 4:
				state = state.withProperty(AXIS, EnumAxis.X);
				break;
			case 8:
				state = state.withProperty(AXIS, EnumAxis.Z);
				break;
		}
		return state;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = 0;
		meta |= state.getValue(VARIANT).getMetadata();
        /*
        if (state.getValue(VARIANT).getMetadata() < 4) {
            meta |= state.getValue(VARIANT).getMetadata();
        } else {
            meta |= state.getValue(VARIANT).getMetadata() - 4;
        }
        */

		switch (state.getValue(AXIS)) {
			case X:
				meta |= 4;
				break;
			case Z:
				meta |= 8;
				break;
			case NONE:
				meta |= 12;
				break;
		}
		return meta;
	}

	@Override
	public int getLightValue(IBlockState state) {
		switch (state.getValue(VARIANT)) {
			case YGGDRASIL:
				return 8;
			default:
				return super.getLightValue(state);
		}
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, VARIANT, AXIS);
	}

	@Override
	public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public boolean isWood(IBlockAccess world, BlockPos pos) {
		return true;
	}
}
