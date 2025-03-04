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
@Table(name = "adminfathertree")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdminFatherTree.findAll", query = "SELECT a FROM AdminFatherTree a"),
    @NamedQuery(name = "AdminFatherTree.findByAdminID", query = "SELECT a FROM AdminFatherTree a WHERE a.adminFatherTreePK.adminID = :adminID"),
    @NamedQuery(name = "AdminFatherTree.findByFamilyTreeID", query = "SELECT a FROM AdminFatherTree a WHERE a.adminFatherTreePK.familyTreeID = :familyTreeID"),
    @NamedQuery(name = "AdminFatherTree.findByStartDate", query = "SELECT a FROM AdminFatherTree a WHERE a.startDate = :startDate"),
    @NamedQuery(name = "AdminFatherTree.findByEndDate", query = "SELECT a FROM AdminFatherTree a WHERE a.endDate = :endDate"),
    @NamedQuery(name = "AdminFatherTree.findByIsValid", query = "SELECT a FROM AdminFatherTree a WHERE a.isValid = :isValid"),
    @NamedQuery(name = "AdminFatherTree.findByCreatedDate", query = "SELECT a FROM AdminFatherTree a WHERE a.createdDate = :createdDate"),
    @NamedQuery(name = "AdminFatherTree.findByModifiedDate", query = "SELECT a FROM AdminFatherTree a WHERE a.modifiedDate = :modifiedDate")})
public class AdminFatherTree implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AdminFatherTreePK adminFatherTreePK;
    @Column(name = "startDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "endDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Column(name = "isValid")
    private Boolean isValid;
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
    @ManyToOne
    private Person updateBy;
    @JoinColumn(name = "adminID", referencedColumnName = "personID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Person admin;

    public AdminFatherTree() {
    }

    public AdminFatherTree(AdminFatherTreePK adminFatherTreePK) {
        this.adminFatherTreePK = adminFatherTreePK;
    }

    public AdminFatherTree(String adminID, String familyTreeID) {
        this.adminFatherTreePK = new AdminFatherTreePK(adminID, familyTreeID);
    }

    public AdminFatherTreePK getAdminFatherTreePK() {
        return adminFatherTreePK;
    }

    public void setAdminFatherTreePK(AdminFatherTreePK adminFatherTreePK) {
        this.adminFatherTreePK = adminFatherTreePK;
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

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
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

    public Person getAdmin() {
        return admin;
    }

    public void setAdmin(Person admin) {
        this.admin = admin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adminFatherTreePK != null ? adminFatherTreePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminFatherTree)) {
            return false;
        }
        AdminFatherTree other = (AdminFatherTree) object;
        if ((this.adminFatherTreePK == null && other.adminFatherTreePK != null) || (this.adminFatherTreePK != null && !this.adminFatherTreePK.equals(other.adminFatherTreePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.AdminFatherTree[ adminFatherTreePK=" + adminFatherTreePK + " ]";
    }
    
}
