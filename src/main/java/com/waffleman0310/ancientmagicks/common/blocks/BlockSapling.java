package com.waffleman0310.ancientmagicks.common.blocks;

import com.waffleman0310.ancientmagicks.common.blocks.base.AncientMagicksBush;
import com.waffleman0310.ancientmagicks.variant.EnumTreeType;
import com.waffleman0310.ancientmagicks.world.feature.gen.tree.WorldGenArcanocTree;
import com.waffleman0310.ancientmagicks.world.feature.gen.tree.WorldGenTimeTwistedTree;
import com.waffleman0310.ancientmagicks.world.feature.gen.tree.WorldGenYggdrasil;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockSapling extends AncientMagicksBush implements IGrowable {

	public static final PropertyEnum<EnumTreeType> VARIANT = PropertyEnum.create("variant", EnumTreeType.class, EnumTreeType.values());
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);

	public BlockSapling(String name) {
		super(name, Material.GRASS);
		setDefaultState(blockState.getBaseState().withProperty(VARIANT, EnumTreeType.TIME_TWISTED).withProperty(STAGE, 0));
		setTickRandomly(true);
	}

	public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random random) {
		if (!TerrainGen.saplingGrowTree(worldIn, random, pos)) return;

		WorldGenerator worldGenerator = new WorldGenTimeTwistedTree(true);

		IBlockState AIR = Blocks.AIR.getDefaultState();

		switch (state.getValue(VARIANT)) {

			case TIME_TWISTED:
				worldIn.setBlockState(pos, AIR);
				worldGenerator = new WorldGenTimeTwistedTree(true);
				break;
			case ARCANOC:
				worldIn.setBlockState(pos, AIR);
				worldGenerator = new WorldGenArcanocTree(true);
				break;
			case YGGDRASIL:
				worldIn.setBlockState(pos, AIR);
				worldGenerator = new WorldGenYggdrasil(true);
				break;
		}

		worldGenerator.generate(worldIn, random, pos);

	}

	@Nullable
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}


	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
		if (!worldIn.isRemote) {
			super.updateTick(worldIn, pos, state, random);
			if (canGrow(worldIn, pos, state, true)) {
				grow(worldIn, random, pos, state);
			}
		}
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		if (state.getValue(STAGE) == 0) {
			// Flag was 4
			worldIn.setBlockState(pos, state.cycleProperty(STAGE), 2);
		} else {
			generateTree(worldIn, pos, state, rand);
		}

	}

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return true;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(VARIANT).getMetadata();
	}

	@Override
	public void getSubBlocks(CreativeTabs tabs, NonNullList<ItemStack> list) {
		for (EnumTreeType type : EnumTreeType.values()) {
			list.add(new ItemStack(this, 1, type.getMetadata()));
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(VARIANT, EnumTreeType.byMetadata(meta & 7)).withProperty(STAGE, (meta & 8) >> 3);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = 0;
		meta = meta | state.getValue(VARIANT).getMetadata();
		meta = meta | state.getValue(STAGE) << 3;
		return meta;
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, VARIANT, STAGE);
	}
}
