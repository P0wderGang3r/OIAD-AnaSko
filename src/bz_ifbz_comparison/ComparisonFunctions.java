package bz_ifbz_comparison;

import functions.ProgramGlobals;
import functions.fundamental_knowledge.ParameterType;
import functions.inductive_alternatives_classes.AlternativeInductiveClass;
import functions.inductive_alternatives_classes.AlternativesByNumInductiveClass;
import functions.inductive_knowledge_classes.InductiveKnowledgeClass;
import functions.knowledge_classes.DiseaseKnowledgeClass;

import java.util.ArrayList;

public class ComparisonFunctions {
    private static final float[] globalMatchPercentage = new float[30];

    private static final float[][] globalMatchPeriodPercentage = new float[10][];
    private static final float[][] globalMatchPeriodNum = new float[10][];

    public static void compareOnNumOfPeriods() {
        ArrayList<DiseaseKnowledgeClass> basicKnowledgeBase = ProgramGlobals.getBasicKnowledgeBase();
        ArrayList<InductiveKnowledgeClass> inductiveKnowledgeBase = ProgramGlobals.getInductiveKnowledgeBase();

        for (int diseaseIndex = 0; diseaseIndex < 3; diseaseIndex++) {
            for (int attributeIndex = 0; attributeIndex < 10; attributeIndex++) {

                if (basicKnowledgeBase.get(diseaseIndex)
                        .getAttributeClasses().get(attributeIndex)
                        .getParameterType() != ParameterType.ИНТЕРВАЛЬНЫЙ) {

                    int difference = 10;

                    int numOfNearest = 0;

                    for (AlternativesByNumInductiveClass alternativesByNum :
                            inductiveKnowledgeBase.get(diseaseIndex).
                                    getAttributes().get(attributeIndex)
                                    .getAlternativesByNum()) {
                        int localDifference = Math.abs(basicKnowledgeBase.get(diseaseIndex)
                                .getAttributeClasses().get(attributeIndex)
                                .getNumOfDynamicPeriods() - alternativesByNum.getDynamicPeriodsAmount());

                        if (localDifference < difference) {
                            difference = localDifference;
                            numOfNearest = alternativesByNum.getDynamicPeriodsAmount();

                        }
                    }

                    System.out.println("| Название болезни: " + basicKnowledgeBase.get(diseaseIndex).getName());
                    System.out.println("| Название признака: " + basicKnowledgeBase.get(diseaseIndex)
                            .getAttributeClasses().get(attributeIndex).getName());
                    System.out.println("|");
                    System.out.println("|-->| Количество периодов динамики в БЗ: " + basicKnowledgeBase.get(diseaseIndex)
                            .getAttributeClasses().get(attributeIndex).getNumOfDynamicPeriods());
                    System.out.println("|-->| Количество периодов динамики в ИБЗ: " + numOfNearest);

                    float matchPercents = (float) (Math.max(basicKnowledgeBase.get(diseaseIndex).getAttributeClasses()
                            .get(attributeIndex).getNumOfDynamicPeriods(), numOfNearest) - difference) /
                            (float) Math.max(basicKnowledgeBase.get(diseaseIndex).getAttributeClasses()
                                    .get(attributeIndex).getNumOfDynamicPeriods(), numOfNearest);

                    System.out.println("|-->| Совпадение: " + (matchPercents * 100) + "%");
                    globalMatchPercentage[diseaseIndex * 10 + attributeIndex] = matchPercents;


                    System.out.println();
                }
            }
        }
    }

    public static void showGlobalPercentage() {
        ArrayList<DiseaseKnowledgeClass> basicKnowledgeBase = ProgramGlobals.getBasicKnowledgeBase();

        for (int attributeIndex = 0; attributeIndex < 10; attributeIndex++) {
            if (basicKnowledgeBase.get(0).getAttributeClasses().get(attributeIndex)
                    .getParameterType() != ParameterType.ИНТЕРВАЛЬНЫЙ) {
                System.out.println("| Признак: " + basicKnowledgeBase.get(0)
                        .getAttributeClasses().get(attributeIndex).getName());
                System.out.println("|");
                System.out.println("|-->| Совпадение по признаку: " +
                        (((globalMatchPercentage[attributeIndex] + globalMatchPercentage[10 + attributeIndex]
                                + globalMatchPercentage[20 + attributeIndex]) / 3) * 100) + "%");
                System.out.println();
            }
        }
    }

    public static void comparisonOfElementsInPeriods() {
        ArrayList<DiseaseKnowledgeClass> basicKnowledgeBase = ProgramGlobals.getBasicKnowledgeBase();
        ArrayList<InductiveKnowledgeClass> inductiveKnowledgeBase = ProgramGlobals.getInductiveKnowledgeBase();

        for (int diseaseIndex = 0; diseaseIndex < 3; diseaseIndex++) {
            for (int attributeIndex = 0; attributeIndex < 10; attributeIndex++) {
                if (basicKnowledgeBase.get(diseaseIndex)
                        .getAttributeClasses().get(attributeIndex)
                        .getParameterType() != ParameterType.ИНТЕРВАЛЬНЫЙ) {


                    //Проходим по все альтернативам от номера
                    //Если найдено совпадение между двумя элементами

                    for (AlternativesByNumInductiveClass alternativesByNum :
                            inductiveKnowledgeBase.get(diseaseIndex).
                                    getAttributes().get(attributeIndex)
                                    .getAlternativesByNum()) {
                        for (AlternativeInductiveClass alternatives : alternativesByNum.getAlternatives()) {

                            if (alternatives.getPeriods().size() == basicKnowledgeBase.get(diseaseIndex)
                                    .getAttributeClasses().get(attributeIndex).getDynamicPeriodClasses().size()) {

                                System.out.println("| Название болезни: " + basicKnowledgeBase.get(diseaseIndex).getName());
                                System.out.println("| Название признака: " + basicKnowledgeBase.get(diseaseIndex)
                                        .getAttributeClasses().get(attributeIndex).getName());

                                globalMatchPeriodPercentage[attributeIndex] = new float[alternatives.getPeriods().size()];
                                globalMatchPeriodNum[attributeIndex] = new float[alternatives.getPeriods().size()];

                                for (int localIndex = 0; localIndex < alternatives.getPeriods().size(); localIndex++) {
                                    globalMatchPeriodPercentage[attributeIndex][localIndex] = 0;
                                    globalMatchPeriodNum[attributeIndex][localIndex] = 0;
                                }


                                for (int periodIndex = 0; periodIndex < alternatives.getPeriods().size(); periodIndex++) {

                                    int numOfValuesInIBZ = 0;
                                    for (String value : alternatives.getPeriods().get(periodIndex).getValues()) {
                                        numOfValuesInIBZ++;
                                    }

                                    int numOfValuesInBZ = 0;
                                    for (String value : basicKnowledgeBase.get(diseaseIndex)
                                            .getAttributeClasses().get(attributeIndex)
                                            .getDynamicPeriodClasses().get(periodIndex).getValues()) {
                                        numOfValuesInBZ++;
                                    }

                                    //Проход по значениям, если БЗ является подмножеством значений ИФБЗ
                                    if (numOfValuesInIBZ > numOfValuesInBZ) {
                                        int numOfMatchingAttributes = 0;
                                        int numOfMismatchingAttributes = 0;

                                        for (String firstValue : alternatives.getPeriods().get(periodIndex).getValues()) {
                                            boolean isMatching = false;

                                            for (String secondValue : basicKnowledgeBase.get(diseaseIndex)
                                                    .getAttributeClasses().get(attributeIndex)
                                                    .getDynamicPeriodClasses().get(periodIndex).getValues()) {

                                                if (firstValue.equals(secondValue)) {
                                                    isMatching = true;
                                                    break;
                                                }
                                            }

                                            if (isMatching) {
                                                numOfMatchingAttributes += 1;
                                            } else {
                                                numOfMismatchingAttributes += 1;
                                            }
                                        }
                                        System.out.println("|");
                                        System.out.println("|-->| Период динамики: " + (periodIndex + 1));
                                        System.out.println("|-->| БЗ: " + basicKnowledgeBase.get(diseaseIndex)
                                                .getAttributeClasses().get(attributeIndex)
                                                .getDynamicPeriodClasses().get(periodIndex).getValues());
                                        System.out.println("|-->| ИФБЗ: " + alternatives.getPeriods().get(periodIndex).getValues());
                                        System.out.println("|-->| БЗ является подмножеством множества значений ИФБЗ");
                                        System.out.println("|-->| Степень совпадения: " + (((float) (numOfMatchingAttributes) / (float) (numOfMatchingAttributes + numOfMismatchingAttributes)) * 100) + "%");

                                        globalMatchPeriodPercentage[attributeIndex][periodIndex] += ((float) (numOfMatchingAttributes) / (float) (numOfMatchingAttributes + numOfMismatchingAttributes));
                                        globalMatchPeriodNum[attributeIndex][periodIndex] += 1;
                                    }

                                    //Проход по значениям, если ИФБЗ является подмножеством значений БЗ
                                    if (numOfValuesInIBZ < numOfValuesInBZ) {
                                        int numOfMatchingAttributes = 0;
                                        int numOfMismatchingAttributes = 0;

                                        for (String secondValue : basicKnowledgeBase.get(diseaseIndex)
                                                .getAttributeClasses().get(attributeIndex)
                                                .getDynamicPeriodClasses().get(periodIndex).getValues()) {
                                            boolean isMatching = false;

                                            for (String firstValue : alternatives.getPeriods().get(periodIndex).getValues()) {

                                                if (firstValue.equals(secondValue)) {
                                                    isMatching = true;
                                                    break;
                                                }
                                            }

                                            if (isMatching) {
                                                numOfMatchingAttributes += 1;
                                            } else {
                                                numOfMismatchingAttributes += 1;
                                            }
                                        }
                                        System.out.println("|");
                                        System.out.println("|-->| Период динамики: " + (periodIndex + 1));
                                        System.out.println("|-->| БЗ: " + basicKnowledgeBase.get(diseaseIndex)
                                                .getAttributeClasses().get(attributeIndex)
                                                .getDynamicPeriodClasses().get(periodIndex).getValues());
                                        System.out.println("|-->| ИФБЗ: " + alternatives.getPeriods().get(periodIndex).getValues());
                                        System.out.println("|-->| ИФБЗ является подмножеством множества значений БЗ");
                                        System.out.println("|-->| Степень совпадения: " + (((float) (numOfMatchingAttributes) / (float) (numOfMatchingAttributes + numOfMismatchingAttributes)) * 100) + "%");

                                        globalMatchPeriodPercentage[attributeIndex][periodIndex] += ((float) (numOfMatchingAttributes) / (float) (numOfMatchingAttributes + numOfMismatchingAttributes));
                                        globalMatchPeriodNum[attributeIndex][periodIndex] += 1;
                                    }

                                    if (numOfValuesInBZ == numOfValuesInIBZ) {
                                        System.out.println("|");
                                        System.out.println("|-->| Период динамики: " + (periodIndex + 1));
                                        System.out.println("|-->| БЗ: " + basicKnowledgeBase.get(diseaseIndex)
                                                .getAttributeClasses().get(attributeIndex)
                                                .getDynamicPeriodClasses().get(periodIndex).getValues());
                                        System.out.println("|-->| ИФБЗ: " + alternatives.getPeriods().get(periodIndex).getValues());
                                        System.out.println("|-->| Степень совпадения: 100.0%");
                                        globalMatchPeriodPercentage[attributeIndex][periodIndex] += 1;
                                        globalMatchPeriodNum[attributeIndex][periodIndex] += 1;
                                    }
                                }

                                System.out.println();
                                System.out.println();
                            }
                        }
                    }

                }
            }
        }
    }

    public static void comparisonOfPeriodsByAttributes() {
        ArrayList<DiseaseKnowledgeClass> basicKnowledgeBase = ProgramGlobals.getBasicKnowledgeBase();

        for (int attributeIndex = 0; attributeIndex < 10; attributeIndex++) {
            if (basicKnowledgeBase.get(0).getAttributeClasses().get(attributeIndex)
                    .getParameterType() != ParameterType.ИНТЕРВАЛЬНЫЙ) {
                System.out.println("| Признак: " + basicKnowledgeBase.get(0)
                        .getAttributeClasses().get(attributeIndex).getName());
                System.out.println("|");
                for (int periodIndex = 0; periodIndex < globalMatchPeriodNum[attributeIndex].length; periodIndex++) {
                    System.out.println("|-->| Совпадение по периоду " + (periodIndex + 1) + ": "
                            + (globalMatchPeriodPercentage[attributeIndex][periodIndex] * 100 / globalMatchPeriodNum[attributeIndex][periodIndex]) + "%");
                }
                System.out.println();
            }
        }
    }

}