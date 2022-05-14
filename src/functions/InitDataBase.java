package functions;

import functions.database_classes.AttributeDataClass;
import functions.database_classes.HistoryDataClass;
import functions.database_classes.ObservationMomentClass;
import functions.database_classes.PeriodDataClass;
import functions.fundamental_knowledge.AttributeName;
import functions.fundamental_knowledge.DiseaseName;
import functions.fundamental_knowledge.ParameterType;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class InitDataBase {

    static int currentPeriodLine = 0;
    static int currentObservationLine = 0;
    
    private static String readPeriodData() {
        FileReader reader;
        String lText = "";

        try {
            reader = new FileReader("NumberOfObservations.txt");//TODO path to NumberOfObservations.txt
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

    private static String readObservationData() {
        FileReader reader;
        String lText = "";

        try {
            reader = new FileReader("ObservationData.txt");//TODO path to ObservationData.txt
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


    private static ArrayList<String[]> ToDataList(String inpData) {
        String[] firstStepData = inpData.split("\\r\\n|\\n");
        ArrayList<String[]> secondStepData = new ArrayList<>();

        for (String data: firstStepData) {
            secondStepData.add(data.split("\\t"));
        }

        return secondStepData;
    }

    private static ArrayList<ObservationMomentClass> initObservationMomentClass(ArrayList<String[]> observationData, int numOfObservations) {
        ArrayList<ObservationMomentClass> observationDataClasses = new ArrayList<>();

        for (int index = 0; index < numOfObservations; index++) {
            observationDataClasses.add(new ObservationMomentClass(
                    Integer.parseInt(observationData.get(currentObservationLine + index)[0]),
                    observationData.get(currentObservationLine + index)[1]
                    )
            );
        }

        currentObservationLine += numOfObservations;
        
        return observationDataClasses;
    }

    private static ArrayList<PeriodDataClass> initPeriodDataClass(ArrayList<String[]> periodData, ArrayList<String[]> observationData) {
        ArrayList<PeriodDataClass> periodDataClasses = new ArrayList<>();
        
        do {
            periodDataClasses.add(
                    new PeriodDataClass(
                            Integer.parseInt(periodData.get(currentPeriodLine)[0]),
                            Integer.parseInt(periodData.get(currentPeriodLine)[1]),
                            Integer.parseInt(periodData.get(currentPeriodLine)[2]),
                            initObservationMomentClass(observationData, Integer.parseInt(periodData.get(currentPeriodLine)[2]))
                    )
            );
            currentPeriodLine++;
            if (currentPeriodLine == periodData.size())
                break;
        } while (!Objects.equals(periodData.get(currentPeriodLine)[0], "1"));
        
        return periodDataClasses;
    }



private static ArrayList<AttributeDataClass> initAttributesData(ArrayList<String[]> periodData, ArrayList<String[]> observationData) {
    ArrayList<AttributeDataClass> attributesData = new ArrayList<>();

    attributesData.add(new AttributeDataClass(AttributeName.Стенокардия, ParameterType.БИНАРНЫЙ, initPeriodDataClass(periodData, observationData)));

    attributesData.add(new AttributeDataClass(AttributeName.Синкопе, ParameterType.БИНАРНЫЙ, initPeriodDataClass(periodData, observationData)));

    attributesData.add(new AttributeDataClass(AttributeName.Тотальная_сердечная_недостаточность, ParameterType.ПЕРЕЧИСЛИМЫЙ, initPeriodDataClass(periodData, observationData)));

    attributesData.add(new AttributeDataClass(AttributeName.Увеличение_сердца, ParameterType.ПЕРЕЧИСЛИМЫЙ, initPeriodDataClass(periodData, observationData)));

    attributesData.add(new AttributeDataClass(AttributeName.Гипертрофия_ЭКГ, ParameterType.ИНТЕРВАЛЬНЫЙ, initPeriodDataClass(periodData, observationData)));

    attributesData.add(new AttributeDataClass(AttributeName.Размер_левого_желудочка, ParameterType.ИНТЕРВАЛЬНЫЙ, initPeriodDataClass(periodData, observationData)));

    attributesData.add(new AttributeDataClass(AttributeName.Размер_левого_предсердия, ParameterType.ИНТЕРВАЛЬНЫЙ, initPeriodDataClass(periodData, observationData)));

    attributesData.add(new AttributeDataClass(AttributeName.Гипертрофия_ЭхоКГ, ParameterType.ПЕРЕЧИСЛИМЫЙ, initPeriodDataClass(periodData, observationData)));

    attributesData.add(new AttributeDataClass(AttributeName.Индекс_сократимости_левого_желудочка_в_фазе_изгнания, ParameterType.ПЕРЕЧИСЛИМЫЙ, initPeriodDataClass(periodData, observationData)));

    attributesData.add(new AttributeDataClass(AttributeName.Систолическое_движение_передней_створки_митрального_клапана_кпереди, ParameterType.ПЕРЕЧИСЛИМЫЙ, initPeriodDataClass(periodData, observationData)));


    return attributesData;
}

    public static ArrayList<HistoryDataClass> historyDataClasses() {
        ArrayList<HistoryDataClass> historyDataClasses = new ArrayList<>();

        ArrayList<String[]> periodData = ToDataList(readPeriodData());
        ArrayList<String[]> observationData = ToDataList(readObservationData());

        historyDataClasses.add(new HistoryDataClass(DiseaseName.Диалатационная_КМП, 1, initAttributesData(periodData, observationData)));

        historyDataClasses.add(new HistoryDataClass(DiseaseName.Диалатационная_КМП, 2, initAttributesData(periodData, observationData)));

        historyDataClasses.add(new HistoryDataClass(DiseaseName.Диалатационная_КМП, 3, initAttributesData(periodData, observationData)));

        historyDataClasses.add(new HistoryDataClass(DiseaseName.Диалатационная_КМП, 4, initAttributesData(periodData, observationData)));

        historyDataClasses.add(new HistoryDataClass(DiseaseName.Диалатационная_КМП, 5, initAttributesData(periodData, observationData)));

        historyDataClasses.add(new HistoryDataClass(DiseaseName.Гипертрофическая_КМП, 6, initAttributesData(periodData, observationData)));

        historyDataClasses.add(new HistoryDataClass(DiseaseName.Гипертрофическая_КМП, 7, initAttributesData(periodData, observationData)));

        historyDataClasses.add(new HistoryDataClass(DiseaseName.Гипертрофическая_КМП, 8, initAttributesData(periodData, observationData)));

        historyDataClasses.add(new HistoryDataClass(DiseaseName.Гипертрофическая_КМП, 9, initAttributesData(periodData, observationData)));

        historyDataClasses.add(new HistoryDataClass(DiseaseName.Гипертрофическая_КМП, 10, initAttributesData(periodData, observationData)));

        historyDataClasses.add(new HistoryDataClass(DiseaseName.Рестриктивная_КМП, 11, initAttributesData(periodData, observationData)));

        historyDataClasses.add(new HistoryDataClass(DiseaseName.Рестриктивная_КМП, 12, initAttributesData(periodData, observationData)));

        historyDataClasses.add(new HistoryDataClass(DiseaseName.Рестриктивная_КМП, 13, initAttributesData(periodData, observationData)));

        historyDataClasses.add(new HistoryDataClass(DiseaseName.Рестриктивная_КМП, 14, initAttributesData(periodData, observationData)));

        historyDataClasses.add(new HistoryDataClass(DiseaseName.Рестриктивная_КМП, 15, initAttributesData(periodData, observationData)));


        return historyDataClasses;
    }
}
