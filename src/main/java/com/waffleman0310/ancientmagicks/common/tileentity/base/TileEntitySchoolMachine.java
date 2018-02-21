package com.waffleman0310.ancientmagicks.common.tileentity.base;

import com.waffleman0310.ancientmagicks.api.school.School;
import com.waffleman0310.ancientmagicks.api.school.resource.CapabilityResource;
import com.waffleman0310.ancientmagicks.api.school.resource.IResourceStorage;
import com.waffleman0310.ancientmagicks.api.school.resource.ResourceStorage;
import com.waffleman0310.ancientmagicks.api.tileentity.ISchoolMachine;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public abstract class TileEntitySchoolMachine<K extends School> extends TileEntityMachine implements ISchoolMachine<K> {

	protected ResourceStorage<K> resourceStorage;

	public TileEntitySchoolMachine(K school, int inventorySlots, long capacity, long maxRecieve) {
		super(inventorySlots);
		resourceStorage = new ResourceStorage<>(school, capacity, 0, maxRecieve);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		resourceStorage.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		resourceStorage.writeToNBT(compound);
		return super.writeToNBT(compound);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return (capability == CapabilityResource.RESOURCE || super.hasCapability(capability, facing));
	}

	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if (capability == CapabilityResource.RESOURCE) {
			return (T) this.resourceStorage;
		}
		return super.getCapability(capability, facing);
	}
}
