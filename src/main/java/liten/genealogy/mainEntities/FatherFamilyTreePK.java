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
public class FatherFamilyTreePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "personID")
    private String personID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "familyTreeID")
    private String familyTreeID;

    public FatherFamilyTreePK() {
    }

    public FatherFamilyTreePK(String personID, String familyTreeID) {
        this.personID = personID;
        this.familyTreeID = familyTreeID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
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
        hash += (personID != null ? personID.hashCode() : 0);
        hash += (familyTreeID != null ? familyTreeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FatherFamilyTreePK)) {
            return false;
        }
        FatherFamilyTreePK other = (FatherFamilyTreePK) object;
        if ((this.personID == null && other.personID != null) || (this.personID != null && !this.personID.equals(other.personID))) {
            return false;
        }
        if ((this.familyTreeID == null && other.familyTreeID != null) || (this.familyTreeID != null && !this.familyTreeID.equals(other.familyTreeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.FatherFamilyTreePK[ personID=" + personID + ", familyTreeID=" + familyTreeID + " ]";
    }
    
}
