package com.waffleman0310.ancientmagicks.api.tileentity;

import com.waffleman0310.ancientmagicks.api.school.School;
import com.waffleman0310.ancientmagicks.api.school.resource.IResourceReciever;
import com.waffleman0310.ancientmagicks.api.school.resource.ResourceStorage;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public interface ISchoolMachine<T extends School> extends ITickable, IResourceReciever {

	ResourceStorage<T> getResourceStorage();

	default long getResourceCapacity() {

		return getResourceStorage().getResourceCapacity();
	}

	default long getResourceStored() {

		return getResourceStorage().getResourceStored();
	}

	default long getResourceSpace() {

		return getResourceStorage().getResourceCapacity() - getResourceStorage().getResourceStored();
	}

	default School getResourceSchool() {
		return getResourceStorage().getResourceSchool();
	}

	default void consumeResource(long toConsume) {
		setResourceStored(getResourceStorage().getResourceStored() - toConsume);
	}

	default void setResourceStored(long stored) {
		getResourceStorage().setResourceStored(stored);
	}

	@Override
	default long recieveResource(EnumFacing side, long maxRecieve) {
		return getResourceStorage().recieveResource(maxRecieve);
	}
}
