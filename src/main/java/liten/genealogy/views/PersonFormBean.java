package liten.genealogy.views;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import liten.genealogy.mainEntities.Person;
import liten.genealogy.utilitiesDB.MainDAO;

@Named
@SessionScoped
public class PersonFormBean implements Serializable {

    private String lastName;
    private String firstName;
    private String gender;
    private Date dateOfBirth;
    private String placeOfBirth;
    private String nationality;
    private String address;
    private String fatherFullName;
    private Date fatherDob;
    private String motherFullName;
    private Date motherDob;
    private boolean married;
    private List<String> spouseNames = new ArrayList<>();
    private List<List<String>> childrenNames = new ArrayList<>();

    
    public PersonFormBean(){
        spouseNames = new ArrayList<>();
        childrenNames = new ArrayList<>();
    }
    // Getters and Setters for all fields

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFatherFullName() {
        return fatherFullName;
    }

    public void setFatherFullName(String fatherFullName) {
        this.fatherFullName = fatherFullName;
    }

    public Date getFatherDob() {
        return fatherDob;
    }

    public void setFatherDob(Date fatherDob) {
        this.fatherDob = fatherDob;
    }

    public String getMotherFullName() {
        return motherFullName;
    }

    public void setMotherFullName(String motherFullName) {
        this.motherFullName = motherFullName;
    }

    public Date getMotherDob() {
        return motherDob;
    }

    public void setMotherDob(Date motherDob) {
        this.motherDob = motherDob;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public List<String> getSpouseNames() {
        return spouseNames;
    }

    public void setSpouseNames(List<String> spouseNames) {
        this.spouseNames = spouseNames;
    }

    public List<List<String>> getChildrenNames() {
        return childrenNames;
    }

    public void setChildrenNames(List<List<String>> childrenNames) {
        this.childrenNames = childrenNames;
    }

    // Initialize the form data
    @PostConstruct
    public void init() {
        // Add a default spouse if the user is married
        if (married) {
            spouseNames.add(""); // Add an empty spouse name
            childrenNames.add(new ArrayList<>()); // Initialize children list for the spouse
        }
    }
    
    // Method to add a spouse
    public void addSpouse() {
        spouseNames.add("");
        childrenNames.add(new ArrayList<>());
    }

    // Method to add a child for a specific spouse
    public void addChild(int spouseIndex) {
        childrenNames.get(spouseIndex).add("");
    }
    
    public void removeSpouse(int spouseIndex) {
    spouseNames.remove(spouseIndex);
    childrenNames.remove(spouseIndex);
}
    public void removeChild(int spouseIndex, int childIndex) {
    childrenNames.get(spouseIndex).remove(childIndex);
}
    public String submit() {
    // Create the root person
    Person rootPerson = new Person();
    rootPerson.setFirstName(this.firstName);
    rootPerson.setLastName(this.lastName);
    rootPerson.setDateOfBirth(this.dateOfBirth);
    rootPerson.setGenre(MainDAO.findGenre(this.gender.equals("homme") ? "GNR_1" : "GNR_2"));

    // Create parents
    Person father = new Person();
    father.setFirstName(this.fatherFullName.split(" ")[0]);
    father.setLastName(this.fatherFullName.split(" ")[1]);
    father.setDateOfBirth(this.fatherDob);
    father.setGenre(MainDAO.findGenre("GNR_1"));

    Person mother = new Person();
    mother.setFirstName(this.motherFullName.split(" ")[0]);
    mother.setLastName(this.motherFullName.split(" ")[1]);
    mother.setDateOfBirth(this.motherDob);
    mother.setGenre(MainDAO.findGenre("GNR_2"));

    // Add spouses and children
    if (this.married) {
        for (int i = 0; i < this.spouseNames.size(); i++) {
            Person spouse = new Person();
            spouse.setFirstName(this.spouseNames.get(i).split(" ")[0]);
            spouse.setLastName(this.spouseNames.get(i).split(" ")[1]);
            spouse.setGenre(MainDAO.findGenre(this.gender.equals("homme") ? "GNR_2" : "GNR_1"));

            // Add children
            for (String childName : this.childrenNames.get(i)) {
                Person child = new Person();
                child.setFirstName(childName.split(" ")[0]);
                child.setLastName(childName.split(" ")[1]);
                child.setGenre(MainDAO.findGenre("GNR_1")); // Default to male
                // Add child to spouse
            }
        }
    }

    // Navigate to the tree view page
    return "flowchart.xhtml?faces-redirect=true";
}
}