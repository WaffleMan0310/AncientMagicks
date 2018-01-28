package com.waffleman0310.ancientmagicks.api.school.resource;

import com.waffleman0310.ancientmagicks.api.school.ISchool;
import net.minecraft.client.renderer.EnumFaceDirection;
import net.minecraft.util.EnumFacing;

public interface IResourceReciever {

    long recieveResource(EnumFacing side, long maxRecieve);

}
