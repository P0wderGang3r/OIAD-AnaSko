package functions.inductive_alternatives_classes;

import functions.fundamental_knowledge.AttributeName;
import functions.fundamental_knowledge.ParameterType;

import java.util.ArrayList;

public class AttributeInductiveClass {
    AttributeName name;

    ParameterType parameterType;

    ArrayList<AlternativesByNumInductiveClass> alternativesByNum;

    public AttributeName getName() {
        return name;
    }

    public ParameterType getParameterType() {
        return parameterType;
    }

    public ArrayList<AlternativesByNumInductiveClass> getAlternativesByNum() {
        return alternativesByNum;
    }

    public AttributeInductiveClass(AttributeName name, ParameterType parameterType,
                                   ArrayList<AlternativesByNumInductiveClass> alternativesByNum) {
        this.name = name;
        this.parameterType = parameterType;
        this.alternativesByNum = alternativesByNum;
    }
}
