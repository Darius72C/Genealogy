/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.mainEntities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author bayomock.a
 */
@Embeddable
public class AdminFatherTreePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "adminID")
    private String adminID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "familyTreeID")
    private String familyTreeID;

    public AdminFatherTreePK() {
    }

    public AdminFatherTreePK(String adminID, String familyTreeID) {
        this.adminID = adminID;
        this.familyTreeID = familyTreeID;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getFamilyTreeID() {
        return familyTreeID;
    }

    public void setFamilyTreeID(String familyTreeID) {
        this.familyTreeID = familyTreeID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adminID != null ? adminID.hashCode() : 0);
        hash += (familyTreeID != null ? familyTreeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminFatherTreePK)) {
            return false;
        }
        AdminFatherTreePK other = (AdminFatherTreePK) object;
        if ((this.adminID == null && other.adminID != null) || (this.adminID != null && !this.adminID.equals(other.adminID))) {
            return false;
        }
        if ((this.familyTreeID == null && other.familyTreeID != null) || (this.familyTreeID != null && !this.familyTreeID.equals(other.familyTreeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.AdminFatherTreePK[ adminID=" + adminID + ", familyTreeID=" + familyTreeID + " ]";
    }
    
}
