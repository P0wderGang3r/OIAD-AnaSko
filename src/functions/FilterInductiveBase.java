package functions;

import functions.inductive_alternatives_classes.AlternativeInductiveClass;
import functions.inductive_alternatives_classes.AlternativesByNumInductiveClass;
import functions.inductive_alternatives_classes.AttributeInductiveClass;
import functions.inductive_knowledge_classes.InductiveKnowledgeClass;

import java.util.ArrayList;

public class FilterInductiveBase {

    public static void emptyAlternativeByNumFilter(ArrayList<InductiveKnowledgeClass> alternativesForHistories) {
        boolean isDeleted = false;
        for (InductiveKnowledgeClass diseaseHistory : alternativesForHistories) {
            for (AttributeInductiveClass attribute : diseaseHistory.getAttributes()) {
                boolean firstRun = true;
                while (isDeleted || firstRun) {
                    isDeleted = false;
                    firstRun = false;

                    for (AlternativesByNumInductiveClass alternativeByNum : attribute.getAlternativesByNum()) {
                        if (alternativeByNum.getAlternatives().size() == 0) {
                            attribute.getAlternativesByNum().remove(alternativeByNum);
                            isDeleted = true;
                            break;
                        }
                    }
                }
            }
        }

    }

    public static void equalInNeighborFilter(ArrayList<InductiveKnowledgeClass> alternativesForHistories) {

        boolean isDeleted = false;
        for (InductiveKnowledgeClass diseaseHistory: alternativesForHistories) {
            for (AttributeInductiveClass attribute: diseaseHistory.getAttributes()) {
                for (AlternativesByNumInductiveClass alternativeByNum: attribute.getAlternativesByNum()) {
                    boolean firstRun = true;
                    while (isDeleted || firstRun) {
                        isDeleted = false;
                        firstRun = false;
                        for (AlternativeInductiveClass alternative : alternativeByNum.getAlternatives()) {

                            for (int index = 0; index < alternative.getPeriods().size() - 1; index++) {
                                for (String firstValue : alternative.getPeriods().get(index).getValues()) {
                                    for (String secondValue : alternative.getPeriods().get(index + 1).getValues()) {
                                        if (firstValue.equals(secondValue)) {
                                            alternativeByNum.getAlternatives().remove(alternative);
                                            isDeleted = true;
                                            break;
                                        }
                                    }
                                    if (isDeleted)
                                        break;
                                }
                                if (isDeleted)
                                    break;
                            }
                            if (isDeleted)
                                break;
                        }
                    }
                }
            }
        }
    }

}
