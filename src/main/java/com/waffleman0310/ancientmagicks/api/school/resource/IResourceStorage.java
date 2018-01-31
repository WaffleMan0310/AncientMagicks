package com.waffleman0310.ancientmagicks.api.school.resource;

import com.waffleman0310.ancientmagicks.api.school.ISchool;

public interface IResourceStorage<T extends ISchool> {

	T getResourceSchool();

	long extractMana(long maxRecieve);

	long recieveResource(long maxRecieve);

	long getResourceCapacity();

	long getResourceStored();

	boolean canExtract();

	boolean canRecieve();
}
