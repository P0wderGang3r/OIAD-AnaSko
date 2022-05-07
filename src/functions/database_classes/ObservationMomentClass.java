package functions.database_classes;

public class ObservationMomentClass {
    int observationTime;

    String value;

    public int getObservationTime() {
        return observationTime;
    }

    public String getValue() {
        return value;
    }

    public ObservationMomentClass(int observationTime, String value) {
        this.observationTime = observationTime;
        this.value = value;
    }
}
