package com.waffleman0310.ancientmagicks.api.world.gen.feature.tree;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface ITreeGenerator {

    enum EnumGenerationType {
        WOOD, LEAVES, ROOTS
    }

    void placeLeavesAt(World worldIn, BlockPos pos);

    void placeWoodAt(World worldIn, BlockPos pos);

    default void placeRootsAt(World worldIn, BlockPos pos) {}
}
