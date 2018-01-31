package com.waffleman0310.ancientmagicks.research;

import com.waffleman0310.ancientmagicks.api.research.IResearchable.NodeType;
import com.waffleman0310.ancientmagicks.api.research.ResearchableMeta;
import com.waffleman0310.ancientmagicks.schools.EnumSchool;
import net.minecraft.item.ItemStack;


import java.util.*;
import java.util.Map.Entry;

public final class ResearchMap {

	private static final HashMap<ResearchableMeta, List<ResearchableMeta>> researchMap = new HashMap<>(EnumResearch.values().length);
	private static final HashMap<Integer, ResearchableMeta> researchIDMap = new HashMap<>(EnumResearch.values().length);

	private static int next_Id = 0;

	public static void initialize() {
		for (EnumResearch research : EnumResearch.values()) {
			addResearch(research.getResearchable(), research.getPrerequisites());
		}
	}

	public static void addResearch(ResearchableMeta research, ResearchableMeta... prerequisites) {
		researchMap.put(research, Arrays.asList(prerequisites));
		researchIDMap.put(next_Id, research);
		next_Id++;
	}

	public static boolean stackHasResearch(ItemStack stack) {
		if (!stack.isEmpty()) {
			for (Entry<ResearchableMeta, List<ResearchableMeta>> entry : researchMap.entrySet()) {
				if (ItemStack.areItemsEqual(entry.getKey().getAsStack(), stack)) {
					return true;
				}
			}
		}

		return false;
	}

	public static Entry<ResearchableMeta, List<ResearchableMeta>> getResearchEntryForStack(ItemStack stack) {
		if (stackHasResearch(stack)) {
			for (Entry<ResearchableMeta, List<ResearchableMeta>> entry : researchMap.entrySet()) {
				if (ItemStack.areItemsEqual(entry.getKey().getAsStack(), stack)) {
					return entry;
				}
			}
		}

		return null;
	}

	public static List<ResearchableMeta> getPreprequisitesForStack(ItemStack stack) {
		return getResearchEntryForStack(stack).getValue();
	}

	public static NodeType getNodeTypeForStack(ItemStack stack) {
		return getResearchEntryForStack(stack).getKey().getNodeType();
	}

	public static EnumSchool getSchoolForStack(ItemStack stack) {
		return getResearchEntryForStack(stack).getKey().getSchool();
	}

	public static boolean isResearchInMap(ResearchableMeta research) {
		for (ResearchableMeta key : researchMap.keySet()) {
			if (key.equals(research)) {
				return true;
			}
		}

		return false;
	}

	public static List<ResearchableMeta> getPrerequisites(ResearchableMeta research) {
		if (isResearchInMap(research)) {
			return researchMap.get(research);
		}

		return null;
	}

	public static EnumSchool getSchool(ResearchableMeta research) {
		for (ResearchableMeta key : researchMap.keySet()) {
			if (key.equals(research)) {
				return key.getSchool();
			}
		}

		return null;
	}

	public static NodeType getNodeType(ResearchableMeta research) {
		for (ResearchableMeta key : researchMap.keySet()) {
			if (key.equals(research)) {
				return key.getNodeType();
			}
		}

		return null;
	}

	public static ResearchableMeta getResearchFromName(String str) {
		for (ResearchableMeta r : researchMap.keySet()) {
			if (r.toString().equals(str)) {
				return r;
			}
		}

		return null;
	}

	public static ResearchableMeta getResearchForId(int idx) {
		if (idx < getSize()) {
			return researchIDMap.get(idx);
		}

		return null;
	}

	public static int getIdForResearch(ResearchableMeta research) {
		if (isResearchInMap(research)) {
			for (Entry<Integer, ResearchableMeta> entry : researchIDMap.entrySet()) {
				return entry.getKey();
			}
		}

		return -1;
	}

	public static int getSize() {
		return researchMap.size();
	}
}
