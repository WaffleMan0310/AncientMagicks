package com.waffleman0310.ancientmagicks.api.school.schools;

import com.waffleman0310.ancientmagicks.api.school.ISchool;
import com.waffleman0310.ancientmagicks.api.school.research.ResearchMap;

public class SchoolDemonology implements ISchool {

    public static final SchoolDemonology INSTANCE = new SchoolDemonology();

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
