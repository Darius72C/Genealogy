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
@Table(name = "fatherchildren")
@NamedQueries({
    @NamedQuery(name = "FatherChildren.findAll", query = "SELECT f FROM FatherChildren f"),
    @NamedQuery(name = "FatherChildren.findByFatherID", query = "SELECT f FROM FatherChildren f WHERE f.fatherChildrenPK.fatherID = :fatherID"),
    @NamedQuery(name = "FatherChildren.findByChildID", query = "SELECT f FROM FatherChildren f WHERE f.fatherChildrenPK.childID = :childID"),
    @NamedQuery(name = "FatherChildren.findByCreatedDate", query = "SELECT f FROM FatherChildren f WHERE f.createdDate = :createdDate"),
    @NamedQuery(name = "FatherChildren.findByModifiedDate", query = "SELECT f FROM FatherChildren f WHERE f.modifiedDate = :modifiedDate")})
@Named
@RequestScoped
public class FatherChildren implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FatherChildrenPK fatherChildrenPK;
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
    @JoinColumn(name = "fatherID", referencedColumnName = "personID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Person father;
    @JoinColumn(name = "updatedBy", referencedColumnName = "personID")
    @ManyToOne(optional = false)
    private Person updatedBy;

    public FatherChildren() {
    }

    public FatherChildren(FatherChildrenPK fatherChildrenPK) {
        this.fatherChildrenPK = fatherChildrenPK;
    }

    public FatherChildren(FatherChildrenPK fatherChildrenPK, Date createdDate, Date modifiedDate) {
        this.fatherChildrenPK = fatherChildrenPK;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public FatherChildren(String fatherID, String childID) {
        this.fatherChildrenPK = new FatherChildrenPK(fatherID, childID);
    }

    public FatherChildrenPK getFatherChildrenPK() {
        return fatherChildrenPK;
    }

    public void setFatherChildrenPK(FatherChildrenPK fatherChildrenPK) {
        this.fatherChildrenPK = fatherChildrenPK;
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

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
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
        hash += (fatherChildrenPK != null ? fatherChildrenPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FatherChildren)) {
            return false;
        }
        FatherChildren other = (FatherChildren) object;
        if ((this.fatherChildrenPK == null && other.fatherChildrenPK != null) || (this.fatherChildrenPK != null && !this.fatherChildrenPK.equals(other.fatherChildrenPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.FatherChildren[ fatherChildrenPK=" + fatherChildrenPK + " ]";
    }
    
}
