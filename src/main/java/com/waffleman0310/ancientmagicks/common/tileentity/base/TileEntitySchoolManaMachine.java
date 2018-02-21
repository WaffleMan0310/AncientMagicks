package com.waffleman0310.ancientmagicks.common.tileentity.base;

import com.waffleman0310.ancientmagicks.api.mana.IMana;
import com.waffleman0310.ancientmagicks.api.mana.ManaStorage;
import com.waffleman0310.ancientmagicks.api.school.School;
import com.waffleman0310.ancientmagicks.api.school.resource.ResourceStorage;
import com.waffleman0310.ancientmagicks.api.tileentity.IManaMachine;
import com.waffleman0310.ancientmagicks.api.tileentity.ISchoolMachine;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class TileEntitySchoolManaMachine<K extends School> extends TileEntityMachine implements IManaMachine, ISchoolMachine<K> {

	protected ManaStorage manaStorage;
	protected ResourceStorage<K> resourceStorage;

	public TileEntitySchoolManaMachine(int inventorySlots,
	                                   long manaCapacity,
	                                   long maxManaRecieve,
	                                   IMana.EnumManaType manaType,
	                                   K school,
	                                   long resourceCapacity,
	                                   long maxResourceRecieve
	) {
		super(inventorySlots);
		manaStorage = new ManaStorage(manaCapacity, 0, maxManaRecieve, manaType);
		resourceStorage = new ResourceStorage<>(school, resourceCapacity, 0, maxResourceRecieve);
	}

	@Override
	public ManaStorage getManaStorage() {
		return this.manaStorage;
	}

	@Override
	public ResourceStorage<K> getResourceStorage() {
		return this.resourceStorage;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		manaStorage.readFromNBT(compound);
		resourceStorage.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		manaStorage.writeToNBT(compound);
		resourceStorage.writeToNBT(compound);
		return super.writeToNBT(compound);
	}
}
