/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.mainEntities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import jakarta.persistence.ManyToOne;
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
@Table(name = "endoflife")
@NamedQueries({
    @NamedQuery(name = "EndofLife.findAll", query = "SELECT e FROM EndofLife e"),
    @NamedQuery(name = "EndofLife.findByPersonID", query = "SELECT e FROM EndofLife e WHERE e.personID = :personID"),
    @NamedQuery(name = "EndofLife.findBySummary", query = "SELECT e FROM EndofLife e WHERE e.summary = :summary"),
    @NamedQuery(name = "EndofLife.findByDeathDate", query = "SELECT e FROM EndofLife e WHERE e.deathDate = :deathDate"),
    @NamedQuery(name = "EndofLife.findByInhumationDate", query = "SELECT e FROM EndofLife e WHERE e.inhumationDate = :inhumationDate"),
    @NamedQuery(name = "EndofLife.findByUpdatedBy", query = "SELECT e FROM EndofLife e WHERE e.updatedBy = :updatedBy"),
    @NamedQuery(name = "EndofLife.findByCreatedDate", query = "SELECT e FROM EndofLife e WHERE e.createdDate = :createdDate"),
    @NamedQuery(name = "EndofLife.findByModifiedDate", query = "SELECT e FROM EndofLife e WHERE e.modifiedDate = :modifiedDate")})
@Named
@RequestScoped
public class EndofLife implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "summary")
    private String summary;
    @Basic(optional = false)
    @NotNull()
    @Column(name = "deathDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deathDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inhumationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inhumationDate;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "testimonialVideo")
    private byte[] testimonialVideo;
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
    @JoinColumn(name = "updatedBy", referencedColumnName = "personID")
    @ManyToOne(optional = false)
    @JsonManagedReference
    private Person updatedBy;
    
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

    public EndofLife() {
    }

    public EndofLife(String personID) {
        this.personID = personID;
    }

    public EndofLife(String personID, String summary, Date deathDate, Date inhumationDate, byte[] testimonialVideo, Person updatedBy, Date createdDate, Date modifiedDate) {
        this.personID = personID;
        this.summary = summary;
        this.deathDate = deathDate;
        this.inhumationDate = inhumationDate;
        this.testimonialVideo = testimonialVideo;
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
        if (!(object instanceof EndofLife)) {
            return false;
        }
        EndofLife other = (EndofLife) object;
        if ((this.personID == null && other.personID != null) || (this.personID != null && !this.personID.equals(other.personID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.EndofLife[ personID=" + personID + " ]";
    }


    public byte[] getTestimonialVideo() {
        return testimonialVideo;
    }

    public void setTestimonialVideo(byte[] testimonialVideo) {
        this.testimonialVideo = testimonialVideo;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public Date getInhumationDate() {
        return inhumationDate;
    }

    public void setInhumationDate(Date inhumationDate) {
        this.inhumationDate = inhumationDate;
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

    public Person getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Person updatedBy) {
        this.updatedBy = updatedBy;
    }
    
}
