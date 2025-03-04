/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.mainEntities;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "enrollment")
@NamedQueries({
    @NamedQuery(name = "Enrollment.findAll", query = "SELECT e FROM Enrollment e"),
    @NamedQuery(name = "Enrollment.findByEnrollmentID", query = "SELECT e FROM Enrollment e WHERE e.enrollmentID = :enrollmentID"),
    @NamedQuery(name = "Enrollment.findByStatus", query = "SELECT e FROM Enrollment e WHERE e.status = :status"),
    @NamedQuery(name = "Enrollment.findByStartDate", query = "SELECT e FROM Enrollment e WHERE e.startDate = :startDate"),
    @NamedQuery(name = "Enrollment.findByEndDate", query = "SELECT e FROM Enrollment e WHERE e.endDate = :endDate"),
    @NamedQuery(name = "Enrollment.findByIsValid", query = "SELECT e FROM Enrollment e WHERE e.isValid = :isValid"),
    @NamedQuery(name = "Enrollment.findByPrice", query = "SELECT e FROM Enrollment e WHERE e.price = :price"),
    @NamedQuery(name = "Enrollment.findByDiscount", query = "SELECT e FROM Enrollment e WHERE e.discount = :discount"),
    @NamedQuery(name = "Enrollment.findByCost", query = "SELECT e FROM Enrollment e WHERE e.cost = :cost")})
public class Enrollment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "enrollmentID")
    private String enrollmentID;
    @Size(max = 20)
    @Column(name = "status")
    private String status;
    @Column(name = "startDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "endDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Column(name = "isValid")
    private Boolean isValid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @Column(name = "discount")
    private Double discount;
    @Column(name = "cost")
    private Double cost;
    @JoinColumn(name = "clientID", referencedColumnName = "clientID")
    @ManyToOne(optional = false)
    private Client client;
    @JoinColumn(name = "licenseTypeID", referencedColumnName = "licenseTypeID")
    @ManyToOne(optional = false)
    private LicenseType licenseType;
    @JoinColumn(name = "paymentID", referencedColumnName = "paymentID")
    @ManyToOne
    private Payment payment;

    public Enrollment() {
    }

    public Enrollment(String enrollmentID) {
        this.enrollmentID = enrollmentID;
    }

    public String getEnrollmentID() {
        return enrollmentID;
    }

    public void setEnrollmentID(String enrollmentID) {
        this.enrollmentID = enrollmentID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LicenseType getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(LicenseType licenseType) {
        this.licenseType = licenseType;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (enrollmentID != null ? enrollmentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enrollment)) {
            return false;
        }
        Enrollment other = (Enrollment) object;
        if ((this.enrollmentID == null && other.enrollmentID != null) || (this.enrollmentID != null && !this.enrollmentID.equals(other.enrollmentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.Enrollment[ enrollmentID=" + enrollmentID + " ]";
    }
    
}
