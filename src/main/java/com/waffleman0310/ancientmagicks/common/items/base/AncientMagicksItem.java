package com.waffleman0310.ancientmagicks.common.items.base;

import com.waffleman0310.ancientmagicks.creativetabs.CreativeTab;
import com.waffleman0310.ancientmagicks.util.AncientMagicksUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class AncientMagicksItem extends Item{

    private String name;

    public AncientMagicksItem(String name) {
        this.name = name;
        setCreativeTab(CreativeTab.ancientMagicksTab);

    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return AncientMagicksUtil.formatUnlocalizedName(AncientMagicksUtil.EnumResourcePrefix.ITEM, getName());
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return AncientMagicksUtil.localize(AncientMagicksUtil.EnumResourceSuffix.NAME, getUnlocalizedName(stack));
    }

    public String getName() {
        return name;
    }
}
