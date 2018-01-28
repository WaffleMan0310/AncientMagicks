package com.waffleman0310.ancientmagicks.api.world.gen.feature.ore;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public interface IOreGenerator extends IWorldGenerator{

    IBlockState getOre();

    int getGenerationDimension();

    int getMaxHeight();

    int getMinHeight();

    int getMaxVeinSize();

    int getMinVeinSize();

    // Why? So I could implement my own generation algorithm in the future.
    default void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator generator, IChunkProvider provider) {
        if (world.provider.getDimension() == getGenerationDimension()) {
            WorldGenMinable oreGenerator = new WorldGenMinable(
                    getOre(),
                    MathHelper.getInt(random, getMinVeinSize(), getMaxVeinSize())
            );


            BlockPos oreLocation = new BlockPos(
                    (chunkX * 16) + MathHelper.getInt(random, 0, 16),
                    MathHelper.getInt(random, getMinHeight(), getMaxHeight()),
                    (chunkZ * 16) + MathHelper.getInt(random, 0, 16)
            );

            oreGenerator.generate(world, random, oreLocation);
        }
    }
}
