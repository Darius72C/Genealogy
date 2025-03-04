/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.mainEntities;

import java.io.Serializable;
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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author bayomock.a
 */
@Entity
@Table(name = "country")
@NamedQueries({
    @NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c"),
    @NamedQuery(name = "Country.findByCountryID", query = "SELECT c FROM Country c WHERE c.countryID = :countryID"),
    @NamedQuery(name = "Country.findByName", query = "SELECT c FROM Country c WHERE c.name = :name"),
    @NamedQuery(name = "Country.findByDescription", query = "SELECT c FROM Country c WHERE c.description = :description")})
@Named
@RequestScoped
public class Country implements Serializable {

    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Size(max = 1500)
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "country")
    private List<MobileOperator> mobileOperatorList;

    private static final long serialVersionU = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "countryID")
    private String countryID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    private List<EthnicGroup> ethnicGroupList;

    public Country() {
    }

    public Country(String countryID) {
        this.countryID = countryID;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }


    public List<EthnicGroup> getEthnicGroupList() {
        return ethnicGroupList;
    }

    public void setEthnicGroupList(List<EthnicGroup> ethnicGroupList) {
        this.ethnicGroupList = ethnicGroupList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (countryID != null ? countryID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Country)) {
            return false;
        }
        Country other = (Country) object;
        if ((this.countryID == null && other.countryID != null) || (this.countryID != null && !this.countryID.equals(other.countryID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.Country[ countryID=" + countryID + " ]";
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

    public List<MobileOperator> getMobileOperatorList() {
        return mobileOperatorList;
    }

    public void setMobileOperatorList(List<MobileOperator> mobileOperatorList) {
        this.mobileOperatorList = mobileOperatorList;
    }
    
}
