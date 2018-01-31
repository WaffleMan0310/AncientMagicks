package com.waffleman0310.ancientmagicks.api.research;

import com.waffleman0310.ancientmagicks.schools.EnumSchool;
import com.waffleman0310.ancientmagicks.util.AncientMagicksUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IResearchable<T extends Item> {

    // Will be implemented by items to show that they are researchable, and provides the information needed to make the object a target to research
    // Needs to use metadata to determine that it is evaluating the correct item/itemblock

    enum NodeType {
        LESSAR, NORMAL, GREATER
    }

    NodeType getNodeType(ItemStack stack);

    EnumSchool getSchool(ItemStack stack);

    int getKnowledgeLevels(ItemStack stack);

    T getItem();
}
