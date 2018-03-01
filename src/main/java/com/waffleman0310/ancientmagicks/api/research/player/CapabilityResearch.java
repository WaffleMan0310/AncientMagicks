package com.waffleman0310.ancientmagicks.api.research.player;

import com.waffleman0310.ancientmagicks.api.research.registry.IResearchEntry;
import com.waffleman0310.ancientmagicks.api.research.registry.ResearchNode;
import com.waffleman0310.ancientmagicks.api.school.School;
import com.waffleman0310.ancientmagicks.init.Schools;
import net.minecraft.nbt.*;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

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
						NBTTagCompound compound = new NBTTagCompound();

						((PlayerResearch) instance).unlockMap.forEach((node, unlocked) -> compound.setBoolean(node.getResearch().getName(), unlocked));

						return compound;
					}

					@Override
					public void readNBT(Capability<IPlayerResearch> capability, IPlayerResearch instance, EnumFacing side, NBTBase nbt) {
						NBTTagCompound compound = (NBTTagCompound) nbt;


						((PlayerResearch) instance).unlockMap.forEach((node, unlocked) -> {
							boolean shouldBeUnlocked = compound.getBoolean(node.getResearch().getName());
							if (shouldBeUnlocked) {
								instance.unlock(node.getResearch());
							}
						});
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
