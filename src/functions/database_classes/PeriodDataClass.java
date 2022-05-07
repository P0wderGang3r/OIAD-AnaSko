package functions.database_classes;

import java.util.ArrayList;

public class PeriodDataClass {
    int numberOfDynamicPeriod;

    int dynamicPeriodTime;

    int numberOfObservationMoments;

    ArrayList<ObservationMomentClass> observationMomentClasses;

    public int getNumberOfDynamicPeriod() {
        return numberOfDynamicPeriod;
    }

    public int getDynamicPeriodTime() {
        return dynamicPeriodTime;
    }

    public int getNumberOfObservationMoments() {
        return numberOfObservationMoments;
    }

    public ArrayList<ObservationMomentClass> getObservationMomentClasses() {
        return observationMomentClasses;
    }

    public PeriodDataClass(int numberOfDynamicPeriod, int dynamicPeriodTime, int numberOfObservationMoments,
                           ArrayList<ObservationMomentClass> observationMomentClasses) {
         this.numberOfDynamicPeriod = numberOfDynamicPeriod;
         this.dynamicPeriodTime = dynamicPeriodTime;
         this.numberOfObservationMoments = numberOfObservationMoments;
         this.observationMomentClasses = observationMomentClasses;
     }
}
