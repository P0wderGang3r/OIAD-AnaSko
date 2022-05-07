package functions.inductive_alternatives_classes.list_combinations;

import functions.database_classes.ObservationMomentClass;
import functions.inductive_alternatives_classes.AlternativeInductiveClass;
import functions.inductive_alternatives_classes.PeriodInductiveClass;

import java.util.ArrayList;

public class AllPartitionCombinations {
    public static ArrayList<PartitionElement> partitionElements = new ArrayList<>();

    static int numberOfPartitions = 0;

    public static void clearListElements(int numberOfPartition, int length) {
        partitionElements.clear();
        numberOfPartitions = numberOfPartition;

        for (int index = 0; index < length; index++) {
            partitionElements.add(new PartitionElement());
            if (index < numberOfPartition)
                partitionElements.get(index).isUsed = true;
        }
    }

    //Почему новый? Старый был ужасен и не всегда верен, вследствие переписан
    //Короче, перебор всех перестановок listElement-ов.
    //Суть в предоставлении всевозможных границ разбиения для всевозможных альтернатив.
    //Если доходим до конца - все элементы справа - то заканчиваем выполнение.
    public static boolean newDoNextMove() {
        int globalIndex = 0;

        for (int index = 0; index < partitionElements.size(); index++) {
            globalIndex = index;

            while (partitionElements.get(globalIndex).isUsed) {
                globalIndex++;

                if (globalIndex == partitionElements.size()) {
                    return false;
                }

                if (!partitionElements.get(globalIndex).isUsed) {
                    partitionElements.get(globalIndex).isUsed = true;
                    globalIndex -= 1;
                    partitionElements.get(globalIndex).isUsed = false;
                    index = partitionElements.size();
                    break;
                }
            }
        }

        int localNumOfElements = 0;

        //Проверяем количество элементов, которые нужно сбросить на начальные позиции
        for (int index = 0; index < globalIndex; index++) {
            if (partitionElements.get(index).isUsed) {
                localNumOfElements++;

                //Заодно чистим весь список элементов
                partitionElements.get(index).isUsed = false;
            }
        }

        //Записываем значения разбиений в самое начало списка разбиений
        for (int index = 0; index < localNumOfElements; index++) {
            partitionElements.get(index).isUsed = true;
        }

        return true;
    }

    //Смысл функции в том, чтобы наложить значения в периодах для каждой из альтернатив
    //на перестановки границ, предварительно генерируемые
    public static AlternativeInductiveClass mapAlternative(ArrayList<ObservationMomentClass> observations) {
        int mappingIndex = 0;
        int index = -1;
        int lowerTime = observations.get(0).getObservationTime();
        int upperTime = observations.get(0).getObservationTime();
        int reduce = 0;


        ArrayList<PeriodInductiveClass> mapping = new ArrayList<>();
        mapping.add(new PeriodInductiveClass(upperTime, lowerTime, new ArrayList<>()));

        while (observations.size() - 1 > index) {
            do {
                index++;

                if (observations.size() == index) {
                    break;
                }

                upperTime = observations.get(index).getObservationTime() - reduce;

                mapping.get(mappingIndex).setUpperTime(upperTime);
                mapping.get(mappingIndex).getValues().add(observations.get(index).getValue());

                if (partitionElements.get(index).isUsed) {

                    mapping.add(new PeriodInductiveClass(upperTime, lowerTime, new ArrayList<>()));

                    reduce = mapping.get(mappingIndex).getUpperTime();

                    if (index < observations.size() - 1) {
                        lowerTime = observations.get(index + 1).getObservationTime() - reduce;
                    }

                    mappingIndex += 1;
                }

            } while (partitionElements.get(index).isUsed);

        }
        return new AlternativeInductiveClass(mapping);
    }
}