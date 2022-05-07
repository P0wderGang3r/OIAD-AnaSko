package functions.knowledge_classes;

import functions.fundamental_knowledge.DiseaseName;

import java.util.ArrayList;

public class DiseaseKnowledgeClass {
    DiseaseName name;

    ArrayList<AttributeKnowledgeClass> attributeKnowledgeClasses;

    public DiseaseName getName() {
        return name;
    }

    public ArrayList<AttributeKnowledgeClass> getAttributeClasses() {
        return attributeKnowledgeClasses;
    }

    public DiseaseKnowledgeClass(DiseaseName name, ArrayList<AttributeKnowledgeClass> attributeKnowledgeClasses) {
        this.name = name;
        this.attributeKnowledgeClasses = attributeKnowledgeClasses;
    }
}

