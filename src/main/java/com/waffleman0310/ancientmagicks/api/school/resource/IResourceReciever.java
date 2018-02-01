package com.waffleman0310.ancientmagicks.api.school.resource;

import net.minecraft.util.EnumFacing;

public interface IResourceReciever {

	long recieveResource(EnumFacing side, long maxRecieve);

}
