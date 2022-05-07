import functions.ProgramGlobals;
import functions.database_classes.AttributeDataClass;
import functions.database_classes.HistoryDataClass;
import functions.database_classes.ObservationMomentClass;
import functions.database_classes.PeriodDataClass;
import functions.fundamental_knowledge.ParameterType;
import functions.inductive_alternatives_classes.*;
import functions.inductive_knowledge_classes.InductiveKnowledgeClass;
import functions.knowledge_classes.AttributeKnowledgeClass;
import functions.knowledge_classes.DiseaseKnowledgeClass;
import functions.knowledge_classes.PeriodKnowledgeClass;

import java.util.ArrayList;

public class GlobalLogs {
    public static void writeBasicKnowledge() {
        System.out.println("________________/БАЗА ЗНАНИЙ\\_______________");
        for (DiseaseKnowledgeClass disease : ProgramGlobals.getBasicKnowledgeBase()) {
            System.out.println("|");
            System.out.println("|------------------------------------------|");
            System.out.println("|");
            System.out.println("| Название заболевания: " + disease.getName());
            System.out.println("|");
            System.out.println("|------------------------------------------|");
            for (AttributeKnowledgeClass attribute : disease.getAttributeClasses()) {
                System.out.println("|");
                System.out.println("|-->| Название признака: " + attribute.getName());
                System.out.println("|-->| Тип признака: " + attribute.getParameterType());
                System.out.println("|-->| Количество периодов динамики: " + attribute.getNumOfDynamicPeriods());

                for (PeriodKnowledgeClass period : attribute.getDynamicPeriodClasses()) {
                    System.out.println("|   |");
                    System.out.println("|   |--->| Номер периода динамики: " + period.getNumberOfDynamicPeriod());
                    System.out.println("|   |--->| Верхняя граница: " + period.getUpperTime());
                    System.out.println("|   |--->| Нижняя граница: " + period.getLowerTime());
                    System.out.println("|   |--->| Значения для периода: " + period.getValues());
                }
            }
        }
    }


    public static void writeDataBase() {
        System.out.println("________________/БАЗА ДАННЫХ\\_______________");
        for (HistoryDataClass diseaseHistory : ProgramGlobals.getDiseaseHistoriesData()) {
            System.out.println("|");
            System.out.println("|------------------------------------------|");
            System.out.println("|");
            System.out.println("| Номер истории болезни: " + diseaseHistory.getNumberOfHistory());
            System.out.println("| Название заболевания: " + diseaseHistory.getName());
            System.out.println("|");
            System.out.println("|------------------------------------------|");

            for (AttributeDataClass attribute : diseaseHistory.getAttributeDataClasses()) {
                System.out.println("|");
                System.out.println("|-->| Название признака: " + attribute.getName());
                System.out.println("|-->| Тип признака: " + attribute.getParameterType());

                for (PeriodDataClass period : attribute.getPeriodDataClasses()) {
                    System.out.println("|   |");
                    System.out.println("|   |--->| Номер периода динамики: " + period.getNumberOfDynamicPeriod());
                    System.out.println("|   |--->| Длительность периода динамки: " + period.getDynamicPeriodTime());
                    System.out.println("|   |--->| Количество моментов наблюдения: " + period.getNumberOfObservationMoments());

                    for (ObservationMomentClass observation : period.getObservationMomentClasses()) {
                        System.out.println("|   |    |");
                        System.out.println("|   |    |---->| Момент наблюдения: " + observation.getObservationTime());
                        System.out.println("|   |    |---->| Значение в момент наблюдения: " + observation.getValue());
                    }
                }
            }
        }
    }

    public static void writeDirtyInductiveBase(ArrayList<HistoryInductiveClass> inductiveHistories) {
        System.out.println("_______________/АЛЬТЕРНАТИВЫ\\_______________");
        for (HistoryInductiveClass diseaseHistory : inductiveHistories) {
            System.out.println("|");
            System.out.println("|------------------------------------------|");
            System.out.println("|");
            System.out.println("| Номер истории болезни: " + diseaseHistory.getNumberOfHistory());
            System.out.println("| Название заболевания: " + diseaseHistory.getName());
            System.out.println("|");
            System.out.println("|------------------------------------------|");
            for (AttributeInductiveClass attribute : diseaseHistory.getAttributes()) {
                if (attribute.getParameterType() != ParameterType.ИНТЕРВАЛЬНЫЙ) {
                    System.out.println("|");
                    System.out.println("|-->| Название признака: " + attribute.getName());
                    System.out.println("|-->| Тип признака: " + attribute.getParameterType());

                    for (AlternativesByNumInductiveClass alternativesByNum : attribute.getAlternativesByNum()) {
                        System.out.println("|   |");
                        System.out.println("|   |--->| Альтернативы для количества периодов: " + alternativesByNum.getDynamicPeriodsAmount());

                        for (AlternativeInductiveClass alternative : alternativesByNum.getAlternatives()) {
                            System.out.println("|   |    |");
                            System.out.println("|   |    |---->| Следующая альтернатива");

                            for (PeriodInductiveClass period : alternative.getPeriods()) {
                                System.out.println("|   |    |     |");
                                System.out.println("|   |    |     |----->| Следующий период");
                                System.out.println("|   |    |     |----->| Верхняя граница: " + period.getUpperTime());
                                System.out.println("|   |    |     |----->| Нижняя граница: " + period.getLowerTime());
                                System.out.println("|   |    |     |----->| Значения для периода: " + period.getValues());
                            }
                        }
                    }
                }
            }
        }
    }


    public static void writeInductiveBase(ArrayList<InductiveKnowledgeClass> inductiveHistories) {
        System.out.println("___________________/ИБЗ\\____________________");
        for (InductiveKnowledgeClass diseaseHistory : inductiveHistories) {
            System.out.println("|");
            System.out.println("|------------------------------------------|");
            System.out.println("|");
            System.out.println("| Название заболевания: " + diseaseHistory.getName());
            System.out.println("|");
            System.out.println("|------------------------------------------|");
            for (AttributeInductiveClass attribute : diseaseHistory.getAttributes()) {
                if (attribute.getParameterType() != ParameterType.ИНТЕРВАЛЬНЫЙ) {
                    System.out.println("|");
                    System.out.println("|-->| Название признака: " + attribute.getName());
                    System.out.println("|-->| Тип признака: " + attribute.getParameterType());

                    for (AlternativesByNumInductiveClass alternativesByNum : attribute.getAlternativesByNum()) {
                        System.out.println("|   |");
                        System.out.println("|   |--->| Альтернативы для количества периодов: " + alternativesByNum.getDynamicPeriodsAmount());

                        for (AlternativeInductiveClass alternative : alternativesByNum.getAlternatives()) {
                            System.out.println("|   |    |");
                            System.out.println("|   |    |---->| Следующая альтернатива");

                            for (PeriodInductiveClass period : alternative.getPeriods()) {
                                System.out.println("|   |    |     |");
                                System.out.println("|   |    |     |----->| Следующий период");
                                System.out.println("|   |    |     |----->| Верхняя граница: " + period.getUpperTime());
                                System.out.println("|   |    |     |----->| Нижняя граница: " + period.getLowerTime());
                                System.out.println("|   |    |     |----->| Значения для периода: " + period.getValues());
                            }
                        }
                    }
                }
            }
        }
    }

}
