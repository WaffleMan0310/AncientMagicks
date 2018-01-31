package com.waffleman0310.ancientmagicks.common.items.itemblock;

import com.waffleman0310.ancientmagicks.common.blocks.base.AncientMagicksBlock;
import com.waffleman0310.ancientmagicks.common.items.base.AncientMagicksItemBlock;
import com.waffleman0310.ancientmagicks.variant.EnumTreeType;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockLeaves extends AncientMagicksItemBlock {

	public ItemBlockLeaves(AncientMagicksBlock block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		EnumTreeType type = EnumTreeType.byMetadata(stack.getMetadata());
		return String.format("%s.%s", Block.getBlockFromItem(stack.getItem()).getUnlocalizedName(), type.getUnlocalizedName());
	}
}
