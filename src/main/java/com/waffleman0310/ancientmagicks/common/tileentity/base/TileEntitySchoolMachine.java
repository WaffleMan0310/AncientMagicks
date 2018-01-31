package com.waffleman0310.ancientmagicks.common.tileentity.base;

import com.waffleman0310.ancientmagicks.api.school.ISchool;
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

public abstract class TileEntitySchoolMachine<K extends ISchool> extends TileEntityMachine implements ISchoolMachine<K> {

	protected ResourceStorage<K> resourceStorage;

	public TileEntitySchoolMachine(K school, int inventorySlots, long capacity, long maxRecieve) {
		super(inventorySlots);
		resourceStorage = new ResourceStorage<>(school, capacity, 0, maxRecieve);
	}

	@Override
	public void update() {
		sendResourceDataToClient(this.world, this.pos, this.resourceStorage);
	}

	protected static <N extends ISchool> void sendResourceDataToClient(World worldIn, BlockPos pos, ResourceStorage<N> resourceStorage) {
		// Implement
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
			return CapabilityResource.RESOURCE.cast(new IResourceStorage<ISchool>() {
				@Override
				public ISchool getResourceSchool() {
					return TileEntitySchoolMachine.this.getResourceSchool();
				}

				@Override
				public long extractMana(long maxRecieve) {
					return 0;
				}

				@Override
				public long recieveResource(long maxRecieve) {
					return TileEntitySchoolMachine.this.recieveResource(facing, maxRecieve);
				}

				@Override
				public long getResourceCapacity() {
					return TileEntitySchoolMachine.this.getResourceCapacity();
				}

				@Override
				public long getResourceStored() {
					return TileEntitySchoolMachine.this.getResourceStored();
				}

				@Override
				public boolean canExtract() {
					return false;
				}

				@Override
				public boolean canRecieve() {
					return true;
				}
			});
		}
		return super.getCapability(capability, facing);
	}
}
