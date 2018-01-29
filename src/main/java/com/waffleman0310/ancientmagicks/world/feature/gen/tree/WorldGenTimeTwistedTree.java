package com.waffleman0310.ancientmagicks.world.feature.gen.tree;

import com.waffleman0310.ancientmagicks.common.blocks.BlockLog;
import com.waffleman0310.ancientmagicks.init.Blocks;
import com.waffleman0310.ancientmagicks.util.helpers.TreeHelper;
import com.waffleman0310.ancientmagicks.variant.EnumTreeType;
import com.waffleman0310.ancientmagicks.world.feature.gen.base.WorldGenAncientMagicksTree;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class WorldGenTimeTwistedTree extends WorldGenAncientMagicksTree{

    public static final IBlockState WOOD = Blocks.LOG.getDefaultState().withProperty(BlockLog.VARIANT, EnumTreeType.TIME_TWISTED);
    public static final IBlockState LEAVES = Blocks.LOG.getDefaultState().withProperty(BlockLog.VARIANT, EnumTreeType.TIME_TWISTED);

    public WorldGenTimeTwistedTree(boolean notify) {
        super(notify);
    }

    @Override
    public int getMinTrunkHeight() {
        return 0;
    }

    @Override
    public int getMaxTrunkHeight() {
        return 0;
    }

    @Override
    public int getMinTrunkWidth() {
        return 0;
    }

    @Override
    public int getMaxTrunkWidth() {
        return 0;
    }

    @Override
    public int getMinCrownHeight(int trunkHeight, int trunkTopY) {
        return 0;
    }

    @Override
    public int getMaxCrownHeight(int trunkHeight, int trunkTopY) {
        return 0;
    }

    @Override
    public int getMinCrownRadius() {
        return 0;
    }

    @Override
    public int getMaxCrownRadius() {
        return 0;
    }

    @Override
    public int getTrunkCrownOverlap() {
        return 0;
    }

    @Override
    public int getMinRootfieldRadius() {
        return 0;
    }

    @Override
    public int getMaxRootfieldRadius() {
        return 0;
    }

    @Override
    public int getMinRootRadius() {
        return 0;
    }

    @Override
    public int getMaxRootRadius() {
        return 0;
    }

    @Override
    public int getMinRootDepth(BlockPos pos) {
        return 0;
    }

    @Override
    public int getMaxRootDepth(BlockPos pos) {
        return 0;
    }

    @Override
    public int getMinLeafRadius() {
        return 0;
    }

    @Override
    public int getMaxLeafRadius() {
        return 0;
    }

    @Override
    public int getTrunkRootOverlap() {
        return 0;
    }

    @Override
    public int getCrownNodes() {
        return 0;
    }

    @Override
    public int getCrownAttractionRadius() {
        return 0;
    }

    @Override
    public int getCrownRemoveRadius() {
        return 0;
    }

    @Override
    public int getCrownBranchLength() {
        return 0;
    }

    @Override
    public float getCrownSizeDecrement() {
        return 0;
    }

    @Override
    public int getRootNodes() {
        return 0;
    }

    @Override
    public int getRootAttractionRadius() {
        return 0;
    }

    @Override
    public int getRootRemovalRadius() {
        return 0;
    }

    @Override
    public int getRootBranchLength() {
        return 0;
    }

    @Override
    public float getLeafRoughness() {
        return 0;
    }

    @Override
    public float getRootSizeDecrement() {
        return 0;
    }

    @Override
    public String getTreeName() {
        return null;
    }

    @Override
    public EnumTrunkType getTrunkType() {
        return null;
    }

    @Override
    public TreeHelper.TreeShapeEnum getCrownShape() {
        return null;
    }

    @Override
    public TreeHelper.TreeShapeEnum getRootShape() {
        return null;
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
