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
@Table(name = "personresume")
@NamedQueries({
    @NamedQuery(name = "PersonResume.findAll", query = "SELECT p FROM PersonResume p"),
    @NamedQuery(name = "PersonResume.findByPersonID", query = "SELECT p FROM PersonResume p WHERE p.personID = :personID"),
    @NamedQuery(name = "PersonResume.findByShortBio", query = "SELECT p FROM PersonResume p WHERE p.shortBio = :shortBio"),
    @NamedQuery(name = "PersonResume.findByUpdatedBy", query = "SELECT p FROM PersonResume p WHERE p.updatedBy = :updatedBy"),
    @NamedQuery(name = "PersonResume.findByCreatedDate", query = "SELECT p FROM PersonResume p WHERE p.createdDate = :createdDate"),
    @NamedQuery(name = "PersonResume.findByModifiedDate", query = "SELECT p FROM PersonResume p WHERE p.modifiedDate = :modifiedDate")})
@Named
@RequestScoped
public class PersonResume implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "short_bio")
    private String shortBio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "updatedBy")
    private String updatedBy;
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

    public PersonResume() {
    }

    public PersonResume(String personID) {
        this.personID = personID;
    }

    public PersonResume(String personID, String shortBio, String updatedBy, Date createdDate, Date modifiedDate) {
        this.personID = personID;
        this.shortBio = shortBio;
        this.updatedBy = updatedBy;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getShortBio() {
        return shortBio;
    }

    public void setShortBio(String shortBio) {
        this.shortBio = shortBio;
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
        if (!(object instanceof PersonResume)) {
            return false;
        }
        PersonResume other = (PersonResume) object;
        if ((this.personID == null && other.personID != null) || (this.personID != null && !this.personID.equals(other.personID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.PersonResume[ personID=" + personID + " ]";
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
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
