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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bayomock.a
 */
@Entity
@Table(name = "familytree")
@NamedQueries({
    @NamedQuery(name = "FamilyTree.findAll", query = "SELECT f FROM FamilyTree f"),
    @NamedQuery(name = "FamilyTree.findByFamilyTreeID", query = "SELECT f FROM FamilyTree f WHERE f.familyTreeID = :familyTreeID"),
    @NamedQuery(name = "FamilyTree.findByRootPersonID", query = "SELECT f FROM FamilyTree f WHERE f.rootPersonID = :rootPersonID"),
    @NamedQuery(name = "FamilyTree.findByEtnicGroupID", query = "SELECT f FROM FamilyTree f WHERE f.etnicGroupID = :etnicGroupID"),
    @NamedQuery(name = "FamilyTree.findByName", query = "SELECT f FROM FamilyTree f WHERE f.name = :name"),
    @NamedQuery(name = "FamilyTree.findByDescription", query = "SELECT f FROM FamilyTree f WHERE f.description = :description"),
    @NamedQuery(name = "FamilyTree.findByUpdatedBy", query = "SELECT f FROM FamilyTree f WHERE f.updatedBy = :updatedBy"),
    @NamedQuery(name = "FamilyTree.findByCreatedDate", query = "SELECT f FROM FamilyTree f WHERE f.createdDate = :createdDate"),
    @NamedQuery(name = "FamilyTree.findByModifiedDate", query = "SELECT f FROM FamilyTree f WHERE f.modifiedDate = :modifiedDate")})
@Named
@RequestScoped
public class FamilyTree implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "rootPersonID")
    private String rootPersonID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "etnicGroupID")
    private String etnicGroupID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "familyTree")
    private List<AdminFatherTree> adminFatherTreeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "familyTree")
    private List<FatherFamilyTree> fatherFamilyTreeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "familyTree")
    private List<MotherFamilyTree> motherFamilyTreeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "familyTree")
    private List<AdminMotherTree> adminMotherTreeList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "familyTreeID")
    private String familyTreeID;
    @Column(name = "modifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "familyTree")
    private List<AdminFamilyTree> adminFamilyTreeList;

    public FamilyTree() {
    }

    public FamilyTree(String familyTreeID) {
        this.familyTreeID = familyTreeID;
    }

    public FamilyTree(String familyTreeID, String rootPersonID, String etnicGroupID, String name, String description, String updatedBy, Date createdDate) {
        this.familyTreeID = familyTreeID;
        this.rootPersonID = rootPersonID;
        this.etnicGroupID = etnicGroupID;
        this.name = name;
        this.description = description;
        this.updatedBy = updatedBy;
        this.createdDate = createdDate;
    }

    public String getFamilyTreeID() {
        return familyTreeID;
    }

    public void setFamilyTreeID(String familyTreeID) {
        this.familyTreeID = familyTreeID;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }


    public List<AdminFamilyTree> getAdminFamilyTreeList() {
        return adminFamilyTreeList;
    }

    public void setAdminFamilyTreeList(List<AdminFamilyTree> adminFamilyTreeList) {
        this.adminFamilyTreeList = adminFamilyTreeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (familyTreeID != null ? familyTreeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FamilyTree)) {
            return false;
        }
        FamilyTree other = (FamilyTree) object;
        if ((this.familyTreeID == null && other.familyTreeID != null) || (this.familyTreeID != null && !this.familyTreeID.equals(other.familyTreeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.FamilyTree[ familyTreeID=" + familyTreeID + " ]";
    }

    public String getRootPersonID() {
        return rootPersonID;
    }

    public void setRootPersonID(String rootPersonID) {
        this.rootPersonID = rootPersonID;
    }

    public String getEtnicGroupID() {
        return etnicGroupID;
    }

    public void setEtnicGroupID(String etnicGroupID) {
        this.etnicGroupID = etnicGroupID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @XmlTransient
    public List<AdminFatherTree> getAdminFatherTreeList() {
        return adminFatherTreeList;
    }

    public void setAdminFatherTreeList(List<AdminFatherTree> adminFatherTreeList) {
        this.adminFatherTreeList = adminFatherTreeList;
    }

    @XmlTransient
    public List<FatherFamilyTree> getFatherFamilyTreeList() {
        return fatherFamilyTreeList;
    }

    public void setFatherFamilyTreeList(List<FatherFamilyTree> fatherFamilyTreeList) {
        this.fatherFamilyTreeList = fatherFamilyTreeList;
    }

    @XmlTransient
    public List<MotherFamilyTree> getMotherFamilyTreeList() {
        return motherFamilyTreeList;
    }

    public void setMotherFamilyTreeList(List<MotherFamilyTree> motherFamilyTreeList) {
        this.motherFamilyTreeList = motherFamilyTreeList;
    }

    @XmlTransient
    public List<AdminMotherTree> getAdminMotherTreeList() {
        return adminMotherTreeList;
    }

    public void setAdminMotherTreeList(List<AdminMotherTree> adminMotherTreeList) {
        this.adminMotherTreeList = adminMotherTreeList;
    }

}
