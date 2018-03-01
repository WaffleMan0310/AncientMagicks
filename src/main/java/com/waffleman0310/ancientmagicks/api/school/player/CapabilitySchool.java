package com.waffleman0310.ancientmagicks.api.school.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilitySchool implements ICapabilitySerializable {

	@CapabilityInject(IPlayerSchool.class)
	public static Capability<IPlayerSchool> PLAYER_SCHOOL = null;

	private IPlayerSchool instance = PLAYER_SCHOOL.getDefaultInstance();

	public static void register() {
		CapabilityManager.INSTANCE.register(
				IPlayerSchool.class,
				new IStorage<IPlayerSchool>() {
					@Nullable
					@Override
					public NBTBase writeNBT(Capability<IPlayerSchool> capability, IPlayerSchool instance, EnumFacing side) {
						NBTTagCompound compound = new NBTTagCompound();
						PlayerSchool playerSchool = ((PlayerSchool) instance);

						playerSchool.schoolExperianceMap.forEach((school, xp) -> compound.setInteger(String.format("%s_xp", school.toString()), xp));
						playerSchool.schoolLevelMap.forEach((school, level) -> compound.setInteger(String.format("%s_level", school.toString()), level));

						return compound;
					}

					@Override
					public void readNBT(Capability<IPlayerSchool> capability, IPlayerSchool instance, EnumFacing side, NBTBase nbt) {
						NBTTagCompound compound = (NBTTagCompound) nbt;

						PlayerSchool playerSchool = (PlayerSchool) instance;

						playerSchool.schoolExperianceMap.forEach((school, xp) -> {
							playerSchool.schoolExperianceMap.put(school, compound.getInteger(String.format("%s_xp", school.toString())));
							playerSchool.schoolLevelMap.put(school, compound.getInteger(String.format("%s_level", school.toString())));
						});

					}
				},
				PlayerSchool::new
		);
	}

	@Override
	public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == CapabilitySchool.PLAYER_SCHOOL;
	}

	@Nullable
	@Override
	public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
		if (capability == CapabilitySchool.PLAYER_SCHOOL) {
			return CapabilitySchool.PLAYER_SCHOOL.cast(this.instance);
		}

		return null;
	}

	@Override
	public NBTBase serializeNBT() {
		return PLAYER_SCHOOL.writeNBT(this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		PLAYER_SCHOOL.readNBT(this.instance, null, nbt);
	}
}
