package com.waffleman0310.ancientmagicks.world.feature.gen.tree;

import com.waffleman0310.ancientmagicks.api.world.gen.feature.tree.ISpacialColTwistedGenerator;
import com.waffleman0310.ancientmagicks.common.blocks.BlockLog;
import com.waffleman0310.ancientmagicks.init.Blocks;
import com.waffleman0310.ancientmagicks.api.util.helpers.TreeHelper.EnumTreeShape;
import com.waffleman0310.ancientmagicks.variant.EnumTreeType;
import com.waffleman0310.ancientmagicks.world.feature.gen.base.WorldGenAncientMagicksTree;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenTimeTwistedTree extends WorldGenAncientMagicksTree implements ISpacialColTwistedGenerator {

	public static final IBlockState WOOD = Blocks.LOG.getDefaultState().withProperty(BlockLog.VARIANT, EnumTreeType.TIME_TWISTED);
	public static final IBlockState LEAVES = Blocks.LEAVES.getDefaultState().withProperty(BlockLog.VARIANT, EnumTreeType.TIME_TWISTED);

	public WorldGenTimeTwistedTree(boolean notify) {
		super(notify);
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		return this.generateTree(worldIn, rand, position);
	}

	@Override
	public int getMinCurves() {
		return 2;
	}

	@Override
	public int getMaxCurves() {
		return 2;
	}

	@Override
	public int getMinCurveAmplitude() {
		return 5;
	}

	@Override
	public int getMaxCurveAmplitude() {
		return 8;
	}

	@Override
	public int getMinTrunkHeight() {
		return 25;
	}

	@Override
	public int getMaxTrunkHeight() {
		return 35;
	}

	@Override
	public int getMinTrunkWidth() {
		return 8;
	}

	@Override
	public int getMaxTrunkWidth() {
		return 12;
	}

	@Override
	public int getMinCrownHeight(int trunkHeight, int trunkTopY) {
		return 20;
	}

	@Override
	public int getMaxCrownHeight(int trunkHeight, int trunkTopY) {
		return 32;
	}

	@Override
	public int getMinCrownDiameter() {
		return 40;
	}

	@Override
	public int getMaxCrownDiameter() {
		return 55;
	}

	@Override
	public int getTrunkCrownOverlap() {
		return 3;
	}

	@Override
	public int getMinRootfieldDiameter() {
		return 45;
	}

	@Override
	public int getMaxRootfieldDiameter() {
		return 60;
	}

	@Override
	public int getMinRootDepth(BlockPos pos) {
		return 20;
	}

	@Override
	public int getMaxRootDepth(BlockPos pos) {
		return 30;
	}

	@Override
	public int getMinLeafDiameter() {
		return 6;
	}

	@Override
	public int getMaxLeafDiameter() {
		return 10;
	}

	@Override
	public int getTrunkRootOverlap() {
		return 7;
	}

	@Override
	public int getCrownNodes() {
		return 300;
	}

	@Override
	public int getCrownAttractionRadius() {
		return 10;
	}

	@Override
	public int getCrownRemoveRadius() {
		return 3;
	}

	@Override
	public int getCrownBranchLength() {
		return 2;
	}

	@Override
	public float getCrownSizeDecrement() {
		return 0.027f;
	}

	@Override
	public int getRootNodes() {
		return 500;
	}

	@Override
	public int getRootAttractionRadius() {
		return 15;
	}

	@Override
	public int getRootRemovalRadius() {
		return 5;
	}

	@Override
	public int getRootBranchLength() {
		return 2;
	}

	@Override
	public float getLeafRoughness() {
		return 0.15f;
	}

	@Override
	public float getRootSizeDecrement() {
		return 0.01f;
	}

	@Override
	public String getTreeName() {
		return "time_twisted";
	}

	@Override
	public EnumTreeShape getCrownShape() {
		return EnumTreeShape.SEMISPHERE;
	}

	@Override
	public EnumTreeShape getRootShape() {
		return EnumTreeShape.SEMISPHERE;
	}

	@Override
	public void placeLeavesAt(World worldIn, BlockPos pos) {
		if (worldIn.getBlockState(pos) != LEAVES) {
			setBlockAndNotifyAdequately(worldIn, pos, LEAVES);
		}
	}

	@Override
	public void placeWoodAt(World worldIn, BlockPos pos) {
		if (worldIn.getBlockState(pos) != WOOD) {
			setBlockAndNotifyAdequately(worldIn, pos, WOOD);
		}
	}
}
