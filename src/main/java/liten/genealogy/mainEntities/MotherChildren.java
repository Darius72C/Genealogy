/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.mainEntities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author bayomock.a
 */
@Entity
@Table(name = "motherchildren")
@NamedQueries({
    @NamedQuery(name = "MotherChildren.findAll", query = "SELECT m FROM MotherChildren m"),
    @NamedQuery(name = "MotherChildren.findByMotherID", query = "SELECT m FROM MotherChildren m WHERE m.motherChildrenPK.motherID = :motherID"),
    @NamedQuery(name = "MotherChildren.findByChildID", query = "SELECT m FROM MotherChildren m WHERE m.motherChildrenPK.childID = :childID"),
    @NamedQuery(name = "MotherChildren.findByCreatedDate", query = "SELECT m FROM MotherChildren m WHERE m.createdDate = :createdDate"),
    @NamedQuery(name = "MotherChildren.findByModifiedDate", query = "SELECT m FROM MotherChildren m WHERE m.modifiedDate = :modifiedDate")})
@Named
@RequestScoped
public class MotherChildren implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MotherChildrenPK motherChildrenPK;
    @Lob
    @Column(name = "childAudio")
    private byte[] childAudio;
    @Lob
    @Column(name = "childVideo")
    private byte[] childVideo;
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
    @JoinColumn(name = "childID", referencedColumnName = "personID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Person child;
    @JoinColumn(name = "motherID", referencedColumnName = "personID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Person mother;
    @JoinColumn(name = "updatedBy", referencedColumnName = "personID")
    @ManyToOne
    private Person updatedBy;

    public MotherChildren() {
    }

    public MotherChildren(MotherChildrenPK motherChildrenPK) {
        this.motherChildrenPK = motherChildrenPK;
    }

    public MotherChildren(MotherChildrenPK motherChildrenPK, Date createdDate, Date modifiedDate) {
        this.motherChildrenPK = motherChildrenPK;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public MotherChildren(String motherID, String childID) {
        this.motherChildrenPK = new MotherChildrenPK(motherID, childID);
    }

    public MotherChildrenPK getMotherChildrenPK() {
        return motherChildrenPK;
    }

    public void setMotherChildrenPK(MotherChildrenPK motherChildrenPK) {
        this.motherChildrenPK = motherChildrenPK;
    }

    public byte[] getChildAudio() {
        return childAudio;
    }

    public void setChildAudio(byte[] childAudio) {
        this.childAudio = childAudio;
    }

    public byte[] getChildVideo() {
        return childVideo;
    }

    public void setChildVideo(byte[] childVideo) {
        this.childVideo = childVideo;
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

    public Person getChild() {
        return child;
    }

    public void setChild(Person child) {
        this.child = child;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public Person getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Person updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (motherChildrenPK != null ? motherChildrenPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MotherChildren)) {
            return false;
        }
        MotherChildren other = (MotherChildren) object;
        if ((this.motherChildrenPK == null && other.motherChildrenPK != null) || (this.motherChildrenPK != null && !this.motherChildrenPK.equals(other.motherChildrenPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.MotherChildren[ motherChildrenPK=" + motherChildrenPK + " ]";
    }
    
}
