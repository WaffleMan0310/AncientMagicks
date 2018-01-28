package com.waffleman0310.ancientmagicks.api.school.research;

import com.waffleman0310.ancientmagicks.api.school.ISchool;

public interface IResearchable {

    enum NodeType {
        LESSAR, NORMAL, GREATER
    }

    // Get research name, description, etc

    NodeType getType();

    IResearchable[] getConnectedNodes();

    IResearchable[] getPrerequisites();

    ISchool getSchool();
}
