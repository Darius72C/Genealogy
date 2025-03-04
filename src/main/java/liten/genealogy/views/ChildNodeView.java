/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import liten.genealogy.mainEntities.Person;
import org.primefaces.model.diagram.Element;

/**
 *
 * @author bayomock.a
 */
public class ChildNodeView extends MainNodeView implements Serializable {

    private Person selectedPartner;
    private Element partnerElement;
    private ParentNodeView partnerParent;
    private List<Person> partnerChildrenList;

    public ChildNodeView() {
        super();
        selectedPartner = new Person();
        partnerElement = new Element(selectedPartner);
        partnerParent = new ParentNodeView();
        this.partnerChildrenList = new ArrayList<Person>();
    }

    public ChildNodeView(Person curPerson) {
        super(curPerson);
        this.selectedPartner = new Person();
        this.partnerElement = new Element();
        this.partnerParent = new ParentNodeView();
        this.partnerChildrenList = new ArrayList<Person>();
    }

    public ChildNodeView(Person curPerson, Person partner) {
        super(curPerson);
        this.selectedPartner = partner;
        this.partnerParent = new ParentNodeView();
        this.partnerElement = new Element(this.selectedPartner.getLastName() + " " + this.selectedPartner.getFirstName());
        this.partnerChildrenList = new ArrayList<Person>();
    }

    public ChildNodeView(Person person, Person partner, Person father, Person mother) {
        super(person);
        this.selectedPartner = partner;
        this.partnerParent = new ParentNodeView();
        this.partnerElement = new Element(this.selectedPartner.getLastName() + " " + this.selectedPartner.getFirstName());
        this.partnerChildrenList = new ArrayList<Person>();
    }

    public Person getSelectedPartner() {
        return selectedPartner;
    }

    public void setSelectedPartner(Person selectedPartner) {
        this.selectedPartner = selectedPartner;
    }

    public Element getPartnerElement() {
        return partnerElement;
    }

    public void setPartnerElement(Element partnerElement) {
        this.partnerElement = partnerElement;
    }

    public ParentNodeView getPartnerParent() {
        return this.partnerParent;
    }

    public void setPartnerParent(ParentNodeView partnerParent) {
        this.partnerParent = partnerParent;
    }

    public List<Person> getPartnerChildrenList() {
        return this.partnerChildrenList;
    }

    public void setPartnerChildrenList(List<Person> partnerChildrenList) {
        this.partnerChildrenList = partnerChildrenList;
    }

    public void addChild(Person child) {
        if (selectedPartner != null && selectedPartner.getPersonID() != null) {
            assignFatherMother(child);
            partnerChildrenList.add(child);
        }
    }

    public void assignFatherMother(Person child) {
        if (this.getCurPerson().getPersonGenreList().get(0).getGenre().getName() == "Masculin") {
            child.setFather(this.getCurPerson());
            child.setMother(this.selectedPartner);
        } else {
            child.setMother(this.getCurPerson());
            child.setFather(this.selectedPartner);
        }
    }
}
