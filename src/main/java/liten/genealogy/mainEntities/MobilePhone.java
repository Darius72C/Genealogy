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
@Table(name = "mobilephone")
@NamedQueries({
    @NamedQuery(name = "MobilePhone.findAll", query = "SELECT m FROM MobilePhone m"),
    @NamedQuery(name = "MobilePhone.findByMobilePhoneID", query = "SELECT m FROM MobilePhone m WHERE m.mobilePhoneID = :mobilePhoneID"),
    @NamedQuery(name = "MobilePhone.findByMobileNbr", query = "SELECT m FROM MobilePhone m WHERE m.mobileNbr = :mobileNbr"),
    @NamedQuery(name = "MobilePhone.findByCreatedDate", query = "SELECT m FROM MobilePhone m WHERE m.createdDate = :createdDate"),
    @NamedQuery(name = "MobilePhone.findByModifiedDate", query = "SELECT m FROM MobilePhone m WHERE m.modifiedDate = :modifiedDate")})
@Named
@RequestScoped
public class MobilePhone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "mobilePhoneID")
    private String mobilePhoneID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "mobileNbr")
    private String mobileNbr;
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "modifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @JoinColumn(name = "clientID", referencedColumnName = "clientID")
    @ManyToOne(optional = false)
    private Client client;
    @JoinColumn(name = "mobileOperatorID", referencedColumnName = "mobileOperatorID")
    @ManyToOne(optional = false)
    private MobileOperator mobileOperator;

    public MobilePhone() {
    }

    public MobilePhone(String mobilePhoneID) {
        this.mobilePhoneID = mobilePhoneID;
    }

    public MobilePhone(String mobilePhoneID, String mobileNbr) {
        this.mobilePhoneID = mobilePhoneID;
        this.mobileNbr = mobileNbr;
    }

    public String getMobilePhoneID() {
        return mobilePhoneID;
    }

    public void setMobilePhoneID(String mobilePhoneID) {
        this.mobilePhoneID = mobilePhoneID;
    }

    public String getMobileNbr() {
        return mobileNbr;
    }

    public void setMobileNbr(String mobileNbr) {
        this.mobileNbr = mobileNbr;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public MobileOperator getMobileOperator() {
        return mobileOperator;
    }

    public void setMobileOperator(MobileOperator mobileOperator) {
        this.mobileOperator = mobileOperator;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mobilePhoneID != null ? mobilePhoneID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MobilePhone)) {
            return false;
        }
        MobilePhone other = (MobilePhone) object;
        if ((this.mobilePhoneID == null && other.mobilePhoneID != null) || (this.mobilePhoneID != null && !this.mobilePhoneID.equals(other.mobilePhoneID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.MobilePhone[ mobilePhoneID=" + mobilePhoneID + " ]";
    }
    
}
