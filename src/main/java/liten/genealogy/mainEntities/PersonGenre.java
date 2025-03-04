/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.mainEntities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author bayomock.a
 */
@Entity
@Table(name = "persongenre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonGenre.findAll", query = "SELECT p FROM PersonGenre p"),
    @NamedQuery(name = "PersonGenre.findByPersonID", query = "SELECT p FROM PersonGenre p WHERE p.personGenrePK.personID = :personID"),
    @NamedQuery(name = "PersonGenre.findByGenreID", query = "SELECT p FROM PersonGenre p WHERE p.personGenrePK.genreID = :genreID"),
    @NamedQuery(name = "PersonGenre.findByStartDate", query = "SELECT p FROM PersonGenre p WHERE p.startDate = :startDate"),
    @NamedQuery(name = "PersonGenre.findByEndDate", query = "SELECT p FROM PersonGenre p WHERE p.endDate = :endDate"),
    @NamedQuery(name = "PersonGenre.findByCreateDate", query = "SELECT p FROM PersonGenre p WHERE p.createDate = :createDate"),
    @NamedQuery(name = "PersonGenre.findByModifiedDate", query = "SELECT p FROM PersonGenre p WHERE p.modifiedDate = :modifiedDate")})
@Named
@RequestScoped
public class PersonGenre implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonGenrePK personGenrePK;
    @Column(name = "startDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "endDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Column(name = "createDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "modifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @JoinColumn(name = "genreID", referencedColumnName = "genreID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @JsonManagedReference
    private Genre genre;
    @JoinColumn(name = "personID", referencedColumnName = "personID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @JsonManagedReference
    private Person person;

    public PersonGenre() {
    }

    public PersonGenre(PersonGenrePK personGenrePK) {
        this.personGenrePK = personGenrePK;
    }

    public PersonGenre(String personID, String genreID) {
        this.personGenrePK = new PersonGenrePK(personID, genreID);
    }

    public PersonGenrePK getPersonGenrePK() {
        return personGenrePK;
    }

    public void setPersonGenrePK(PersonGenrePK personGenrePK) {
        this.personGenrePK = personGenrePK;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personGenrePK != null ? personGenrePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonGenre)) {
            return false;
        }
        PersonGenre other = (PersonGenre) object;
        if ((this.personGenrePK == null && other.personGenrePK != null) || (this.personGenrePK != null && !this.personGenrePK.equals(other.personGenrePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.PersonGenre[ personGenrePK=" + personGenrePK + " ]";
    }
    
}
