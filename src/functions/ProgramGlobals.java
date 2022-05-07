package functions;

import functions.database_classes.HistoryDataClass;
import functions.inductive_knowledge_classes.InductiveKnowledgeClass;
import functions.knowledge_classes.DiseaseKnowledgeClass;

import java.util.ArrayList;

public class ProgramGlobals {
    private static ArrayList<DiseaseKnowledgeClass> BasicKnowledgeBase;

    public static ArrayList<DiseaseKnowledgeClass> getBasicKnowledgeBase() {
        return BasicKnowledgeBase;
    }

    public static void setBasicKnowledgeBase(ArrayList<DiseaseKnowledgeClass> basicKnowledgeBase) {
        BasicKnowledgeBase = basicKnowledgeBase;
    }

    private static ArrayList<HistoryDataClass> DiseaseHistoriesData;

    public static ArrayList<HistoryDataClass> getDiseaseHistoriesData() {
        return DiseaseHistoriesData;
    }

    public static void setDiseaseHistoriesData(ArrayList<HistoryDataClass> diseaseHistoriesData) {
        DiseaseHistoriesData = diseaseHistoriesData;
    }

    private static ArrayList<InductiveKnowledgeClass> inductiveKnowledgeBase;

    public static ArrayList<InductiveKnowledgeClass> getInductiveKnowledgeBase() {
        return inductiveKnowledgeBase;
    }

    public static void setInductiveKnowledgeBase(ArrayList<InductiveKnowledgeClass> inductiveKnowledgeBase) {
        ProgramGlobals.inductiveKnowledgeBase = inductiveKnowledgeBase;
    }
}
