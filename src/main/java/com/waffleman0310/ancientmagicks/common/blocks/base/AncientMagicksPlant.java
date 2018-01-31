package com.waffleman0310.ancientmagicks.common.blocks.base;

import com.waffleman0310.ancientmagicks.api.block.IPlant;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public abstract class AncientMagicksPlant extends AncientMagicksBush implements IPlant {

	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 15);

	public AncientMagicksPlant(String name) {
		super(name, Material.GRASS);
		setCreativeTab(null);
	}

	public IBlockState withAge(int age) {
		return getDefaultState().withProperty(AGE, age);
	}

	public boolean isMaxAge(IBlockState state) {
		if (state.getValue(AGE) >= getMaxAge()) {
			return true;
		}
		return false;
	}

	public int getAge(IBlockState state) {
		return state.getValue(AGE);
	}

	public int getMaxAge() {
		return 15;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(worldIn, pos, state, rand);
		if (worldIn.getLight(pos, true) >= getLightToGrow(worldIn, pos)) {
			if (rand.nextFloat() < getGrowthChance(worldIn, pos)) {
				if (worldIn.getBlockState(pos).getValue(AGE) < getMaxAge()) {
					worldIn.setBlockState(pos, withAge(getAge(state) + 1), 2);
				}
			}
		}
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		int age = getAge(state) + getBonemealAgeIncrease(worldIn, pos);
		if (age > getMaxAge()) {
			age = getMaxAge();
		}
		worldIn.setBlockState(pos, withAge(age), 2);
	}

	@Nullable
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		Item itemToDrop;
		if (isMaxAge(state)) {
			itemToDrop = getCrop();
		} else {
			itemToDrop = getSeeds();
		}
		return itemToDrop;
	}

	@Nullable
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		ItemStack itemStack;
		if (isMaxAge(state)) {
			itemStack = new ItemStack(getCrop(), 1, 0);
		} else {
			itemStack = new ItemStack(getSeeds(), 1, 0);
		}
		return itemStack;
	}

	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
		super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
		Random random = worldIn.rand != null ? worldIn.rand : new Random();
		if (isMaxAge(state)) {
			spawnAsEntity(worldIn, pos, new ItemStack(getSeeds(), MathHelper.getInt(random, getMinSeedsDropped(), getMaxSeedsDropped())));
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (isMaxAge(state)) {
			for (ItemStack stack : getDrops(worldIn, pos, state, 0)) {
				spawnAsEntity(worldIn, pos, stack);
			}
			worldIn.setBlockState(pos, withAge(0), 2);
		}
		return false;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> drops = new ArrayList<>();
		Random random = new Random();

		drops.addAll(getDrops().stream()
				.filter(dropEntry -> random.nextFloat() < dropEntry.getChance()) // This predicate does the spawn probability
				.map(dropEntry -> dropEntry.drop(random))
				.collect(Collectors.toList())
		);
		return drops;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(AGE, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(AGE);
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, AGE);
	}
}
