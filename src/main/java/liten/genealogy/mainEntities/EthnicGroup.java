/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.mainEntities;

import java.io.Serializable;
import java.util.Date;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
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
@Table(name = "ethnicgroup")
@NamedQueries({
    @NamedQuery(name = "EthnicGroup.findAll", query = "SELECT e FROM EthnicGroup e"),
    @NamedQuery(name = "EthnicGroup.findByEthnicGroupID", query = "SELECT e FROM EthnicGroup e WHERE e.ethnicGroupID = :ethnicGroupID"),
    @NamedQuery(name = "EthnicGroup.findByName", query = "SELECT e FROM EthnicGroup e WHERE e.name = :name"),
    @NamedQuery(name = "EthnicGroup.findByDescription", query = "SELECT e FROM EthnicGroup e WHERE e.description = :description"),
    @NamedQuery(name = "EthnicGroup.findByCreatedDate", query = "SELECT e FROM EthnicGroup e WHERE e.createdDate = :createdDate"),
    @NamedQuery(name = "EthnicGroup.findByModifiedDate", query = "SELECT e FROM EthnicGroup e WHERE e.modifiedDate = :modifiedDate")})
@Named
@RequestScoped
public class EthnicGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "ethnicGroupID")
    private String ethnicGroupID;
        @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "name")
    private String name;
    @Size(max = 500)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "countryID", referencedColumnName = "countryID")
    @ManyToOne(optional = false)
    private Country country;
    @Column(name = "modifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    public EthnicGroup() {
    }

    public EthnicGroup(String ethnicGroupID) {
        this.ethnicGroupID = ethnicGroupID;
    }

    public EthnicGroup(String ethnicGroupID, String name, Date createdDate) {
        this.ethnicGroupID = ethnicGroupID;
        this.name = name;
        this.createdDate = createdDate;
    }

    public String getEthnicGroupID() {
        return ethnicGroupID;
    }

    public void setEthnicGroupID(String ethnicGroupID) {
        this.ethnicGroupID = ethnicGroupID;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ethnicGroupID != null ? ethnicGroupID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EthnicGroup)) {
            return false;
        }
        EthnicGroup other = (EthnicGroup) object;
        if ((this.ethnicGroupID == null && other.ethnicGroupID != null) || (this.ethnicGroupID != null && !this.ethnicGroupID.equals(other.ethnicGroupID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.EthnicGroup[ ethnicGroupID=" + ethnicGroupID + " ]";
    }
}
