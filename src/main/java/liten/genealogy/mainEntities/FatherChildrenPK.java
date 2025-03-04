/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.mainEntities;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author bayomock.a
 */
@Embeddable
public class FatherChildrenPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "fatherID")
    private String fatherID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "childID")
    private String childID;

    public FatherChildrenPK() {
    }

    public FatherChildrenPK(String fatherID, String childID) {
        this.fatherID = fatherID;
        this.childID = childID;
    }

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public String getChildID() {
        return childID;
    }

    public void setChildID(String childID) {
        this.childID = childID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fatherID != null ? fatherID.hashCode() : 0);
        hash += (childID != null ? childID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FatherChildrenPK)) {
            return false;
        }
        FatherChildrenPK other = (FatherChildrenPK) object;
        if ((this.fatherID == null && other.fatherID != null) || (this.fatherID != null && !this.fatherID.equals(other.fatherID))) {
            return false;
        }
        if ((this.childID == null && other.childID != null) || (this.childID != null && !this.childID.equals(other.childID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.FatherChildrenPK[ fatherID=" + fatherID + ", childID=" + childID + " ]";
    }
    
}
