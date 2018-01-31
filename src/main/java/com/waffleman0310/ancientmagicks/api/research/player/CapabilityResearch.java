package com.waffleman0310.ancientmagicks.api.research.player;

import com.waffleman0310.ancientmagicks.api.research.ResearchableMeta;
import com.waffleman0310.ancientmagicks.research.ResearchMap;
import net.minecraft.nbt.*;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map.Entry;

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
						NBTTagList list = new NBTTagList();
						for (Entry<ResearchableMeta, Boolean> entry : ((PlayerResearch) instance).researchDiscoveredMap.entrySet()) {
							NBTTagCompound compound = new NBTTagCompound();
							compound.setTag("researchName", new NBTTagString(entry.getKey().getName()));
							compound.setTag("researchUnlocked", new NBTTagByte((byte) (entry.getValue() ? 1 : 0)));
							list.appendTag(compound);
						}
						return list;
					}

					@Override
					public void readNBT(Capability<IPlayerResearch> capability, IPlayerResearch instance, EnumFacing side, NBTBase nbt) {
						NBTTagList list = (NBTTagList) nbt;

						list.forEach(nbtBase -> {
							NBTTagCompound compound = (NBTTagCompound) nbtBase;
							((PlayerResearch) instance).researchDiscoveredMap.put(
									ResearchMap.getResearchFromName(compound.getString("researchName")),
									compound.getBoolean("researchUnlocked")
							);
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
