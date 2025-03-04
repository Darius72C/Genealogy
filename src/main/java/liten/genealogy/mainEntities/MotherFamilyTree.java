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
@Table(name = "motherfamilytree")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MotherFamilyTree.findAll", query = "SELECT m FROM MotherFamilyTree m"),
    @NamedQuery(name = "MotherFamilyTree.findByCurrentPersonID", query = "SELECT m FROM MotherFamilyTree m WHERE m.motherFamilyTreePK.currentPersonID = :currentPersonID"),
    @NamedQuery(name = "MotherFamilyTree.findByFamilyTreeID", query = "SELECT m FROM MotherFamilyTree m WHERE m.motherFamilyTreePK.familyTreeID = :familyTreeID"),
    @NamedQuery(name = "MotherFamilyTree.findByValidatedDate", query = "SELECT m FROM MotherFamilyTree m WHERE m.validatedDate = :validatedDate"),
    @NamedQuery(name = "MotherFamilyTree.findByCreatedDate", query = "SELECT m FROM MotherFamilyTree m WHERE m.createdDate = :createdDate"),
    @NamedQuery(name = "MotherFamilyTree.findByModifiedDate", query = "SELECT m FROM MotherFamilyTree m WHERE m.modifiedDate = :modifiedDate")})
public class MotherFamilyTree implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MotherFamilyTreePK motherFamilyTreePK;
    @Column(name = "validatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validatedDate;
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
    @JoinColumn(name = "currentPersonID", referencedColumnName = "personID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Person person;

    public MotherFamilyTree() {
    }

    public MotherFamilyTree(MotherFamilyTreePK motherFamilyTreePK) {
        this.motherFamilyTreePK = motherFamilyTreePK;
    }

    public MotherFamilyTree(String currentPersonID, String familyTreeID) {
        this.motherFamilyTreePK = new MotherFamilyTreePK(currentPersonID, familyTreeID);
    }

    public MotherFamilyTreePK getMotherFamilyTreePK() {
        return motherFamilyTreePK;
    }

    public void setMotherFamilyTreePK(MotherFamilyTreePK motherFamilyTreePK) {
        this.motherFamilyTreePK = motherFamilyTreePK;
    }

    public Date getValidatedDate() {
        return validatedDate;
    }

    public void setValidatedDate(Date validatedDate) {
        this.validatedDate = validatedDate;
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
        hash += (motherFamilyTreePK != null ? motherFamilyTreePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MotherFamilyTree)) {
            return false;
        }
        MotherFamilyTree other = (MotherFamilyTree) object;
        if ((this.motherFamilyTreePK == null && other.motherFamilyTreePK != null) || (this.motherFamilyTreePK != null && !this.motherFamilyTreePK.equals(other.motherFamilyTreePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.MotherFamilyTree[ motherFamilyTreePK=" + motherFamilyTreePK + " ]";
    }
    
}
