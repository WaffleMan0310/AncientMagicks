package com.waffleman0310.ancientmagicks.world.feature.gen.tree;

import com.waffleman0310.ancientmagicks.common.blocks.BlockLeaves;
import com.waffleman0310.ancientmagicks.common.blocks.BlockLog;
import com.waffleman0310.ancientmagicks.variant.EnumTreeType;
import com.waffleman0310.ancientmagicks.init.Blocks;
import com.waffleman0310.ancientmagicks.util.helpers.TreeHelper;
import com.waffleman0310.ancientmagicks.world.feature.gen.base.WorldGenAncientMagicksTree;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class WorldGenYggdrasil extends WorldGenAncientMagicksTree {

	public static final IBlockState WOOD = Blocks.LOG.getDefaultState().withProperty(BlockLog.VARIANT, EnumTreeType.YGGDRASIL);
	public static final IBlockState LEAVES = Blocks.LEAVES.getDefaultState().withProperty(BlockLeaves.VARIANT, EnumTreeType.YGGDRASIL);

	public WorldGenYggdrasil(boolean notify) {
		super(notify);
	}

	@Override
	public int getMinTrunkHeight() {
		return 80;
	}

	@Override
	public int getMaxTrunkHeight() {
		return 100;
	}

	@Override
	public int getMinTrunkWidth() {
		return 48;
	}

	@Override
	public int getMaxTrunkWidth() {
		return 56;
	}

	@Override
	public int getMinCrownHeight(int trunkHeight, int trunkTopY) {
		return (256 - (getMaxLeafRadius() * 2) - trunkHeight);
	}

	@Override
	public int getMaxCrownHeight(int trunkHeight, int trunkTopY) {
		return (256 - (getMaxLeafRadius() * 2) - trunkHeight);
	}

	@Override
	public int getMinRootRadius() {
		return 7;
	}

	@Override
	public int getMaxRootRadius() {
		return 10;
	}

	@Override
	public int getMinRootDepth(BlockPos pos) {
		return pos.getY();
	}

	@Override
	public int getMaxRootDepth(BlockPos pos) {
		return pos.getY();
	}

	@Override
	public int getMinCrownRadius() {
		return 200;
	}

	@Override
	public int getMaxCrownRadius() {
		return 256;
	}

	@Override
	public int getMinRootfieldRadius() {
		return 200;
	}

	@Override
	public int getMaxRootfieldRadius() {
		return 256;
	}

	@Override
	public int getMinLeafRadius() {
		return 6;
	}

	@Override
	public int getMaxLeafRadius() {
		return 12;
	}

	@Override
	public int getTrunkCrownOverlap() {
		return 20;
	}

	@Override
	public int getTrunkRootOverlap() {
		return 30;
	}

	@Override
	public int getCrownNodes() {
		return 2000;
	}

	@Override
	public int getCrownAttractionRadius() {
		return 35;
	}

	@Override
	public int getCrownRemoveRadius() {
		return 5;
	}

	@Override
	public int getCrownBranchLength() {
		return 2;
	}

	@Override
	public float getCrownSizeDecrement() {
		return 0.015f;
	}

	@Override
	public int getRootNodes() {
		return 2000;
	}

	@Override
	public int getRootAttractionRadius() {
		return 50;
	}

	@Override
	public int getRootRemovalRadius() {
		return 10;
	}

	@Override
	public int getRootBranchLength() {
		return 2;
	}

	@Override
	public float getRootSizeDecrement() {
		return 0.01f;
	}


	@Override
	public float getLeafRoughness() {
		return 0.2f;
	}

	@Override
	public String getTreeName() {
		return "yggdrasil";
	}

	@Override
	public EnumTrunkType getTrunkType() {
		return EnumTrunkType.MULTITRUNK;
	}

	@Override
	public TreeHelper.TreeShapeEnum getCrownShape() {
		return TreeHelper.TreeShapeEnum.SPHERICAL;
	}

	@Override
	public TreeHelper.TreeShapeEnum getRootShape() {
		return TreeHelper.TreeShapeEnum.SEMISPHERE;
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
