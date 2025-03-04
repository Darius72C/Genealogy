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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
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
@Table(name = "mobileoperator")
@NamedQueries({
    @NamedQuery(name = "MobileOperator.findAll", query = "SELECT m FROM MobileOperator m"),
    @NamedQuery(name = "MobileOperator.findByMobileOperatorID", query = "SELECT m FROM MobileOperator m WHERE m.mobileOperatorID = :mobileOperatorID"),
    @NamedQuery(name = "MobileOperator.findByName", query = "SELECT m FROM MobileOperator m WHERE m.name = :name"),
    @NamedQuery(name = "MobileOperator.findByDescription", query = "SELECT m FROM MobileOperator m WHERE m.description = :description"),
    @NamedQuery(name = "MobileOperator.findByCreatedDate", query = "SELECT m FROM MobileOperator m WHERE m.createdDate = :createdDate"),
    @NamedQuery(name = "MobileOperator.findByModifiedDate", query = "SELECT m FROM MobileOperator m WHERE m.modifiedDate = :modifiedDate")})
@Named
@RequestScoped
public class MobileOperator implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "mobileOperatorID")
    private String mobileOperatorID;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Size(max = 150)
    @Column(name = "description")
    private String description;
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "modifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @JoinColumn(name = "countryID", referencedColumnName = "countryID")
    @ManyToOne
    private Country country;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mobileOperator")
    private List<MobilePhone> mobilePhoneList;

    public MobileOperator() {
    }

    public MobileOperator(String mobileOperatorID) {
        this.mobileOperatorID = mobileOperatorID;
    }

    public String getMobileOperatorID() {
        return mobileOperatorID;
    }

    public void setMobileOperatorID(String mobileOperatorID) {
        this.mobileOperatorID = mobileOperatorID;
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

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<MobilePhone> getMobilePhoneList() {
        return mobilePhoneList;
    }

    public void setMobilePhoneList(List<MobilePhone> mobilePhoneList) {
        this.mobilePhoneList = mobilePhoneList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mobileOperatorID != null ? mobileOperatorID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MobileOperator)) {
            return false;
        }
        MobileOperator other = (MobileOperator) object;
        if ((this.mobileOperatorID == null && other.mobileOperatorID != null) || (this.mobileOperatorID != null && !this.mobileOperatorID.equals(other.mobileOperatorID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.MobileOperator[ mobileOperatorID=" + mobileOperatorID + " ]";
    }
    
}
