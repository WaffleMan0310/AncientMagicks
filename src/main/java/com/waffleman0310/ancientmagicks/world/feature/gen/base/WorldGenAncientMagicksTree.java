package com.waffleman0310.ancientmagicks.world.feature.gen.base;

import com.waffleman0310.ancientmagicks.api.world.gen.feature.tree.ISpacialColTreeGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public abstract class WorldGenAncientMagicksTree extends WorldGenAbstractTree implements ISpacialColTreeGenerator{

    public WorldGenAncientMagicksTree(boolean notify) {
        super(notify);
    }
    @Override
    public boolean generate(World worldIn, Random rand, BlockPos pos) {
        return this.generateTree(worldIn, rand, pos);
    }
}
