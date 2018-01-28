package com.waffleman0310.ancientmagicks.common.items;

import com.waffleman0310.ancientmagicks.common.items.base.AncientMagicksItem;
import com.waffleman0310.ancientmagicks.variant.EnumReagentType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.List;

public class ItemReagent extends AncientMagicksItem {

    public ItemReagent(String name) {
        super(name);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        // Implement
        return super.getItemBurnTime(itemStack);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        EnumReagentType type = EnumReagentType.byMetadata(stack.getMetadata());
        return String.format("%s.%s",
                super.getUnlocalizedName(stack),
                type.getUnlocalizedName()
        );
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        for (EnumReagentType reagent : EnumReagentType.values()) {
            subItems.add(new ItemStack(this, 1, reagent.getMetadata()));
        }
    }
}
