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
public class KnownUnionsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "husbandID")
    private String husbandID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "wifeID")
    private String wifeID;

    public KnownUnionsPK() {
    }

    public KnownUnionsPK(String husbandID, String wifeID) {
        this.husbandID = husbandID;
        this.wifeID = wifeID;
    }

    public String getHusbandID() {
        return husbandID;
    }

    public void setHusbandID(String husbandID) {
        this.husbandID = husbandID;
    }

    public String getWifeID() {
        return wifeID;
    }

    public void setWifeID(String wifeID) {
        this.wifeID = wifeID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (husbandID != null ? husbandID.hashCode() : 0);
        hash += (wifeID != null ? wifeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KnownUnionsPK)) {
            return false;
        }
        KnownUnionsPK other = (KnownUnionsPK) object;
        if ((this.husbandID == null && other.husbandID != null) || (this.husbandID != null && !this.husbandID.equals(other.husbandID))) {
            return false;
        }
        if ((this.wifeID == null && other.wifeID != null) || (this.wifeID != null && !this.wifeID.equals(other.wifeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.KnownUnionsPK[ husbandID=" + husbandID + ", wifeID=" + wifeID + " ]";
    }
    
}
