/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.mainEntities;

import java.io.Serializable;
import java.util.Date;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author bayomock.a
 */
@Entity
@Table(name = "persondiploma")
@NamedQueries({
    @NamedQuery(name = "PersonDiploma.findAll", query = "SELECT p FROM PersonDiploma p"),
    @NamedQuery(name = "PersonDiploma.findByPersonID", query = "SELECT p FROM PersonDiploma p WHERE p.personID = :personID"),
    @NamedQuery(name = "PersonDiploma.findByHighestDiploma", query = "SELECT p FROM PersonDiploma p WHERE p.highestDiploma = :highestDiploma"),
    @NamedQuery(name = "PersonDiploma.findByObtentionDate", query = "SELECT p FROM PersonDiploma p WHERE p.obtentionDate = :obtentionDate"),
    @NamedQuery(name = "PersonDiploma.findByCreatedDate", query = "SELECT p FROM PersonDiploma p WHERE p.createdDate = :createdDate"),
    @NamedQuery(name = "PersonDiploma.findByModifiedDate", query = "SELECT p FROM PersonDiploma p WHERE p.modifiedDate = :modifiedDate")})
@Named
@RequestScoped
public class PersonDiploma implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "highestDiploma")
    private String highestDiploma;
    @Basic(optional = false)
    @NotNull
    @Column(name = "obtentionDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date obtentionDate;
    @Lob
    @Column(name = "diplomaPicture")
    private byte[] diplomaPicture;
    @Basic(optional = false)
    @NotNull
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "modifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "personID")
    private String personID;
    @JoinColumn(name = "personID", referencedColumnName = "personID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Person person;

    public PersonDiploma() {
    }

    public PersonDiploma(String personID) {
        this.personID = personID;
    }

    public PersonDiploma(String personID, String highestDiploma, Date obtentionDate, Date createdDate, Date modifiedDate) {
        this.personID = personID;
        this.highestDiploma = highestDiploma;
        this.obtentionDate = obtentionDate;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
        if (!(object instanceof PersonDiploma)) {
            return false;
        }
        PersonDiploma other = (PersonDiploma) object;
        if ((this.personID == null && other.personID != null) || (this.personID != null && !this.personID.equals(other.personID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.PersonDiploma[ personID=" + personID + " ]";
    }

    public String getHighestDiploma() {
        return highestDiploma;
    }

    public void setHighestDiploma(String highestDiploma) {
        this.highestDiploma = highestDiploma;
    }

    public Date getObtentionDate() {
        return obtentionDate;
    }

    public void setObtentionDate(Date obtentionDate) {
        this.obtentionDate = obtentionDate;
    }

    public byte[] getDiplomaPicture() {
        return diplomaPicture;
    }

    public void setDiplomaPicture(byte[] diplomaPicture) {
        this.diplomaPicture = diplomaPicture;
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
    
}
