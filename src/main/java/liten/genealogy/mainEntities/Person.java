/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.mainEntities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bayomock.a
 */
@Entity
@Table(name = "person")
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findByPersonID", query = "SELECT p FROM Person p WHERE p.personID = :personID"),
    @NamedQuery(name = "Person.findByFirstName", query = "SELECT p FROM Person p WHERE p.firstName = :firstName"),
    @NamedQuery(name = "Person.findByLastName", query = "SELECT p FROM Person p WHERE p.lastName = :lastName"),
    @NamedQuery(name = "Person.findByDateOfBirth", query = "SELECT p FROM Person p WHERE p.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "Person.findByPlaceOfBirth", query = "SELECT p FROM Person p WHERE p.placeOfBirth = :placeOfBirth"),
    @NamedQuery(name = "Person.findByCreatedDate", query = "SELECT p FROM Person p WHERE p.createdDate = :createdDate"),
    @NamedQuery(name = "Person.findByModifiedDate", query = "SELECT p FROM Person p WHERE p.modifiedDate = :modifiedDate")})

public class Person implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "firstName")
    private String firstName;
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 80)
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "dateOfBirth")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;
    @Size(max = 100)
    @Column(name = "placeOfBirth")
    private String placeOfBirth;
    @Lob()
    @Column(name = "presentationAudio")
    private byte[] presentationAudio;
    @Lob()
    @Column(name = "presentationVideo")
    private byte[] presentationVideo;
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "modifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @JoinColumn(name = "genreID", referencedColumnName = "genreID")
    @ManyToOne
    private Genre genre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "updatedBy")
    @JsonBackReference
    private List<EndofLife> endofLifeList;
    @OneToMany(mappedBy = "updateBy")
    @JsonBackReference
    private List<AdminFatherTree> adminFatherTreeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admin")
    @JsonBackReference
    private List<AdminFatherTree> adminFatherTreeList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    @JsonBackReference
    private List<PersonGenre> personGenreList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "updateBy")
    @JsonBackReference
    private List<FatherFamilyTree> fatherFamilyTreeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    @JsonBackReference
    private List<FatherFamilyTree> fatherFamilyTreeList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "updateBy")
    @JsonBackReference
    private List<MotherFamilyTree> motherFamilyTreeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    @JsonBackReference
    private List<MotherFamilyTree> motherFamilyTreeList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wife")
    @JsonBackReference
    private List<KnownUnions> knownUnionsList;
    @OneToMany(mappedBy = "updateBy")
    @JsonBackReference
    private List<KnownUnions> knownUnionsList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "husband")
    @JsonBackReference
    private List<KnownUnions> knownUnionsList2;
    @JoinColumn(name = "motherID", referencedColumnName = "personID")
    @ManyToOne(optional = false)
    @JsonManagedReference
    private Person mother;
    @JoinColumn(name = "fatherID", referencedColumnName = "personID")
    @ManyToOne
    @JsonManagedReference
    private Person father;
    @OneToMany(mappedBy = "updateBy")
    @JsonBackReference
    private List<AdminMotherTree> adminMotherTreeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admin")
    @JsonBackReference
    private List<AdminMotherTree> adminMotherTreeList1;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "personID")
    private String personID;
    @OneToMany(mappedBy = "person")
    @JsonBackReference
    private List<AdminFamilyTree> adminFamilyTreeList;
    @OneToMany(mappedBy = "person")
    @JsonBackReference
    private List<Client> clientList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    @JsonManagedReference
    private PersonResume personResume;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    @JsonManagedReference
    private EndofLife endofLife;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    @JsonManagedReference
    private PersonPicture personPicture;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    @JsonManagedReference
    private PersonDiploma personDiploma;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mother")
    @JsonBackReference
    private List<MotherChildren> motherChildrenList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "father")
    @JsonBackReference
    private List<FatherChildren> fatherChildrenList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "updateBy")
    @JsonBackReference
    private List<Person> personList;
    @JoinColumn(name = "updateBy", referencedColumnName = "personID")
    @ManyToOne(optional = false)
    @JsonManagedReference
    private Person updateBy;

    public Person() {
    }

    public Person(String personID) {
        this.personID = personID;
    }

    public Person(String personID, String firstName, String lastName, Date dateOfBirth, String placeOfBirth, Date createdDate, Date modifiedDate) {
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public List<MotherChildren> getMotherChildrenList() {
        return motherChildrenList;
    }

    public void setMotherChildrenList(List<MotherChildren> motherChildrenList) {
        this.motherChildrenList = motherChildrenList;
    }

    public List<FatherChildren> getFatherChildrenList() {
        return fatherChildrenList;
    }

    public void setFatherChildrenList(List<FatherChildren> fatherChildrenList) {
        this.fatherChildrenList = fatherChildrenList;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public Person getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Person updateBy) {
        this.updateBy = updateBy;
    }

    public PersonResume getPersonResume() {
        return personResume;
    }

    public void setPersonResume(PersonResume personResume) {
        this.personResume = personResume;
    }

    public EndofLife getEndofLife() {
        return endofLife;
    }

    public void setEndofLife(EndofLife endofLife) {
        this.endofLife = endofLife;
    }

    public PersonPicture getPersonPicture() {
        return personPicture;
    }

    public void setPersonPicture(PersonPicture personPicture) {
        this.personPicture = personPicture;
    }

    public PersonDiploma getPersonDiploma() {
        return personDiploma;
    }

    public void setPersonDiploma(PersonDiploma personDiploma) {
        this.personDiploma = personDiploma;
    }

    public List<AdminFamilyTree> getAdminFamilyTreeList() {
        return adminFamilyTreeList;
    }

    public void setAdminFamilyTreeList(List<AdminFamilyTree> adminFamilyTreeList) {
        this.adminFamilyTreeList = adminFamilyTreeList;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personID != null ? personID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.personID == null && other.personID != null) || (this.personID != null && !this.personID.equals(other.personID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.Person[ personID=" + personID + " ]";
    }

    @XmlTransient
    public List<AdminFatherTree> getAdminFatherTreeList() {
        return adminFatherTreeList;
    }

    public void setAdminFatherTreeList(List<AdminFatherTree> adminFatherTreeList) {
        this.adminFatherTreeList = adminFatherTreeList;
    }

    @XmlTransient
    public List<AdminFatherTree> getAdminFatherTreeList1() {
        return adminFatherTreeList1;
    }

    public void setAdminFatherTreeList1(List<AdminFatherTree> adminFatherTreeList1) {
        this.adminFatherTreeList1 = adminFatherTreeList1;
    }

    @XmlTransient
    public List<PersonGenre> getPersonGenreList() {
        return personGenreList;
    }

    public void setPersonGenreList(List<PersonGenre> personGenreList) {
        this.personGenreList = personGenreList;
    }

    @XmlTransient
    public List<FatherFamilyTree> getFatherFamilyTreeList() {
        return fatherFamilyTreeList;
    }

    public void setFatherFamilyTreeList(List<FatherFamilyTree> fatherFamilyTreeList) {
        this.fatherFamilyTreeList = fatherFamilyTreeList;
    }

    @XmlTransient
    public List<FatherFamilyTree> getFatherFamilyTreeList1() {
        return fatherFamilyTreeList1;
    }

    public void setFatherFamilyTreeList1(List<FatherFamilyTree> fatherFamilyTreeList1) {
        this.fatherFamilyTreeList1 = fatherFamilyTreeList1;
    }

    @XmlTransient
    public List<MotherFamilyTree> getMotherFamilyTreeList() {
        return motherFamilyTreeList;
    }

    public void setMotherFamilyTreeList(List<MotherFamilyTree> motherFamilyTreeList) {
        this.motherFamilyTreeList = motherFamilyTreeList;
    }

    @XmlTransient
    public List<MotherFamilyTree> getMotherFamilyTreeList1() {
        return motherFamilyTreeList1;
    }

    public void setMotherFamilyTreeList1(List<MotherFamilyTree> motherFamilyTreeList1) {
        this.motherFamilyTreeList1 = motherFamilyTreeList1;
    }

    @XmlTransient
    public List<KnownUnions> getKnownUnionsList() {
        return knownUnionsList;
    }

    public void setKnownUnionsList(List<KnownUnions> knownUnionsList) {
        this.knownUnionsList = knownUnionsList;
    }

    @XmlTransient
    public List<KnownUnions> getKnownUnionsList1() {
        return knownUnionsList1;
    }

    public void setKnownUnionsList1(List<KnownUnions> knownUnionsList1) {
        this.knownUnionsList1 = knownUnionsList1;
    }

    @XmlTransient
    public List<KnownUnions> getKnownUnionsList2() {
        return knownUnionsList2;
    }

    public void setKnownUnionsList2(List<KnownUnions> knownUnionsList2) {
        this.knownUnionsList2 = knownUnionsList2;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    @XmlTransient
    public List<AdminMotherTree> getAdminMotherTreeList() {
        return adminMotherTreeList;
    }

    public void setAdminMotherTreeList(List<AdminMotherTree> adminMotherTreeList) {
        this.adminMotherTreeList = adminMotherTreeList;
    }

    @XmlTransient
    public List<AdminMotherTree> getAdminMotherTreeList1() {
        return adminMotherTreeList1;
    }

    public void setAdminMotherTreeList1(List<AdminMotherTree> adminMotherTreeList1) {
        this.adminMotherTreeList1 = adminMotherTreeList1;
    }
    @XmlTransient
    public List<EndofLife> getEndofLifeList() {
        return endofLifeList;
    }
    public void setEndofLifeList(List<EndofLife> endofLifeList) {
        this.endofLifeList = endofLifeList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public byte[] getPresentationAudio() {
        return presentationAudio;
    }

    public void setPresentationAudio(byte[] presentationAudio) {
        this.presentationAudio = presentationAudio;
    }

    public byte[] getPresentationVideo() {
        return presentationVideo;
    }

    public void setPresentationVideo(byte[] presentationVideo) {
        this.presentationVideo = presentationVideo;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

}
