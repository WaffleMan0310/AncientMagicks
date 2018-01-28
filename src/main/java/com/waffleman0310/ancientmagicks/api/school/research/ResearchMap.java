package com.waffleman0310.ancientmagicks.api.school.research;

import java.util.TreeMap;

public class ResearchMap {

    TreeMap<IResearchable, Object> map = new TreeMap<>();

    public static final ResearchMap instance = new ResearchMap();

    ResearchMap() {
        // Declare Recipes here
    }

    public void addNode(IResearchable prerequisite, IResearchable node) {

    }

    public IResearchable getNode() {
        return null;
    }
}
