package com.waffleman0310.ancientmagicks.api.school.schools;

import com.waffleman0310.ancientmagicks.api.school.ISchool;
import com.waffleman0310.ancientmagicks.api.school.research.ResearchMap;

public class SchoolGeneral implements ISchool {

    public static final SchoolGeneral INSTANCE = new SchoolGeneral();

    @Override
    public String getName() {
        return "general";
    }

    @Override
    public String getResourceName() {
        return "null";
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
