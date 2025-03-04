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
@Table(name = "adminmothertree")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdminMotherTree.findAll", query = "SELECT a FROM AdminMotherTree a"),
    @NamedQuery(name = "AdminMotherTree.findByAdminID", query = "SELECT a FROM AdminMotherTree a WHERE a.adminMotherTreePK.adminID = :adminID"),
    @NamedQuery(name = "AdminMotherTree.findByFamilyTreeID", query = "SELECT a FROM AdminMotherTree a WHERE a.adminMotherTreePK.familyTreeID = :familyTreeID"),
    @NamedQuery(name = "AdminMotherTree.findByStartDate", query = "SELECT a FROM AdminMotherTree a WHERE a.startDate = :startDate"),
    @NamedQuery(name = "AdminMotherTree.findByEndDate", query = "SELECT a FROM AdminMotherTree a WHERE a.endDate = :endDate"),
    @NamedQuery(name = "AdminMotherTree.findByIsValid", query = "SELECT a FROM AdminMotherTree a WHERE a.isValid = :isValid"),
    @NamedQuery(name = "AdminMotherTree.findByCreatedDate", query = "SELECT a FROM AdminMotherTree a WHERE a.createdDate = :createdDate"),
    @NamedQuery(name = "AdminMotherTree.findByModifiedDate", query = "SELECT a FROM AdminMotherTree a WHERE a.modifiedDate = :modifiedDate")})
public class AdminMotherTree implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AdminMotherTreePK adminMotherTreePK;
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

    public AdminMotherTree() {
    }

    public AdminMotherTree(AdminMotherTreePK adminMotherTreePK) {
        this.adminMotherTreePK = adminMotherTreePK;
    }

    public AdminMotherTree(String adminID, String familyTreeID) {
        this.adminMotherTreePK = new AdminMotherTreePK(adminID, familyTreeID);
    }

    public AdminMotherTreePK getAdminMotherTreePK() {
        return adminMotherTreePK;
    }

    public void setAdminMotherTreePK(AdminMotherTreePK adminMotherTreePK) {
        this.adminMotherTreePK = adminMotherTreePK;
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
        hash += (adminMotherTreePK != null ? adminMotherTreePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminMotherTree)) {
            return false;
        }
        AdminMotherTree other = (AdminMotherTree) object;
        if ((this.adminMotherTreePK == null && other.adminMotherTreePK != null) || (this.adminMotherTreePK != null && !this.adminMotherTreePK.equals(other.adminMotherTreePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.AdminMotherTree[ adminMotherTreePK=" + adminMotherTreePK + " ]";
    }
    
}
