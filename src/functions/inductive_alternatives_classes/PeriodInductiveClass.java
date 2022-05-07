package functions.inductive_alternatives_classes;

import java.util.ArrayList;

public class PeriodInductiveClass {
    int upperTime;
    int lowerTime;

    ArrayList<String> values;

    public void setUpperTime(int upperTime) {
        this.upperTime = upperTime;
    }

    public int getUpperTime() {
        return upperTime;
    }

    public int getLowerTime() {
        return lowerTime;
    }

    public void setLowerTime(int lowerTime) {
        this.lowerTime = lowerTime;
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public void setValues(ArrayList<String> values) {
        this.values = values;
    }

    public PeriodInductiveClass(int upperTime, int lowerTime, ArrayList<String> values) {
        this.upperTime = upperTime;
        this.lowerTime = lowerTime;
        this.values = values;
    }
}
