package com.waffleman0310.ancientmagicks.api.tileentity;

import com.waffleman0310.ancientmagicks.api.mana.IMana;
import com.waffleman0310.ancientmagicks.api.mana.IManaReciever;
import com.waffleman0310.ancientmagicks.api.mana.ManaStorage;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public interface IManaMachine extends ITickable, IManaReciever {

    ManaStorage getManaStorage();

    default long getManaStored() {
        return getManaStorage().getManaStored();
    }

    default long getManaCapacity() {
        return getManaStorage().getManaCapacity();
    }

    default long getManaSpace() {
        return getManaStorage().getManaCapacity() - getManaStorage().getManaStored();
    }

    default IMana.EnumManaType getManaType() {
        return getManaStorage().getManaType();
    }

    default float getManaPurity() {
        return getManaStorage().getManaPurity();
    }

    default float getPurityModifier() {
        return getManaStorage().getPurityModifier();
    }

    default void consumeMana(long toConsume) {
        setManaStored(Math.round(getManaStored() - (getPurityModifier() * toConsume)));
    }

    // Strictly for Server -> Client Communication
    default void setManaStored(long mana) {
        getManaStorage().setManaStored(mana);
    }

    default String getManaUnlocalizedName() {
        return getManaStorage().getUnlocalizedName();
    }

    default String getManaPurityUnlocalizedName() {
        return getManaStorage().getPurityUnlocalizedName();
    }

    @Override
    default long recieveMana(EnumFacing side, long maxRecieve, float purity) {
        return getManaStorage().recieveMana(maxRecieve, purity);
    }
}
