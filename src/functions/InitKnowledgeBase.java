package functions;

import functions.fundamental_knowledge.AttributeName;
import functions.fundamental_knowledge.DiseaseName;
import functions.fundamental_knowledge.ParameterType;
import functions.knowledge_classes.AttributeKnowledgeClass;
import functions.knowledge_classes.DiseaseKnowledgeClass;
import functions.knowledge_classes.PeriodKnowledgeClass;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InitKnowledgeBase {
    static int currentLine = 0;
    private static String readKnowledgeData() {
        FileReader reader;
        String lText = "";

        try {
            reader = new FileReader("/home/anasko/ForProjects/input/ValuesByPeriod.txt");
        } catch (Exception e) {
            return "Error";
        }

        int inp = 0;
        try {
            inp = reader.read();

            while (inp != -1) {
                lText = lText + (char) inp;
                inp = reader.read();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lText;
    }

    private static ArrayList<String[]> knowledgeBaseDataList(String knowledgeData) {
        String[] firstStepData = knowledgeData.split("\\r\\n|\\n");
        ArrayList<String[]> secondStepData = new ArrayList<>();

        for (String data: firstStepData) {
            secondStepData.add(data.split("\\t"));
        }

        return secondStepData;
    }

    private static ArrayList<PeriodKnowledgeClass> initDynamicPeriodClass(ArrayList<String[]> knowledgeData) {
        ArrayList<PeriodKnowledgeClass> periodKnowledgeClasses = new ArrayList<>();

        do {
            periodKnowledgeClasses.add(
                    new PeriodKnowledgeClass(
                            Integer.parseInt(knowledgeData.get(currentLine)[0]),
                            Integer.parseInt(knowledgeData.get(currentLine)[2]),
                            Integer.parseInt(knowledgeData.get(currentLine)[3]),
                            new ArrayList<>(List.of(knowledgeData.get(currentLine)[1].split("; ")))
                    )
            );
            currentLine++;
            if (currentLine == knowledgeData.size())
                break;
        } while (!Objects.equals(knowledgeData.get(currentLine)[0], "1"));

        return periodKnowledgeClasses;
    }

    private static ArrayList<AttributeKnowledgeClass> initAttributeClass(ArrayList<String[]> knowledgeData) {
        ArrayList<AttributeKnowledgeClass> attributeKnowledgeClasses = new ArrayList<>();

        attributeKnowledgeClasses.add(new AttributeKnowledgeClass(AttributeName.Стенокардия, ParameterType.БИНАРНЫЙ, initDynamicPeriodClass(knowledgeData)));

        attributeKnowledgeClasses.add(new AttributeKnowledgeClass(AttributeName.Синкопе, ParameterType.БИНАРНЫЙ, initDynamicPeriodClass(knowledgeData)));

        attributeKnowledgeClasses.add(new AttributeKnowledgeClass(AttributeName.Тотальная_сердечная_недостаточность, ParameterType.ПЕРЕЧИСЛИМЫЙ, initDynamicPeriodClass(knowledgeData)));

        attributeKnowledgeClasses.add(new AttributeKnowledgeClass(AttributeName.Увеличение_сердца, ParameterType.ПЕРЕЧИСЛИМЫЙ, initDynamicPeriodClass(knowledgeData)));

        attributeKnowledgeClasses.add(new AttributeKnowledgeClass(AttributeName.Гипертрофия_ЭКГ, ParameterType.ИНТЕРВАЛЬНЫЙ, initDynamicPeriodClass(knowledgeData)));

        attributeKnowledgeClasses.add(new AttributeKnowledgeClass(AttributeName.Размер_левого_желудочка, ParameterType.ИНТЕРВАЛЬНЫЙ, initDynamicPeriodClass(knowledgeData)));

        attributeKnowledgeClasses.add(new AttributeKnowledgeClass(AttributeName.Размер_левого_предсердия, ParameterType.ИНТЕРВАЛЬНЫЙ, initDynamicPeriodClass(knowledgeData)));

        attributeKnowledgeClasses.add(new AttributeKnowledgeClass(AttributeName.Гипертрофия_ЭхоКГ, ParameterType.ПЕРЕЧИСЛИМЫЙ, initDynamicPeriodClass(knowledgeData)));

        attributeKnowledgeClasses.add(new AttributeKnowledgeClass(AttributeName.Индекс_сократимости_левого_желудочка_в_фазе_изгнания, ParameterType.ПЕРЕЧИСЛИМЫЙ, initDynamicPeriodClass(knowledgeData)));

        attributeKnowledgeClasses.add(new AttributeKnowledgeClass(AttributeName.Систолическое_движение_передней_створки_митрального_клапана_кпереди, ParameterType.ПЕРЕЧИСЛИМЫЙ, initDynamicPeriodClass(knowledgeData)));

        return attributeKnowledgeClasses;
    }

    public static ArrayList<DiseaseKnowledgeClass> initDiseaseClass() {
        ArrayList<DiseaseKnowledgeClass> diseaseKnowledgeClasses = new ArrayList<>();

        String firstStepKnowledgeData = InitKnowledgeBase.readKnowledgeData();

        ArrayList<String[]> knowledgeData = InitKnowledgeBase.knowledgeBaseDataList(firstStepKnowledgeData);

        diseaseKnowledgeClasses.add(new DiseaseKnowledgeClass(DiseaseName.Диалатационная_КМП, initAttributeClass(knowledgeData)));

        diseaseKnowledgeClasses.add(new DiseaseKnowledgeClass(DiseaseName.Гипертрофическая_КМП, initAttributeClass(knowledgeData)));

        diseaseKnowledgeClasses.add(new DiseaseKnowledgeClass(DiseaseName.Рестриктивная_КМП, initAttributeClass(knowledgeData)));

        return diseaseKnowledgeClasses;
    }
}
