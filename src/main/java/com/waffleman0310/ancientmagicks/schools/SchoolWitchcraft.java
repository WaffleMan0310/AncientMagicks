package com.waffleman0310.ancientmagicks.schools;

import com.waffleman0310.ancientmagicks.api.school.ISchool;
import com.waffleman0310.ancientmagicks.research.ResearchMap;

public class SchoolWitchcraft implements ISchool {

    @Override
    public String getName() {
        return "witchcraft";
    }

    @Override
    public String getResourceName() {
        return "brewing_base";
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
