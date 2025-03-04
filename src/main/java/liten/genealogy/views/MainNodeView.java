/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import liten.genealogy.mainEntities.Person;
import liten.genealogy.utilitiesDB.MainDAO;
import org.primefaces.model.diagram.Element;

/**
 *
 * @author bayomock.a
 */
public class MainNodeView implements Serializable {

    private Person curPerson;
    private Element curElement;
    private List<Person> partnerList;
    private List<Person> childrenList;
    ParentNodeView curPersonParent;

    public MainNodeView() {
        this.curPerson = new Person();
        this.curElement = new Element();
        this.partnerList = new ArrayList<Person>();
        this.childrenList = new ArrayList<Person>();
        this.curPersonParent = new ParentNodeView();
    }

    public MainNodeView(Person curPerson) {
        this.curPerson = curPerson;
        this.curElement = new Element(this.curPerson.getLastName() +" "+this.curPerson.getFirstName());
        this.partnerList = new ArrayList<Person>();
        this.childrenList = new ArrayList<Person>();
        this.curPersonParent = new ParentNodeView();
    }

    public Person getCurPerson() {
        return curPerson;
    }

    public void setCurPerson(Person curPerson) {
        this.curPerson = curPerson;
    }

    public Element getCurElement() {
        return curElement;
    }

    public void setCurElement(Element curElement) {
        this.curElement = curElement;
    }

    public List<Person> getPartnerList() {
        return partnerList;
    }

    public void setPartnerList(List<Person> partnerList) {
        this.partnerList = partnerList;
    }

    public List<Person> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<Person>childrenList) {
        this.childrenList = childrenList;
    }

    public ParentNodeView getCurPersonParent() {
        return curPersonParent;
    }

    public void setCurPersonParent(ParentNodeView curPersonParent) {
        this.curPersonParent = curPersonParent;
    }

    public void addParents(Person father, Person mother) {
        curPersonParent.setFather(father);
        curPersonParent.setMother(mother);
    }

    public void addPartner(Person partner) {
        partnerList.add(partner);
    }
    
        public void addChild(Person child) {
        childrenList.add(child);
    }


    public boolean comparePerson(Person person1, Person person2) {
        boolean result = false;
        if (person1.getFirstName().equals(person2.getFirstName())
                && person1.getLastName().equals(person2.getLastName())
                && person1.getPlaceOfBirth().equals(person2.getPlaceOfBirth())
                && MainDAO.convertShortDate(person1.getDateOfBirth()).equals(MainDAO.convertShortDate(person2.getDateOfBirth()))) {
            result = true;
        }
        return result;
    }
}
