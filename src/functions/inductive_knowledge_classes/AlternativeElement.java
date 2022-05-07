package functions.inductive_knowledge_classes;

public class AlternativeElement {
    private final int numberOfElements;

    private int currentElement;

    public int getCurrentElement() {
        return currentElement;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setCurrentElement(int currentElement) {
        this.currentElement = currentElement;
    }

    public boolean nextNumber() {
        currentElement++;
        return currentElement == numberOfElements;
    }

    public AlternativeElement(int numberOfElements) {
        this.numberOfElements = numberOfElements;
        currentElement = 0;
    }
}
