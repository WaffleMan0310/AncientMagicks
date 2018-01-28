package com.waffleman0310.ancientmagicks.common.blocks.base;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public abstract class AncientMagicksBush extends AncientMagicksBlock implements IPlantable{

    public AncientMagicksBush(String name, Material material) {
        super(name, material);
        setTickRandomly(false);
    }

    private boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
        return canSustainPlant(state, worldIn, pos.down(), EnumFacing.UP, this);
    }

    private void checkAndDrop(World worldIn, BlockPos pos, IBlockState state) {
        if (!canBlockStay(worldIn, pos, state)) {
            dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
        }
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(worldIn, pos, state, rand);
        checkAndDrop(worldIn, pos, state);
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        /*
        IBlockState state = worldIn.getBlockState(pos);
        return super.canPlaceBlockAt(worldIn, pos) && canSustainPlant(state, worldIn, pos, EnumFacing.UP, this);
        */
        return true;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Plains;
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        if (state != this) {
            return getDefaultState();
        } else {
            return state;
        }
    }
}
