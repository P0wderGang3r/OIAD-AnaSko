package functions.knowledge_classes;

import functions.fundamental_knowledge.AttributeName;
import functions.fundamental_knowledge.ParameterType;

import java.util.ArrayList;

public class AttributeKnowledgeClass {
    AttributeName name;

    int numOfDynamicPeriods;

    ParameterType parameterType;

    ArrayList<PeriodKnowledgeClass> periodKnowledgeClasses;

    public AttributeName getName() {
        return name;
    }

    public ParameterType getParameterType() {
        return parameterType;
    }

    public int getNumOfDynamicPeriods() {
        return numOfDynamicPeriods;
    }

    public ArrayList<PeriodKnowledgeClass> getDynamicPeriodClasses() {
        return periodKnowledgeClasses;
    }

    public AttributeKnowledgeClass(AttributeName name, ParameterType parameterType, ArrayList<PeriodKnowledgeClass> periodKnowledgeClasses) {
        this.name = name;
        this.parameterType = parameterType;
        this.periodKnowledgeClasses = periodKnowledgeClasses;
        this.numOfDynamicPeriods = periodKnowledgeClasses.size();
    }
}
