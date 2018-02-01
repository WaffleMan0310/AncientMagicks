package com.waffleman0310.ancientmagicks.common.blocks;

import com.waffleman0310.ancientmagicks.common.blocks.base.AncientMagicksBlock;
import com.waffleman0310.ancientmagicks.variant.EnumOreType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class BlockOre extends AncientMagicksBlock {

	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumOreType.class);

	public BlockOre(String name) {
		super(name, Material.ROCK);
		setDefaultState(blockState.getBaseState().withProperty(VARIANT, EnumOreType.BAUXITE));
	}

	@Override
	public int damageDropped(IBlockState state) {
		return ((EnumOreType) state.getValue(VARIANT)).getMetadata();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(VARIANT, EnumOreType.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumOreType) state.getValue(VARIANT)).getMetadata();
	}

	@Override
	public void getSubBlocks(CreativeTabs tabs, NonNullList<ItemStack> list) {
		for (EnumOreType ore : EnumOreType.values()) {
			list.add(new ItemStack(this, 1, ore.getMetadata()));
		}
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, VARIANT);
	}
}
