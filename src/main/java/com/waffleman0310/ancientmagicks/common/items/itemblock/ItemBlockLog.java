package com.waffleman0310.ancientmagicks.common.items.itemblock;

import com.waffleman0310.ancientmagicks.common.blocks.base.AncientMagicksBlock;
import com.waffleman0310.ancientmagicks.variant.EnumTreeType;
import com.waffleman0310.ancientmagicks.common.items.base.AncientMagicksItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockLog extends AncientMagicksItemBlock{

    public ItemBlockLog(AncientMagicksBlock block) {
        super(block);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        // Implement
        return super.getItemBurnTime(itemStack);
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
