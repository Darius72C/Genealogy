/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.views;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import liten.genealogy.mainEntities.Person;
import liten.genealogy.mainEntities.PersonDiploma;
import liten.genealogy.mainEntities.PersonPicture;
import liten.genealogy.mainEntities.PersonResume;
/**
 *
 * @author darre
 */
@Named
@SessionScoped
public class PersonModelView implements Serializable {
    String PersonID;
    Person person;
    PersonDiploma personDiploma;
    PersonPicture personPicture;
    PersonResume personResume;
    List<Person> personList;
    List<PersonDiploma> personDiplomaList;
    List<PersonPicture> personPictureList;
    List<PersonResume> personResumeList;

    public PersonModelView() {
    }

    public String getPersonID() {
        return PersonID;
    }

    public void setPersonID(String PersonID) {
        this.PersonID = PersonID;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public PersonDiploma getPersonDiploma() {
        return personDiploma;
    }

    public void setPersonDiploma(PersonDiploma personDiploma) {
        this.personDiploma = personDiploma;
    }

    public PersonPicture getPersonPicture() {
        return personPicture;
    }

    public void setPersonPicture(PersonPicture personPicture) {
        this.personPicture = personPicture;
    }

    public PersonResume getPersonResume() {
        return personResume;
    }

    public void setPersonResume(PersonResume personResume) {
        this.personResume = personResume;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public List<PersonDiploma> getPersonDiplomaList() {
        return personDiplomaList;
    }

    public void setPersonDiplomaList(List<PersonDiploma> personDiplomaList) {
        this.personDiplomaList = personDiplomaList;
    }

    public List<PersonPicture> getPersonPictureList() {
        return personPictureList;
    }

    public void setPersonPictureList(List<PersonPicture> personPictureList) {
        this.personPictureList = personPictureList;
    }

    public List<PersonResume> getPersonResumeList() {
        return personResumeList;
    }

    public void setPersonResumeList(List<PersonResume> personResumeList) {
        this.personResumeList = personResumeList;
    }
    
    
}
