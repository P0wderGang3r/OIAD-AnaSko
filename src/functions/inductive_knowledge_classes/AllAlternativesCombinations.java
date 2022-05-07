package functions.inductive_knowledge_classes;

import functions.inductive_alternatives_classes.AlternativeInductiveClass;

import java.util.ArrayList;

public class AllAlternativesCombinations {
    static ArrayList<AlternativeElement> alternativeElements = new ArrayList<>(5);

    public static void refreshElements(int[] size) {
        alternativeElements.clear();

        for (int index = 0; index < 5; index++) {
            alternativeElements.add(new AlternativeElement(size[index]));
        }
    }

    public static ArrayList<AlternativeElement> getAlternativeElements() {
        return alternativeElements;
    }

    public static boolean nextAlternativeCombination(int currHead) {
        boolean response = true;

        if (currHead == alternativeElements.size()) {
            return false;
        }

        if (alternativeElements.get(currHead).nextNumber()) {
            response = nextAlternativeCombination(currHead + 1);

            alternativeElements.get(currHead).setCurrentElement(0);
        }

        return response;
    }

    public static AlternativeInductiveClass combineAlternatives(AlternativeInductiveClass[] alternativesToCombine) {
        AlternativeInductiveClass alternative = new AlternativeInductiveClass(alternativesToCombine[0].getPeriods());

        /*
        System.out.println(alternativeElements.get(0).getCurrentElement() + " " +
                alternativeElements.get(1).getCurrentElement() + " " +
                alternativeElements.get(2).getCurrentElement() + " " +
                alternativeElements.get(3).getCurrentElement() + " " +
                alternativeElements.get(4).getCurrentElement() + " ");

         */

        for (AlternativeInductiveClass localAlternative: alternativesToCombine) {
            for (int index = 0; index < localAlternative.getPeriods().size(); index++) {
                //В каждый период генерируемой альтернативы добавляем все значения из периода

                for (String secondValue: localAlternative.getPeriods().get(index).getValues()) {
                    boolean isEqual = false;
                    for (String value: alternative.getPeriods().get(index).getValues()) {
                        if (value.equals(secondValue)) {
                            isEqual = true;
                            break;
                        }
                    }
                    if (!isEqual) {
                        alternative.getPeriods().get(index).getValues().add(secondValue);
                    }
                }

                if (alternative.getPeriods().get(index).getUpperTime() < localAlternative.getPeriods().get(index).getUpperTime()) {
                    alternative.getPeriods().get(index).setUpperTime(localAlternative.getPeriods().get(index).getUpperTime());
                }
                if (alternative.getPeriods().get(index).getLowerTime() > localAlternative.getPeriods().get(index).getLowerTime()) {
                    alternative.getPeriods().get(index).setLowerTime(localAlternative.getPeriods().get(index).getLowerTime());
                }
            }
        }

        return alternative;
    }
}
