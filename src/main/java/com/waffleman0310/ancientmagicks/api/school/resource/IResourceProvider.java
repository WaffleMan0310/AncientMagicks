package com.waffleman0310.ancientmagicks.api.school.resource;

import net.minecraft.util.EnumFacing;

public interface IResourceProvider {

    long extractResource(EnumFacing side, long maxExtract);
}
