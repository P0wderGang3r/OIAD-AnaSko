package functions.inductive_alternatives_classes;

import java.util.ArrayList;

public class AlternativesByNumInductiveClass {
    int dynamicPeriodsAmount;

    ArrayList<AlternativeInductiveClass> alternatives;

    public int getDynamicPeriodsAmount() {
        return dynamicPeriodsAmount;
    }

    public ArrayList<AlternativeInductiveClass> getAlternatives() {
        return alternatives;
    }

    public AlternativesByNumInductiveClass(int dynamicPeriodsAmount, ArrayList<AlternativeInductiveClass> alternatives) {
        this.dynamicPeriodsAmount = dynamicPeriodsAmount;
        this.alternatives = alternatives;
    }
}
