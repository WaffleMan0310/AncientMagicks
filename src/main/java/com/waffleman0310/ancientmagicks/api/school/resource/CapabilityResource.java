package com.waffleman0310.ancientmagicks.api.school.resource;

import com.waffleman0310.ancientmagicks.api.school.ISchool;
import com.waffleman0310.ancientmagicks.api.school.schools.SchoolGeneral;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class CapabilityResource {

    @CapabilityInject(IResourceStorage.class)
    public static Capability<IResourceStorage<ISchool>> RESOURCE = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(
                IResourceStorage.class,
                new Capability.IStorage<IResourceStorage>() {
                    @Nullable
                    @Override
                    public NBTBase writeNBT(Capability<IResourceStorage> capability, IResourceStorage instance, EnumFacing side) {
                        return new NBTTagLong(instance.getResourceStored());
                    }

                    @Override
                    public void readNBT(Capability<IResourceStorage> capability, IResourceStorage instance, EnumFacing side, NBTBase nbt) {
                        ((ResourceStorage) instance).resource = ((NBTTagLong) nbt).getLong();
                    }
                },
                () -> new ResourceStorage<>(SchoolGeneral.INSTANCE, 1000)

        );
    }
}
