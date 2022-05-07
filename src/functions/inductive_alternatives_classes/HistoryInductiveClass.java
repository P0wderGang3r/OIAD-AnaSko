package functions.inductive_alternatives_classes;

import functions.fundamental_knowledge.DiseaseName;

import java.util.ArrayList;

public class HistoryInductiveClass {
    DiseaseName name;

    int numberOfHistory;

    ArrayList<AttributeInductiveClass> attributes;

    public DiseaseName getName() {
        return name;
    }

    public int getNumberOfHistory() {
        return numberOfHistory;
    }

    public ArrayList<AttributeInductiveClass> getAttributes() {
        return attributes;
    }

    public HistoryInductiveClass(DiseaseName name, int numberOfHistory, ArrayList<AttributeInductiveClass> attributes) {
        this.name = name;
        this.numberOfHistory = numberOfHistory;
        this.attributes = attributes;
    }
}
