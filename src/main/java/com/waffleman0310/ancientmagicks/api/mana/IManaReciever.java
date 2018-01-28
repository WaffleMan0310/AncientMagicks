package com.waffleman0310.ancientmagicks.api.mana;

import net.minecraft.util.EnumFacing;

public interface IManaReciever {

    long recieveMana(EnumFacing side, long maxRecieve, float purity);
}
