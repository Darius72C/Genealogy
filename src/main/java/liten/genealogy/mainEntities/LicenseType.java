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
@Table(name = "licensetype")
@NamedQueries({
    @NamedQuery(name = "LicenseType.findAll", query = "SELECT l FROM LicenseType l"),
    @NamedQuery(name = "LicenseType.findByLicenseTypeID", query = "SELECT l FROM LicenseType l WHERE l.licenseTypeID = :licenseTypeID"),
    @NamedQuery(name = "LicenseType.findByName", query = "SELECT l FROM LicenseType l WHERE l.name = :name"),
    @NamedQuery(name = "LicenseType.findByDescription", query = "SELECT l FROM LicenseType l WHERE l.description = :description"),
    @NamedQuery(name = "LicenseType.findByPrice", query = "SELECT l FROM LicenseType l WHERE l.price = :price")})
@Named
@RequestScoped
public class LicenseType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "licenseTypeID")
    private String licenseTypeID;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Size(max = 500)
    @Column(name = "description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "licenseType")
    private List<Enrollment> enrollmentList;

    public LicenseType() {
    }

    public LicenseType(String licenseTypeID) {
        this.licenseTypeID = licenseTypeID;
    }

    public String getLicenseTypeID() {
        return licenseTypeID;
    }

    public void setLicenseTypeID(String licenseTypeID) {
        this.licenseTypeID = licenseTypeID;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Enrollment> getEnrollmentList() {
        return enrollmentList;
    }

    public void setEnrollmentList(List<Enrollment> enrollmentList) {
        this.enrollmentList = enrollmentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (licenseTypeID != null ? licenseTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LicenseType)) {
            return false;
        }
        LicenseType other = (LicenseType) object;
        if ((this.licenseTypeID == null && other.licenseTypeID != null) || (this.licenseTypeID != null && !this.licenseTypeID.equals(other.licenseTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.LicenseType[ licenseTypeID=" + licenseTypeID + " ]";
    }
    
}
