import bz_ifbz_comparison.ComparisonFunctions;
import functions.*;
import functions.inductive_alternatives_classes.HistoryInductiveClass;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ProgramGlobals.setBasicKnowledgeBase(InitKnowledgeBase.initDiseaseClass());
        //GlobalLogs.writeBasicKnowledge();

        ProgramGlobals.setDiseaseHistoriesData(InitDataBase.historyDataClasses());
        //GlobalLogs.writeDataBase();

        ArrayList<HistoryInductiveClass> alternativesForHistories = InitDirtyInductiveBase.initNonCheckedHistoriesBase();
        FilterDirtyInductiveBase.emptyPeriodInAlternativeFilter(alternativesForHistories);
        FilterDirtyInductiveBase.equalInOnePeriodFilter(alternativesForHistories);
        FilterDirtyInductiveBase.equalInNeighborFilter(alternativesForHistories);
        FilterDirtyInductiveBase.emptyAlternativeByNumFilter(alternativesForHistories);
        //GlobalLogs.writeDirtyInductiveBase(alternativesForHistories);

        InitInductiveBase.initInductiveBase(alternativesForHistories);
        FilterInductiveBase.equalInNeighborFilter(ProgramGlobals.getInductiveKnowledgeBase());
        FilterInductiveBase.emptyAlternativeByNumFilter(ProgramGlobals.getInductiveKnowledgeBase());
        GlobalLogs.writeInductiveBase(ProgramGlobals.getInductiveKnowledgeBase());

        //ComparisonFunctions.compareOnNumOfPeriods();
        //ComparisonFunctions.showGlobalPercentage();
        //ComparisonFunctions.comparisonOfElementsInPeriods();
        //ComparisonFunctions.comparisonOfPeriodsByAttributes();
    }
}
