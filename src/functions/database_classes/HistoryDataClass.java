package functions.database_classes;

import functions.fundamental_knowledge.DiseaseName;

import java.util.ArrayList;

public class HistoryDataClass {
    DiseaseName name;

    int numberOfHistory;

    ArrayList<AttributeDataClass> attributeDataClasses;

    public DiseaseName getName() {
        return name;
    }

    public int getNumberOfHistory() {
        return numberOfHistory;
    }

    public ArrayList<AttributeDataClass> getAttributeDataClasses() {
        return attributeDataClasses;
    }

    public HistoryDataClass(DiseaseName name, int numberOfHistory, ArrayList<AttributeDataClass> attributeDataClasses) {
        this.name = name;
        this.numberOfHistory = numberOfHistory;
        this.attributeDataClasses = attributeDataClasses;
    }
}
