package functions;

import functions.database_classes.AttributeDataClass;
import functions.database_classes.HistoryDataClass;
import functions.database_classes.ObservationMomentClass;
import functions.database_classes.PeriodDataClass;
import functions.inductive_alternatives_classes.AlternativeInductiveClass;
import functions.inductive_alternatives_classes.AlternativesByNumInductiveClass;
import functions.inductive_alternatives_classes.AttributeInductiveClass;
import functions.inductive_alternatives_classes.HistoryInductiveClass;
import functions.inductive_alternatives_classes.list_combinations.AllPartitionCombinations;

import java.util.ArrayList;

public class InitDirtyInductiveBase {

    private static ArrayList<ObservationMomentClass> getAllObservationMoments(ArrayList<PeriodDataClass> periodDataClasses) {
        ArrayList<ObservationMomentClass> observationMoments = new ArrayList<>();

        for (PeriodDataClass period: periodDataClasses) {
            observationMoments.addAll(period.getObservationMomentClasses());
        }

        return observationMoments;
    }

    //Функция, создающая все альтернативы на основе поданных на вход времён наблюдения
    private static ArrayList<AlternativeInductiveClass> generateAllAlternatives(ArrayList<ObservationMomentClass> observationMoments, int partitionNum) {
        ArrayList<AlternativeInductiveClass> alternatives = new ArrayList<>();
        AllPartitionCombinations.clearListElements(partitionNum, observationMoments.size());

        do {
            alternatives.add(AllPartitionCombinations.mapAlternative(observationMoments));
            if (partitionNum == 0) {
                break;
            }
        } while (AllPartitionCombinations.newDoNextMove());

        return alternatives;
    }

    //Инициализируем "Грязную" ИБЗ
    public static ArrayList<HistoryInductiveClass> initNonCheckedHistoriesBase() {
        ArrayList<HistoryInductiveClass> nonCheckedHistories = new ArrayList<>();

        //Проходим по всем болезням, нам известным
        for (HistoryDataClass diseaseHistory : ProgramGlobals.getDiseaseHistoriesData()) {
            ArrayList<AttributeInductiveClass> inductiveAttribute = new ArrayList<>();

            //Проходим по всем атрибутам, нам известным
            for (AttributeDataClass attribute : diseaseHistory.getAttributeDataClasses()) {
                int numberOfPartitions = 0;
                //Считаем количество возможных разделений по количеству элементов
                for (PeriodDataClass period : attribute.getPeriodDataClasses()) {
                    for (ObservationMomentClass observation : period.getObservationMomentClasses()) {
                        numberOfPartitions++;
                    }
                }
                ArrayList<AlternativesByNumInductiveClass> alternativesByNum = new ArrayList<>(numberOfPartitions);

                //Создаём реестр альтернатив
                for (int index = 0; index < numberOfPartitions; index++) {
                    ArrayList<AlternativeInductiveClass> alternatives =
                            generateAllAlternatives(getAllObservationMoments(attribute.getPeriodDataClasses()), index);

                    //Добавляем альтернативы в реестр альтернатив по количеству разбиений
                    alternativesByNum.add(new AlternativesByNumInductiveClass(index + 1, alternatives));
                }

                inductiveAttribute.add(new AttributeInductiveClass(attribute.getName(), attribute.getParameterType(), alternativesByNum));
            }

            nonCheckedHistories.add(new HistoryInductiveClass(
                    diseaseHistory.getName(), diseaseHistory.getNumberOfHistory(), inductiveAttribute));
        }

        return nonCheckedHistories;
    }
}
