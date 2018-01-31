package com.waffleman0310.ancientmagicks.api.research.player;

import com.waffleman0310.ancientmagicks.api.research.ResearchableMeta;

import java.util.HashMap;

public interface IPlayerResearch {

    void discoverResearch(ResearchableMeta research);

    void unlockResearch(ResearchableMeta research);

    boolean canUnlockResearch(ResearchableMeta research);

    boolean isResearchDiscovered(ResearchableMeta research);

    boolean isResearchUnlocked(ResearchableMeta research);

    void setResearchDiscovered(HashMap<ResearchableMeta, Boolean> researchTable);

    HashMap<ResearchableMeta, Boolean> getResearchDiscovered();
}
