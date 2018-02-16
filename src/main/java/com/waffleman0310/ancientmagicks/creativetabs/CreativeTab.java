package com.waffleman0310.ancientmagicks.creativetabs;

import com.waffleman0310.ancientmagicks.api.util.AncientMagicksUtil;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;


public class CreativeTab {

	public static final net.minecraft.creativetab.CreativeTabs ancientMagicksTab = new net.minecraft.creativetab.CreativeTabs(AncientMagicksUtil.modId) {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(Items.BAKED_POTATO);
		}
	};
}
