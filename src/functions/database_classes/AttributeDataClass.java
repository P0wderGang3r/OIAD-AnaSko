package functions.database_classes;

import functions.fundamental_knowledge.AttributeName;
import functions.fundamental_knowledge.ParameterType;

import java.util.ArrayList;

public class AttributeDataClass {
    AttributeName name;

    ParameterType parameterType;

    ArrayList<PeriodDataClass> periodDataClasses;

    public AttributeName getName() {
        return name;
    }

    public ParameterType getParameterType() {
        return parameterType;
    }

    public ArrayList<PeriodDataClass> getPeriodDataClasses() {
        return periodDataClasses;
    }

    public AttributeDataClass(AttributeName name, ParameterType parameterType,
                              ArrayList<PeriodDataClass> periodDataClasses) {
        this.name = name;
        this.parameterType = parameterType;

        this.periodDataClasses = periodDataClasses;
    }
}
