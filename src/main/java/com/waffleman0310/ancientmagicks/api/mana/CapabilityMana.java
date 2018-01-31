package com.waffleman0310.ancientmagicks.api.mana;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityMana {

	@CapabilityInject(IManaStorage.class)
	public static Capability<IManaStorage> MANA = null;

	public static void register() {
		CapabilityManager.INSTANCE.register(IManaStorage.class, new Capability.IStorage<IManaStorage>() {
					@Nullable
					@Override
					public NBTBase writeNBT(Capability<IManaStorage> capability, IManaStorage instance, EnumFacing side) {
						return new NBTTagLong(instance.getManaStored());
					}

					@Override
					public void readNBT(Capability<IManaStorage> capability, IManaStorage instance, EnumFacing side, NBTBase nbt) {
						((ManaStorage) instance).mana = ((NBTTagLong) nbt).getLong();
					}
				},
				() -> new ManaStorage(1000, IManaStorage.EnumManaType.NORMAL)
		);
	}
}
