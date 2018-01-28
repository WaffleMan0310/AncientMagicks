package com.waffleman0310.ancientmagicks.common.blocks;

import com.waffleman0310.ancientmagicks.common.blocks.base.AncientMagicksBlock;
import com.waffleman0310.ancientmagicks.variant.EnumMetalType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.List;

public class BlockMetal extends AncientMagicksBlock{

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumMetalType.class);

    public BlockMetal(String name) {
        super(name, Material.ANVIL);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return ((EnumMetalType) state.getValue(VARIANT)).getMetadata();
    }

    @Override
    public void getSubBlocks(CreativeTabs tabs, NonNullList<ItemStack> list) {
        for (EnumMetalType type : EnumMetalType.values()) {
           list.add(new ItemStack(this, 1, type.getMetadata()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(VARIANT, EnumMetalType.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumMetalType) state.getValue(VARIANT)).getMetadata();
    }

    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT);
    }
}
