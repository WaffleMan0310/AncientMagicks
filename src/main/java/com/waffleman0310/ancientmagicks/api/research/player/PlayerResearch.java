package com.waffleman0310.ancientmagicks.api.research.player;

import com.waffleman0310.ancientmagicks.api.research.ResearchableMeta;
import com.waffleman0310.ancientmagicks.research.ResearchMap;
import net.minecraft.nbt.NBTBase;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashMap;
import java.util.List;

public class PlayerResearch implements IPlayerResearch {

	protected HashMap<ResearchableMeta, Boolean> researchDiscoveredMap;

	public PlayerResearch() {
		researchDiscoveredMap = new HashMap<>(ResearchMap.getSize());
	}

	@Override
	public void discoverResearch(ResearchableMeta research) {
		if (ResearchMap.isResearchInMap(research) && !isResearchDiscovered(research)) {
			this.researchDiscoveredMap.put(research, false);
		}
	}

	@Override
	public void unlockResearch(ResearchableMeta research) {
		if (isResearchDiscovered(research) && !isResearchUnlocked(research)) {
			this.researchDiscoveredMap.put(research, true);
		}
	}

	@Override
	public boolean canUnlockResearch(ResearchableMeta research) {
		List<ResearchableMeta> prereqs = ResearchMap.getPrerequisites(research);

		if (prereqs == null) {
			return isResearchDiscovered(research);
		} else {
			return researchDiscoveredMap.keySet().containsAll(prereqs) && isResearchDiscovered(research);
		}
	}

	@Override
	public boolean isResearchDiscovered(ResearchableMeta research) {
		return this.researchDiscoveredMap.keySet().contains(research);
	}

	@Override
	public boolean isResearchUnlocked(ResearchableMeta research) {
		return this.researchDiscoveredMap.get(research);
	}

	@Override
	public void setResearchDiscovered(HashMap<ResearchableMeta, Boolean> researchTable) {

	}

	@Override
	public HashMap<ResearchableMeta, Boolean> getResearchDiscovered() {
		return this.researchDiscoveredMap;
	}

}
