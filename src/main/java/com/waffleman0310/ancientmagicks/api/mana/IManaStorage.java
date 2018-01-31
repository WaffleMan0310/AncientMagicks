package com.waffleman0310.ancientmagicks.api.mana;

public interface IManaStorage extends IMana {

	long recieveMana(long maxRecieve, float purity);

	long extractMana(long maxExtract);

	long getManaCapacity();

	long getManaStored();

	boolean canExtract();

	boolean canRecieve();
}
