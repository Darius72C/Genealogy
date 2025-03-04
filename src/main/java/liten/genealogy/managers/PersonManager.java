/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.managers;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import liten.genealogy.mainEntities.Person;
import liten.genealogy.mainEntities.PersonDiploma;
import liten.genealogy.mainEntities.PersonPicture;
import liten.genealogy.mainEntities.PersonResume;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import liten.genealogy.utilitiesDB.DataManager;
import liten.genealogy.utilitiesDB.KeyGeneration;
import liten.genealogy.utilitiesDB.MainDAO;
/**
 *
 * @author darre
 */
@Named
@SessionScoped
public class PersonManager implements Serializable{
    
    public PersonManager() {
        
    }
    
    public Person addNewPerson(Person person){
        Person personDB = null;
        try{
            final DataManager connectionServiceFacade = DataManager.getInstance();
            if(person != null && person.getPersonID() == null){
                String personID = KeyGeneration.generatePrimaryKey(Class.forName("mainEntities.Person"));
                personDB.setPersonID(personID);
            }
            personDB = connectionServiceFacade.mergeEntity(person);
        }   catch(Exception e){
            e.printStackTrace();
        }
        return personDB;
    }
    
    public PersonDiploma addNewPersonDiploma(PersonDiploma personDiploma){
        PersonDiploma personDiplomaDB = null;
        try{
            final DataManager connectionServiceFacade = DataManager.getInstance();
            if(personDiploma != null && personDiploma.getPersonID() == null){
                String personID = KeyGeneration.generatePrimaryKey(Class.forName("mainEntities.PersonDiploma"));
                personDiplomaDB.setPersonID(personID);
            }
            personDiplomaDB = connectionServiceFacade.mergeEntity(personDiploma);
        }   catch(Exception e){
            e.printStackTrace();
        }
        return personDiplomaDB;
    }
    
    public PersonPicture addNewPersonPicture(PersonPicture personPicture){
        PersonPicture personPictureDB = null;
        try{
            final DataManager connectionServiceFacade = DataManager.getInstance();
            if(personPicture != null && personPicture.getPersonID() == null){
                String personID = KeyGeneration.generatePrimaryKey(Class.forName("mainEntities.PersonPicture"));
                personPictureDB.setPersonID(personID);
            }
            personPictureDB = connectionServiceFacade.mergeEntity(personPicture);
        }   catch(Exception e){
            e.printStackTrace();
        }
        return personPictureDB;
    }
    
    public void updatePerson (Person person){
        try{
            final DataManager connectionServiceFacade = DataManager.getInstance();
            Person personDB = MainDAO.findPerson(person.getPersonID());
            if(personDB != null) {
                personDB = connectionServiceFacade.mergeEntity(person);
            }
        } catch(Exception e){
            e.printStackTrace();
        }          
    }
    
    public void updatePersonDiploma (PersonDiploma personDiploma){
        try{
            final DataManager connectionServiceFacade = DataManager.getInstance();
            PersonDiploma personDiplomaDB = MainDAO.findPersonDiploma(personDiploma.getPersonID());
            if(personDiplomaDB != null) {
                personDiplomaDB = connectionServiceFacade.mergeEntity(personDiploma);
            }
        } catch(Exception e){
            e.printStackTrace();
        }          
    }
    
    public void updatePersonPicture (PersonPicture personPicture){
        try{
            final DataManager connectionServiceFacade = DataManager.getInstance();
            PersonPicture personPictureDB = MainDAO.findPersonPicture(personPicture.getPersonID());
            if(personPictureDB != null) {
                personPictureDB = connectionServiceFacade.mergeEntity(personPicture);
            }
        } catch(Exception e){
            e.printStackTrace();
        }          
    }
    
    public void updatePersonResume (PersonResume personResume){
        try{
            final DataManager connectionServiceFacade = DataManager.getInstance();
            PersonResume personResumeDB = MainDAO.findPersonResume(personResume.getPersonID());
            if(personResumeDB != null) {
                personResumeDB = connectionServiceFacade.mergeEntity(personResume);
            }
        } catch(Exception e){
            e.printStackTrace();
        }          
    }
    
    public void deletePerson(String PersonID) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            Person personDB = MainDAO.findPerson(PersonID);
            if (personDB != null) {
                connectionServiceFacade.removeEntity(personDB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deletePersonDiploma(String PersonID) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            PersonDiploma personDiplomaDB = MainDAO.findPersonDiploma(PersonID);
            if (personDiplomaDB != null) {
                connectionServiceFacade.removeEntity(personDiplomaDB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deletePersonPicture(String PersonID) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            PersonPicture personPictureDB = MainDAO.findPersonPicture(PersonID);
            if (personPictureDB != null) {
                connectionServiceFacade.removeEntity(personPictureDB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deletePersonResume(String PersonID) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            PersonResume personResumeDB = MainDAO.findPersonResume(PersonID);
            if (personResumeDB != null) {
                connectionServiceFacade.removeEntity(personResumeDB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Person findPerson(String personID){
        Person person = null;
        try{
            person = MainDAO.findPerson(personID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return person;
    }
    
    public PersonDiploma findPersonDiploma(String personID){
        PersonDiploma personDiploma = null;
        try{
            personDiploma = MainDAO.findPersonDiploma(personID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personDiploma;
    }
    
    public PersonPicture findPersonPicture(String personID){
        PersonPicture personPicture = null;
        try{
            personPicture = MainDAO.findPersonPicture(personID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personPicture;
    }
    
    public PersonResume findPersonResume(String personID){
        PersonResume personResume = null;
        try{
            personResume = MainDAO.findPersonResume(personID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personResume;
    }
    
    public List<Person> findAllPerson() {
        List<Person> myPersonList = MainDAO.findAllPerson();
        return myPersonList;
    }
    
    public List<PersonDiploma> findAllDiplomaPerson(String personID) {
        List<PersonDiploma> myPersonDiplomaList = MainDAO.findAllDiplomaPerPerson(personID);
        return myPersonDiplomaList;
    }
    
    public List<PersonPicture> findAllPicturePerPerson(String personID) {
        List<PersonPicture> myPersonPictureList = MainDAO.findAllPicturePerPerson(personID);
        return myPersonPictureList;
    }
}
