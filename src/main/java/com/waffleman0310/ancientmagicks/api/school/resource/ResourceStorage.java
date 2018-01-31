package com.waffleman0310.ancientmagicks.api.school.resource;

import com.waffleman0310.ancientmagicks.api.school.ISchool;
import net.minecraft.nbt.NBTTagCompound;

public class ResourceStorage<T extends ISchool> implements IResourceStorage<T> {

	protected T school;

	protected long resource;
	protected long capacity;
	protected long maxExtract;
	protected long maxRecieve;

	public ResourceStorage(T school, long capacity) {
		this(school, capacity, capacity, capacity);
	}

	public ResourceStorage(T school, long capacity, long maxTransfer) {
		this(school, capacity, maxTransfer, maxTransfer);
	}

	public ResourceStorage(T school, long capacity, long maxRecieve, long maxExtract) {
		this.school = school;
		this.capacity = capacity;
		this.maxRecieve = maxRecieve;
		this.maxExtract = maxExtract;
	}

	@Override
	public T getResourceSchool() {
		return school;
	}

	@Override
	public long extractMana(long maxExtract) {
		if (!canExtract()) {
			return 0;
		}

		long resourceExtracted = Math.min(this.resource, Math.min(this.maxExtract, maxExtract));
		this.resource -= resourceExtracted;
		return resourceExtracted;
	}

	@Override
	public long recieveResource(long maxRecieve) {
		if (!canRecieve()) {
			return 0;
		}

		long resourceRecieved = Math.min(this.capacity - this.resource, Math.min(this.maxRecieve, maxRecieve));
		this.resource += resourceRecieved;
		return resourceRecieved;
	}

	@Override
	public long getResourceCapacity() {
		return this.capacity;
	}

	@Override
	public long getResourceStored() {
		return this.resource;
	}

	@Override
	public boolean canExtract() {
		return this.maxExtract > 0;
	}

	@Override
	public boolean canRecieve() {
		return this.maxRecieve > 0;
	}

	public void setMaxTransfer(long maxTransfer) {
		if (maxTransfer > 0) {
			this.maxRecieve = maxTransfer;
			this.maxExtract = maxTransfer;
		} else {
			this.maxRecieve = 0;
			this.maxExtract = 0;
		}
	}

	public void setMaxRecieve(long maxRecieve) {
		if (maxRecieve > 0) {
			this.maxRecieve = maxRecieve;
		} else {
			this.maxRecieve = 0;
		}
	}

	public void setMaxExtract(long maxExtract) {
		if (maxExtract > 0) {
			this.maxExtract = maxExtract;
		} else {
			this.maxExtract = 0;
		}
	}

	public void setCapacity(long capacity) {
		if (capacity > 0) {
			this.capacity = capacity;
		} else {
			this.capacity = 0;
		}
	}

	// Strictly for Client -> Server Communication
	public void setResourceStored(long resource) {
		if (resource > capacity) {
			this.resource = capacity;
		} else if (resource < 0) {
			this.resource = 0;
		} else {
			this.resource = resource;
		}
	}

	public String getUnlocalizedResourceName() {
		return this.school.getUnlocalizedResourceName();
	}

	public String getUnlocalizedSchoolName() {
		return this.school.getUnlocalizedName();
	}

	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setLong(this.school.getResourceName(), this.resource);
		return compound;
	}

	public void readFromNBT(NBTTagCompound compound) {
		this.resource = compound.getLong(this.school.getResourceName());
	}
}
