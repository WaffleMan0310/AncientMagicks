package com.waffleman0310.ancientmagicks.common.items;

import com.waffleman0310.ancientmagicks.common.items.base.AncientMagicksItem;
import com.waffleman0310.ancientmagicks.variant.EnumMetalType;
import com.waffleman0310.ancientmagicks.util.AncientMagicksUtil;
import com.waffleman0310.ancientmagicks.util.AncientMagicksUtil.EnumResourcePrefix;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.List;

public class ItemMetal extends AncientMagicksItem {

    public enum EnumMetalForm {
        INGOT, NUGGET
    }

    public ItemMetal(String name) {
        super(name);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        String name;
        int meta = stack.getMetadata();
        if (meta < EnumMetalType.values().length) {
            EnumMetalType type = EnumMetalType.byMetadata(meta);
            name = AncientMagicksUtil.formatUnlocalizedName(
                    EnumResourcePrefix.ITEM,
                    String.format("%s.%s",
                            EnumMetalForm.INGOT.name().toLowerCase(),
                            type.getUnlocalizedName()
                    )
            );
        } else {
            meta = stack.getMetadata() - EnumMetalType.values().length;
            EnumMetalType type = EnumMetalType.byMetadata(meta);
            name = AncientMagicksUtil.formatUnlocalizedName(
                    EnumResourcePrefix.ITEM,
                    String.format("%s.%s",
                            EnumMetalForm.NUGGET.name().toLowerCase(),
                            type.getUnlocalizedName()
                    )
            );
        }
        return name;
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public void getSubItems(CreativeTabs tabs, NonNullList<ItemStack> list) {
        for (EnumMetalForm form : EnumMetalForm.values()) {
            for (EnumMetalType metal : EnumMetalType.values()) {
                list.add(new ItemStack(this, 1, metal.getMetadata() + (form.ordinal() * EnumMetalType.values().length)));
            }
        }
    }

    public static int formatMetadata(EnumMetalForm form, EnumMetalType type) {
        int meta;
        if (form == EnumMetalForm.INGOT) {
            meta = type.getMetadata();
        } else {
            meta = EnumMetalType.values().length + type.getMetadata();
        }
        return meta;
    }
}
