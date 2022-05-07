package functions;

import functions.inductive_alternatives_classes.*;

import java.util.ArrayList;

public class FilterDirtyInductiveBase {

    public static void emptyAlternativeByNumFilter(ArrayList<HistoryInductiveClass> alternativesForHistories) {
        boolean isDeleted = false;
        for (HistoryInductiveClass diseaseHistory : alternativesForHistories) {
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

    public static void equalInNeighborFilter(ArrayList<HistoryInductiveClass> alternativesForHistories) {

        boolean isDeleted = false;
        for (HistoryInductiveClass diseaseHistory: alternativesForHistories) {
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

    public static void equalInOnePeriodFilter(ArrayList<HistoryInductiveClass> alternativesForHistories) {
        for (HistoryInductiveClass diseaseHistory : alternativesForHistories) {
            for (AttributeInductiveClass attribute : diseaseHistory.getAttributes()) {
                for (AlternativesByNumInductiveClass alternativeByNum : attribute.getAlternativesByNum()) {
                    for (AlternativeInductiveClass alternative : alternativeByNum.getAlternatives()) {
                        for (PeriodInductiveClass period : alternative.getPeriods()) {
                            ArrayList<String> filteredPeriodValues = new ArrayList<>();
                            filteredPeriodValues.add(period.getValues().get(0));

                            for (String value: period.getValues()) {
                                boolean isEquals = false;
                                for (String filteredValue: filteredPeriodValues) {
                                    if (value.equals(filteredValue)) {
                                        isEquals = true;
                                        break;
                                    }
                                }

                                if (!isEquals) {
                                    filteredPeriodValues.add(value);
                                }
                            }

                            period.setValues(filteredPeriodValues);
                        }
                    }
                }
            }
        }

    }

    public static void emptyPeriodInAlternativeFilter(ArrayList<HistoryInductiveClass> alternativesForHistories) {
        boolean isDeleted = false;
        for (HistoryInductiveClass diseaseHistory: alternativesForHistories) {
            for (AttributeInductiveClass attribute: diseaseHistory.getAttributes()) {
                for (AlternativesByNumInductiveClass alternativeByNum: attribute.getAlternativesByNum()) {
                    boolean firstRun = true;
                    while (isDeleted || firstRun) {
                        isDeleted = false;
                        firstRun = false;
                        for (AlternativeInductiveClass alternative : alternativeByNum.getAlternatives()) {
                            for (PeriodInductiveClass period : alternative.getPeriods()) {
                                if (period.getValues().size() == 0) {
                                    alternativeByNum.getAlternatives().remove(alternative);
                                    isDeleted = true;
                                    break;
                                }
                            }
                            if (isDeleted) {
                                break;
                            }
                        }
                    }
                }
            }
        }

    }

}
