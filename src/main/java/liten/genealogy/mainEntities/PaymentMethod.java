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
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "paymentmethod")
@NamedQueries({
    @NamedQuery(name = "PaymentMethod.findAll", query = "SELECT p FROM PaymentMethod p"),
    @NamedQuery(name = "PaymentMethod.findByPaymentMethodID", query = "SELECT p FROM PaymentMethod p WHERE p.paymentMethodID = :paymentMethodID"),
    @NamedQuery(name = "PaymentMethod.findByName", query = "SELECT p FROM PaymentMethod p WHERE p.name = :name"),
    @NamedQuery(name = "PaymentMethod.findByDescription", query = "SELECT p FROM PaymentMethod p WHERE p.description = :description"),
    @NamedQuery(name = "PaymentMethod.findByCreatedDate", query = "SELECT p FROM PaymentMethod p WHERE p.createdDate = :createdDate"),
    @NamedQuery(name = "PaymentMethod.findByModifiedDate", query = "SELECT p FROM PaymentMethod p WHERE p.modifiedDate = :modifiedDate")})
@Named
@RequestScoped
public class PaymentMethod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "paymentMethodID")
    private String paymentMethodID;
    @Size(max = 80)
    @Column(name = "name")
    private String name;
    @Size(max = 500)
    @Column(name = "description")
    private String description;
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "modifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @OneToMany(mappedBy = "paymentMethod")
    private List<Payment> paymentList;

    public PaymentMethod() {
    }

    public PaymentMethod(String paymentMethodID) {
        this.paymentMethodID = paymentMethodID;
    }

    public String getPaymentMethodID() {
        return paymentMethodID;
    }

    public void setPaymentMethodID(String paymentMethodID) {
        this.paymentMethodID = paymentMethodID;
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

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentMethodID != null ? paymentMethodID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentMethod)) {
            return false;
        }
        PaymentMethod other = (PaymentMethod) object;
        if ((this.paymentMethodID == null && other.paymentMethodID != null) || (this.paymentMethodID != null && !this.paymentMethodID.equals(other.paymentMethodID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.PaymentMethod[ paymentMethodID=" + paymentMethodID + " ]";
    }
    
}
