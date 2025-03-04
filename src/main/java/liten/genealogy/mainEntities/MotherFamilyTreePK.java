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
public class MotherFamilyTreePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "currentPersonID")
    private String currentPersonID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "familyTreeID")
    private String familyTreeID;

    public MotherFamilyTreePK() {
    }

    public MotherFamilyTreePK(String currentPersonID, String familyTreeID) {
        this.currentPersonID = currentPersonID;
        this.familyTreeID = familyTreeID;
    }

    public String getCurrentPersonID() {
        return currentPersonID;
    }

    public void setCurrentPersonID(String currentPersonID) {
        this.currentPersonID = currentPersonID;
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
        hash += (currentPersonID != null ? currentPersonID.hashCode() : 0);
        hash += (familyTreeID != null ? familyTreeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MotherFamilyTreePK)) {
            return false;
        }
        MotherFamilyTreePK other = (MotherFamilyTreePK) object;
        if ((this.currentPersonID == null && other.currentPersonID != null) || (this.currentPersonID != null && !this.currentPersonID.equals(other.currentPersonID))) {
            return false;
        }
        if ((this.familyTreeID == null && other.familyTreeID != null) || (this.familyTreeID != null && !this.familyTreeID.equals(other.familyTreeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.MotherFamilyTreePK[ currentPersonID=" + currentPersonID + ", familyTreeID=" + familyTreeID + " ]";
    }
    
}
