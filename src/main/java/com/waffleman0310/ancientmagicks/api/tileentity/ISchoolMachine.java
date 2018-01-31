package com.waffleman0310.ancientmagicks.api.tileentity;

import com.waffleman0310.ancientmagicks.api.school.ISchool;
import com.waffleman0310.ancientmagicks.api.school.resource.IResourceReciever;
import com.waffleman0310.ancientmagicks.api.school.resource.ResourceStorage;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public interface ISchoolMachine<T extends ISchool> extends ITickable, IResourceReciever {

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

    default ISchool getResourceSchool() {
        return getResourceStorage().getResourceSchool();
    }

    default void consumeResource(long toConsume) {
        setResourceStored(getResourceStorage().getResourceStored() - toConsume);
    }

    // Strictly for Server -> Client Communication
    default void setResourceStored(long resource) {
        getResourceStorage().setResourceStored(resource);
    }

    @Override
    default long recieveResource(EnumFacing side, long maxRecieve) {
        return getResourceStorage().recieveResource(maxRecieve);
    }
}
