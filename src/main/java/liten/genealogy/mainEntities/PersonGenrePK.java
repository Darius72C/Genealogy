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
public class PersonGenrePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "personID")
    private String personID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "genreID")
    private String genreID;

    public PersonGenrePK() {
    }

    public PersonGenrePK(String personID, String genreID) {
        this.personID = personID;
        this.genreID = genreID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getGenreID() {
        return genreID;
    }

    public void setGenreID(String genreID) {
        this.genreID = genreID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personID != null ? personID.hashCode() : 0);
        hash += (genreID != null ? genreID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonGenrePK)) {
            return false;
        }
        PersonGenrePK other = (PersonGenrePK) object;
        if ((this.personID == null && other.personID != null) || (this.personID != null && !this.personID.equals(other.personID))) {
            return false;
        }
        if ((this.genreID == null && other.genreID != null) || (this.genreID != null && !this.genreID.equals(other.genreID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.PersonGenrePK[ personID=" + personID + ", genreID=" + genreID + " ]";
    }
    
}
