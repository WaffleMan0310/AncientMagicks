package com.waffleman0310.ancientmagicks.schools;

import com.waffleman0310.ancientmagicks.api.school.ISchool;
import com.waffleman0310.ancientmagicks.research.ResearchMap;

public class SchoolDemonology implements ISchool {

    @Override
    public String getName() {
        return "demonology";
    }

    @Override
    public String getResourceName() {
        return "demonic_blood";
    }

    @Override
    public ISchool[] getConnectedSchools() {
        return new ISchool[0];
    }

    @Override
    public ResearchMap getResearchMap() {
        return null;
    }
}
