package com.waffleman0310.ancientmagicks.common.items;

import com.waffleman0310.ancientmagicks.common.items.base.AncientMagicksSeed;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

import java.util.Random;

public class ItemSeed extends AncientMagicksSeed implements IGrowable{

    public ItemSeed(String name) {
        super(name);
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return false;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return false;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {

    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return null;
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        return null;
    }
}
