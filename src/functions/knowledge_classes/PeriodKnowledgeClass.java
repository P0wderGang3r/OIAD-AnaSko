package functions.knowledge_classes;

import java.util.ArrayList;

public class PeriodKnowledgeClass {
    int numberOfDynamicPeriod;

    int upperTime;
    int lowerTime;

    public int getNumberOfDynamicPeriod() {
        return numberOfDynamicPeriod;
    }

    public int getLowerTime() {
        return lowerTime;
    }

    public int getUpperTime() {
        return upperTime;
    }

    public ArrayList<String> getValues() {
        return values;
    }

    ArrayList<String> values;

    public PeriodKnowledgeClass(int numberOfDynamicPeriod, int upperTime, int lowerTime, ArrayList<String> values) {
        this.upperTime = upperTime;
        this.lowerTime = lowerTime;

        this.numberOfDynamicPeriod = numberOfDynamicPeriod;

        this.values = values;
    }
}
