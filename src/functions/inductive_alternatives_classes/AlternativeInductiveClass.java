package functions.inductive_alternatives_classes;

import java.util.ArrayList;

public class AlternativeInductiveClass {
    ArrayList<PeriodInductiveClass> periods;

    public ArrayList<PeriodInductiveClass> getPeriods() {
        return periods;
    }

    public AlternativeInductiveClass(ArrayList<PeriodInductiveClass> periods) {
        this.periods = periods;
    }
}
