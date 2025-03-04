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
public class MotherChildrenPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "motherID")
    private String motherID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "childID")
    private String childID;

    public MotherChildrenPK() {
    }

    public MotherChildrenPK(String motherID, String childID) {
        this.motherID = motherID;
        this.childID = childID;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
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
        hash += (motherID != null ? motherID.hashCode() : 0);
        hash += (childID != null ? childID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MotherChildrenPK)) {
            return false;
        }
        MotherChildrenPK other = (MotherChildrenPK) object;
        if ((this.motherID == null && other.motherID != null) || (this.motherID != null && !this.motherID.equals(other.motherID))) {
            return false;
        }
        if ((this.childID == null && other.childID != null) || (this.childID != null && !this.childID.equals(other.childID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.MotherChildrenPK[ motherID=" + motherID + ", childID=" + childID + " ]";
    }
    
}
