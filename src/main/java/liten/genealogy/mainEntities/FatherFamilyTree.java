/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.mainEntities;

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
@Table(name = "fatherfamilytree")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FatherFamilyTree.findAll", query = "SELECT f FROM FatherFamilyTree f"),
    @NamedQuery(name = "FatherFamilyTree.findByPersonID", query = "SELECT f FROM FatherFamilyTree f WHERE f.fatherFamilyTreePK.personID = :personID"),
    @NamedQuery(name = "FatherFamilyTree.findByFamilyTreeID", query = "SELECT f FROM FatherFamilyTree f WHERE f.fatherFamilyTreePK.familyTreeID = :familyTreeID"),
    @NamedQuery(name = "FatherFamilyTree.findByCreatedDate", query = "SELECT f FROM FatherFamilyTree f WHERE f.createdDate = :createdDate"),
    @NamedQuery(name = "FatherFamilyTree.findByModifiedDate", query = "SELECT f FROM FatherFamilyTree f WHERE f.modifiedDate = :modifiedDate")})
public class FatherFamilyTree implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FatherFamilyTreePK fatherFamilyTreePK;
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "modifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @JoinColumn(name = "familyTreeID", referencedColumnName = "familyTreeID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private FamilyTree familyTree;
    @JoinColumn(name = "updateBy", referencedColumnName = "personID")
    @ManyToOne(optional = false)
    private Person updateBy;
    @JoinColumn(name = "personID", referencedColumnName = "personID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Person person;

    public FatherFamilyTree() {
    }

    public FatherFamilyTree(FatherFamilyTreePK fatherFamilyTreePK) {
        this.fatherFamilyTreePK = fatherFamilyTreePK;
    }

    public FatherFamilyTree(String personID, String familyTreeID) {
        this.fatherFamilyTreePK = new FatherFamilyTreePK(personID, familyTreeID);
    }

    public FatherFamilyTreePK getFatherFamilyTreePK() {
        return fatherFamilyTreePK;
    }

    public void setFatherFamilyTreePK(FatherFamilyTreePK fatherFamilyTreePK) {
        this.fatherFamilyTreePK = fatherFamilyTreePK;
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

    public FamilyTree getFamilyTree() {
        return familyTree;
    }

    public void setFamilyTree(FamilyTree familyTree) {
        this.familyTree = familyTree;
    }

    public Person getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Person updateBy) {
        this.updateBy = updateBy;
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
        hash += (fatherFamilyTreePK != null ? fatherFamilyTreePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FatherFamilyTree)) {
            return false;
        }
        FatherFamilyTree other = (FatherFamilyTree) object;
        if ((this.fatherFamilyTreePK == null && other.fatherFamilyTreePK != null) || (this.fatherFamilyTreePK != null && !this.fatherFamilyTreePK.equals(other.fatherFamilyTreePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.FatherFamilyTree[ fatherFamilyTreePK=" + fatherFamilyTreePK + " ]";
    }
    
}
