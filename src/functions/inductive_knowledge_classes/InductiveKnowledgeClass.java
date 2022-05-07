package functions.inductive_knowledge_classes;

import functions.fundamental_knowledge.DiseaseName;
import functions.inductive_alternatives_classes.AttributeInductiveClass;

import java.util.ArrayList;

public class InductiveKnowledgeClass {
    DiseaseName name;

    ArrayList<AttributeInductiveClass> attributes;

    public DiseaseName getName() {
        return name;
    }

    public ArrayList<AttributeInductiveClass> getAttributes() {
        return attributes;
    }

    public InductiveKnowledgeClass(DiseaseName name, ArrayList<AttributeInductiveClass> attributes) {
        this.name = name;
        this.attributes = attributes;
    }
}
