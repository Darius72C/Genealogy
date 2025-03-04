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
public class RootNodeView extends MainNodeView implements Serializable {

    private Person selectedPartner;
    private Element partnerElement;
    private List<ChildNodeView> partnerChildrenList;
    private ParentNodeView partnerParent;

    public RootNodeView() {
        super();
        this.selectedPartner = new Person();
        this.partnerElement = new Element();
        this.partnerChildrenList = new ArrayList<ChildNodeView>();
        this.partnerParent = new ParentNodeView();
    }

    public RootNodeView(Person curPerson, Person selectedPartner) {
        super(curPerson);
        this.selectedPartner = selectedPartner;
        this.partnerElement = new Element(this.selectedPartner.getLastName()+" "+this.selectedPartner.getFirstName());
        this.partnerChildrenList = new ArrayList<ChildNodeView>();
        this.partnerParent = new ParentNodeView();
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

    public List<ChildNodeView> getPartnerChildrenList() {
        return partnerChildrenList;
    }

    public void setPartnerChildrenList(List<ChildNodeView> partnerChildrenList) {
        this.partnerChildrenList = partnerChildrenList;
    }

    public ParentNodeView getPartnerParent() {
        return this.partnerParent;
    }

    public void setPartnerParent(ParentNodeView partnerParent) {
        this.partnerParent = partnerParent;
    }

    public void addParents(Person father, Person mother) {
        curPersonParent.setFather(father);
        curPersonParent.setMother(mother);
    }

    public void addPartnerParents(Person father, Person mother) {
        partnerParent.setFather(father);
        partnerParent.setMother(mother);
    }

    public void addChild(Person child) {
        if (selectedPartner != null && selectedPartner.getPersonID() != null) {
            assignFatherMother(child);
            getChildrenList().add(child);
            partnerChildrenList.add(new ChildNodeView(child, new Person()));
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
