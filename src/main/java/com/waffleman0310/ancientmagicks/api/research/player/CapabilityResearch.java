package com.waffleman0310.ancientmagicks.api.research.player;

import net.minecraft.nbt.*;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityResearch implements ICapabilitySerializable {

	@CapabilityInject(IPlayerResearch.class)
	public static Capability<IPlayerResearch> RESEARCH = null;

	private IPlayerResearch instance = RESEARCH.getDefaultInstance();

	public static void register() {
		CapabilityManager.INSTANCE.register(
				IPlayerResearch.class,
				new IStorage<IPlayerResearch>() {
					@Nullable
					@Override
					public NBTBase writeNBT(Capability<IPlayerResearch> capability, IPlayerResearch instance, EnumFacing side) {
						long startTime = System.currentTimeMillis();
						NBTTagCompound schoolResearch = new NBTTagCompound();
						instance.getMasterList().forEach((school, researchList) -> {
							NBTTagCompound reseachForSchool = new NBTTagCompound();
							researchList.forEach(research -> reseachForSchool.setBoolean(research.getResearch().getName(), research.getResearch().isUnlocked()));
							schoolResearch.setTag(school.get().getName(), reseachForSchool);
						});

						System.out.printf("Time to write research list to NBT: %d\n", System.currentTimeMillis() - startTime);
						return schoolResearch;
					}

					@Override
					public void readNBT(Capability<IPlayerResearch> capability, IPlayerResearch instance, EnumFacing side, NBTBase nbt) {
						long startTime = System.currentTimeMillis();
						NBTTagCompound schoolResearch = (NBTTagCompound) nbt;
						instance.getMasterList().forEach((school, researchList) -> {
							NBTTagCompound researchForSchool = schoolResearch.getCompoundTag(school.get().getName());
							researchList.forEach(research -> {
								boolean shouldBeUnlocked = researchForSchool.getBoolean(research.getResearch().getName());
								if (shouldBeUnlocked) {
									research.getResearch().unlock();
								}
							});
						});
						System.out.printf("Time to read research from NBT: %d\n", System.currentTimeMillis() - startTime);
					}
				},
				PlayerResearch::new
		);
	}

	@Override
	public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityResearch.RESEARCH;
	}

	@Nullable
	@Override
	public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
		if (capability == CapabilityResearch.RESEARCH) {
			return CapabilityResearch.RESEARCH.cast(this.instance);
		}

		return null;
	}

	@Override
	public NBTBase serializeNBT() {
		return RESEARCH.writeNBT(this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		RESEARCH.readNBT(this.instance, null, nbt);
	}
}
