package com.waffleman0310.ancientmagicks.common.blocks;

import com.waffleman0310.ancientmagicks.common.blocks.base.AncientMagicksPlant;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockCinderbloom extends AncientMagicksPlant {

	public BlockCinderbloom(String name) {
		super(name);
	}

	@Override
	public float getGrowthChance(World worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public int getBonemealAgeIncrease(World worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public NonNullList<DropEntry> getDrops() {
		return null;
	}

	@Override
	public Item getCrop() {
		return null;
	}

	@Override
	public Item getSeeds() {
		return null;
	}

	@Override
	public int getMinSeedsDropped() {
		return 0;
	}

	@Override
	public int getMaxSeedsDropped() {
		return 0;
	}

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return false;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return false;
	}
}