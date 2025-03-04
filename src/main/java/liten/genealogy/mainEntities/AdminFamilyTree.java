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
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
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
@Table(name = "adminfamilytree")
@NamedQueries({
    @NamedQuery(name = "AdminFamilyTree.findAll", query = "SELECT a FROM AdminFamilyTree a"),
    @NamedQuery(name = "AdminFamilyTree.findByAdminID", query = "SELECT a FROM AdminFamilyTree a WHERE a.adminID = :adminID"),
    @NamedQuery(name = "AdminFamilyTree.findByStartDate", query = "SELECT a FROM AdminFamilyTree a WHERE a.startDate = :startDate"),
    @NamedQuery(name = "AdminFamilyTree.findByEndDate", query = "SELECT a FROM AdminFamilyTree a WHERE a.endDate = :endDate"),
    @NamedQuery(name = "AdminFamilyTree.findByIsValid", query = "SELECT a FROM AdminFamilyTree a WHERE a.isValid = :isValid"),
    @NamedQuery(name = "AdminFamilyTree.findByUpdateBy", query = "SELECT a FROM AdminFamilyTree a WHERE a.updateBy = :updateBy"),
    @NamedQuery(name = "AdminFamilyTree.findByValidatedDate", query = "SELECT a FROM AdminFamilyTree a WHERE a.validatedDate = :validatedDate"),
    @NamedQuery(name = "AdminFamilyTree.findByCreatedDate", query = "SELECT a FROM AdminFamilyTree a WHERE a.createdDate = :createdDate"),
    @NamedQuery(name = "AdminFamilyTree.findByModifiedDate", query = "SELECT a FROM AdminFamilyTree a WHERE a.modifiedDate = :modifiedDate")})
@Named
@RequestScoped
public class AdminFamilyTree implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "startDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isValid")
    private boolean isValid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "updateBy")
    private String updateBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "validatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validatedDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
   
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "adminID")
    private String adminID;
    @Column(name = "endDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Column(name = "modifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @JoinColumn(name = "personID", referencedColumnName = "personID")
    @ManyToOne
    private Person person;
    @JoinColumn(name = "familyTreeID", referencedColumnName = "familyTreeID")
    @ManyToOne(optional = false)
    private FamilyTree familyTree;

    public AdminFamilyTree() {
    }

    public AdminFamilyTree(String adminID) {
        this.adminID = adminID;
    }

    public AdminFamilyTree(String adminID, Date startDate, boolean isValid, String updateBy, Date validatedDate, Date createdDate) {
        this.adminID = adminID;
        this.startDate = startDate;
        this.isValid = isValid;
        this.updateBy = updateBy;
        this.validatedDate = validatedDate;
        this.createdDate = createdDate;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }


    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }    

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public FamilyTree getFamilyTree() {
        return familyTree;
    }

    public void setFamilyTree(FamilyTree familyTree) {
        this.familyTree = familyTree;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adminID != null ? adminID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminFamilyTree)) {
            return false;
        }
        AdminFamilyTree other = (AdminFamilyTree) object;
        if ((this.adminID == null && other.adminID != null) || (this.adminID != null && !this.adminID.equals(other.adminID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.AdminFamilyTree[ adminID=" + adminID + " ]";
    }    

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
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
}
