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
@Table(name = "personpicture")
@NamedQueries({
    @NamedQuery(name = "PersonPicture.findAll", query = "SELECT p FROM PersonPicture p"),
    @NamedQuery(name = "PersonPicture.findByPersonID", query = "SELECT p FROM PersonPicture p WHERE p.personID = :personID"),
    @NamedQuery(name = "PersonPicture.findByDescription", query = "SELECT p FROM PersonPicture p WHERE p.description = :description"),
    @NamedQuery(name = "PersonPicture.findByUpdatedBy", query = "SELECT p FROM PersonPicture p WHERE p.updatedBy = :updatedBy"),
    @NamedQuery(name = "PersonPicture.findByCreatedDate", query = "SELECT p FROM PersonPicture p WHERE p.createdDate = :createdDate"),
    @NamedQuery(name = "PersonPicture.findByModifiedDate", query = "SELECT p FROM PersonPicture p WHERE p.modifiedDate = :modifiedDate")})
@Named
@RequestScoped
public class PersonPicture implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "picture")
    private byte[] picture;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "description")
    private String description;
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

    public PersonPicture() {
    }

    public PersonPicture(String personID) {
        this.personID = personID;
    }

    public PersonPicture(String personID, byte[] picture, String description, String updatedBy, Date createdDate, Date modifiedDate) {
        this.personID = personID;
        this.picture = picture;
        this.description = description;
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
        if (!(object instanceof PersonPicture)) {
            return false;
        }
        PersonPicture other = (PersonPicture) object;
        if ((this.personID == null && other.personID != null) || (this.personID != null && !this.personID.equals(other.personID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.PersonPicture[ personID=" + personID + " ]";
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
