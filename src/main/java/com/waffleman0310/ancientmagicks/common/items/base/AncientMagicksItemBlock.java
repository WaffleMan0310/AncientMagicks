package com.waffleman0310.ancientmagicks.common.items.base;

import com.waffleman0310.ancientmagicks.common.blocks.base.AncientMagicksBlock;
import com.waffleman0310.ancientmagicks.creativetabs.CreativeTab;
import com.waffleman0310.ancientmagicks.api.util.AncientMagicksUtil;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class AncientMagicksItemBlock extends ItemBlock {

	public AncientMagicksItemBlock(AncientMagicksBlock block) {
		super(block);
		setCreativeTab(CreativeTab.ancientMagicksTab);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return getBlock().getUnlocalizedName();
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return AncientMagicksUtil.localize(AncientMagicksUtil.EnumResourceSuffix.NAME, getUnlocalizedName(stack));
	}
}
