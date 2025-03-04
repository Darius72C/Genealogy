/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.views;

import java.io.Serializable;
import java.util.List;
import liten.genealogy.mainEntities.Person;
import org.primefaces.model.diagram.Element;

/**
 *
 * @author bayomock.a
 */
public class ParentNodeView implements Serializable {

    Person father;
    Person mother;
    Element fatherElement;
    Element motherElement;
    ParentNodeView nextFatherParentNode;
    ParentNodeView nextMotherParentNode;
    List<ChildNodeView> childrenList;

    public ParentNodeView() {
        father = new Person();
        mother = new Person();
        fatherElement = new Element();
        motherElement = new Element();
    }

    public ParentNodeView(Person father, Person mother) {
        this.father = father;
        this.mother = mother;
        this.fatherElement = new Element(father.getLastName()+ " "+father.getFirstName());
        this.motherElement = new Element(mother.getLastName()+ " "+mother.getFirstName());
    }

    public ParentNodeView(Person father, Person mother, ParentNodeView nextFatherParentNode, ParentNodeView nextMotherParentNode) {
        this.father = father;
        this.mother = mother;
        this.fatherElement = new Element(father.getLastName()+ " "+father.getFirstName());
        this.motherElement = new Element(mother.getLastName()+ " "+mother.getFirstName());
        this.nextFatherParentNode = nextFatherParentNode;
        this.nextMotherParentNode = nextMotherParentNode;
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public Element getFatherElement() {
        return fatherElement;
    }

    public void setFatherElement(Element fatherElement) {
        this.fatherElement = fatherElement;
    }

    public Element getMotherElement() {
        return motherElement;
    }

    public void setMotherElement(Element motherElement) {
        this.motherElement = motherElement;
    }

    public ParentNodeView getNextFatherParentNode() {
        return nextFatherParentNode;
    }

    public void setNextFatherParentNode(ParentNodeView nextFatherParentNode) {
        this.nextFatherParentNode = nextFatherParentNode;
    }

    public ParentNodeView getNextMotherParentNode() {
        return nextMotherParentNode;
    }

    public void setNextMotherParentNode(ParentNodeView nextMotherParentNode) {
        this.nextMotherParentNode = nextMotherParentNode;
    }

    public List<ChildNodeView> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<ChildNodeView> childrenList) {
        this.childrenList = childrenList;
    }

}
