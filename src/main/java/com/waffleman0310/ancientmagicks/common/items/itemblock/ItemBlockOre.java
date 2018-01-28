package com.waffleman0310.ancientmagicks.common.items.itemblock;

import com.waffleman0310.ancientmagicks.common.blocks.base.AncientMagicksBlock;
import com.waffleman0310.ancientmagicks.variant.EnumOreType;
import com.waffleman0310.ancientmagicks.common.items.base.AncientMagicksItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockOre extends AncientMagicksItemBlock{

    public ItemBlockOre(AncientMagicksBlock block) {
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
        EnumOreType type = EnumOreType.byMetadata(stack.getMetadata());
        return String.format("%s.%s", Block.getBlockFromItem(stack.getItem()).getUnlocalizedName(), type.getUnlocalizedName());
    }
}
