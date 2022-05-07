package functions;

import functions.inductive_alternatives_classes.AlternativeInductiveClass;
import functions.inductive_alternatives_classes.AlternativesByNumInductiveClass;
import functions.inductive_alternatives_classes.AttributeInductiveClass;
import functions.inductive_alternatives_classes.HistoryInductiveClass;
import functions.inductive_knowledge_classes.AllAlternativesCombinations;
import functions.inductive_knowledge_classes.AlternativeElement;
import functions.inductive_knowledge_classes.InductiveKnowledgeClass;

import java.util.ArrayList;

public class InitInductiveBase {

    public static void initInductiveBase(ArrayList<HistoryInductiveClass> alternativesForHistories) {
        ArrayList<InductiveKnowledgeClass> inductiveKnowledgeClasses = new ArrayList<>();
        int[] alternativesNum = {0, 0, 0, 0, 0};

        int[] maxAlternativesNum = {0, 0, 0, 0, 0};

        for (int typeOfHistory = 0; typeOfHistory < 3; typeOfHistory++) {

            ArrayList<AttributeInductiveClass> attributes = new ArrayList<>();
            //Проходимся по всем номерам признаков
            for (int attributeIndex = 0; attributeIndex < 10; attributeIndex++) {
                //Заглядываем в историю болезни и узнаём максимальное количество периодов динамики в истории болезни для интересующего нас признака
                for (int localIndex = 0; localIndex < 5; localIndex++)
                    maxAlternativesNum[localIndex] = alternativesForHistories.get(5 * typeOfHistory + localIndex)
                            .getAttributes().get(attributeIndex)
                            .getAlternativesByNum().size();

                int localMax = maxAlternativesNum[0];

                for (int localIndex = 0; localIndex < 5; localIndex++)
                    localMax = Math.min(localMax, maxAlternativesNum[localIndex]);

                ArrayList<AlternativesByNumInductiveClass> alternativesByNum = new ArrayList<>();

                //Считаем все сочетания в рамках localMax
                for (int numOfAltIndex = 0; numOfAltIndex < localMax; numOfAltIndex++) {
                    //Для начала составляем количество возможных альтернатив в перестановках
                    for (int secondIndex = 0; secondIndex < 5; secondIndex++) {
                        alternativesNum[secondIndex] = alternativesForHistories.get(5 * typeOfHistory + secondIndex)
                                .getAttributes().get(attributeIndex)
                                .getAlternativesByNum().get(numOfAltIndex)
                                .getAlternatives().size();
                    }

                    AllAlternativesCombinations.refreshElements(alternativesNum);

                    ArrayList<AlternativeInductiveClass> alternatives = new ArrayList<>();

                    do {
                        ArrayList<AlternativeElement> currentAlternatives = AllAlternativesCombinations.getAlternativeElements();

                        alternatives.add(AllAlternativesCombinations.combineAlternatives(
                                new AlternativeInductiveClass[]{
                                        alternativesForHistories.get(5 * typeOfHistory)
                                                .getAttributes().get(attributeIndex)
                                                .getAlternativesByNum().get(numOfAltIndex)
                                                .getAlternatives().get(currentAlternatives.get(0).getCurrentElement()),

                                        alternativesForHistories.get(5 * typeOfHistory + 1)
                                                .getAttributes().get(attributeIndex)
                                                .getAlternativesByNum().get(numOfAltIndex)
                                                .getAlternatives().get(currentAlternatives.get(1).getCurrentElement()),

                                        alternativesForHistories.get(5 * typeOfHistory + 2)
                                                .getAttributes().get(attributeIndex)
                                                .getAlternativesByNum().get(numOfAltIndex)
                                                .getAlternatives().get(currentAlternatives.get(2).getCurrentElement()),

                                        alternativesForHistories.get(5 * typeOfHistory + 3)
                                                .getAttributes().get(attributeIndex)
                                                .getAlternativesByNum().get(numOfAltIndex)
                                                .getAlternatives().get(currentAlternatives.get(3).getCurrentElement()),

                                        alternativesForHistories.get(5 * typeOfHistory + 4)
                                                .getAttributes().get(attributeIndex)
                                                .getAlternativesByNum().get(numOfAltIndex)
                                                .getAlternatives().get(currentAlternatives.get(4).getCurrentElement())

                                }
                        ));

                    } while (AllAlternativesCombinations.nextAlternativeCombination(0));


                    alternativesByNum.add(new AlternativesByNumInductiveClass(numOfAltIndex + 1, alternatives));
                }

                attributes.add(new AttributeInductiveClass(
                        alternativesForHistories.get(0).
                                getAttributes().get(attributeIndex).getName(),
                        alternativesForHistories.get(0).
                                getAttributes().get(attributeIndex).getParameterType(),
                        alternativesByNum
                ));

            }

            inductiveKnowledgeClasses.add(new InductiveKnowledgeClass(
                    alternativesForHistories.get(typeOfHistory).getName(),
                    attributes
            ));

        }

        ProgramGlobals.setInductiveKnowledgeBase(inductiveKnowledgeClasses);
    }

}
