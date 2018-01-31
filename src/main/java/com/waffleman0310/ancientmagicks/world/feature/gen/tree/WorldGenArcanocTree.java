package com.waffleman0310.ancientmagicks.world.feature.gen.tree;

import com.waffleman0310.ancientmagicks.common.blocks.BlockLeaves;
import com.waffleman0310.ancientmagicks.common.blocks.BlockLog;
import com.waffleman0310.ancientmagicks.init.Blocks;
import com.waffleman0310.ancientmagicks.util.helpers.TreeHelper;
import com.waffleman0310.ancientmagicks.variant.EnumTreeType;
import com.waffleman0310.ancientmagicks.world.feature.gen.base.WorldGenAncientMagicksTree;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class WorldGenArcanocTree extends WorldGenAncientMagicksTree{

    public static final IBlockState WOOD = Blocks.LOG.getDefaultState().withProperty(BlockLog.VARIANT, EnumTreeType.ARCANOC);
    public static final IBlockState LEAVES = Blocks.LEAVES.getDefaultState().withProperty(BlockLeaves.VARIANT, EnumTreeType.ARCANOC);

    public WorldGenArcanocTree(boolean notify) {
        super(notify);
    }

    @Override
    public int getMinTrunkHeight() {
        return 30;
    }

    @Override
    public int getMaxTrunkHeight() {
        return 60;
    }

    @Override
    public int getMinTrunkWidth() {
        return 20;
    }

    @Override
    public int getMaxTrunkWidth() {
        return 30;
    }

    @Override
    public int getMinCrownHeight(int trunkHeight, int trunkTopY) {
        return 128 - (getMaxLeafRadius() * 2) - trunkHeight;
    }

    @Override
    public int getMaxCrownHeight(int trunkHeight, int trunkTopY) {
        return 128 - (getMaxLeafRadius() * 2) - trunkHeight;
    }

    @Override
    public int getMinCrownRadius() {
        return 70;
    }

    @Override
    public int getMaxCrownRadius() {
        return 100;
    }

    @Override
    public int getTrunkCrownOverlap() {
        return 10;
    }

    @Override
    public int getMinRootfieldRadius() {
        return 70;
    }

    @Override
    public int getMaxRootfieldRadius() {
        return 100;
    }

    @Override
    public int getMinRootRadius() {
        return 2;
    }

    @Override
    public int getMaxRootRadius() {
        return 5;
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
    public int getMinLeafRadius() {
        return 5;
    }

    @Override
    public int getMaxLeafRadius() {
        return 8;
    }

    @Override
    public int getTrunkRootOverlap() {
        return 10;
    }

    @Override
    public int getCrownNodes() {
        return 1000;
    }

    @Override
    public int getCrownAttractionRadius() {
        return 35;
    }

    @Override
    public int getCrownRemoveRadius() {
        return 2;
    }

    @Override
    public int getCrownBranchLength() {
        return 2;
    }

    @Override
    public float getCrownSizeDecrement() {
        return 0.03f;
    }

    @Override
    public int getRootNodes() {
        return 1000;
    }

    @Override
    public int getRootAttractionRadius() {
        return 30;
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
        return 0.2f;
    }

    @Override
    public float getRootSizeDecrement() {
        return 0.008f;
    }

    @Override
    public String getTreeName() {
        return "arcanoc";
    }

    @Override
    public EnumTrunkType getTrunkType() {
        return EnumTrunkType.MULTITRUNK;
    }

    @Override
    public TreeHelper.TreeShapeEnum getCrownShape() {
        return TreeHelper.TreeShapeEnum.SEMISPHERE;
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
