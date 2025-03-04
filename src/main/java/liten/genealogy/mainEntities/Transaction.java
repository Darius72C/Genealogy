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
@Table(name = "transaction")
@NamedQueries({
    @NamedQuery(name = "Transaction.findAll", query = "SELECT t FROM Transaction t"),
    @NamedQuery(name = "Transaction.findByTransactionID", query = "SELECT t FROM Transaction t WHERE t.transactionID = :transactionID"),
    @NamedQuery(name = "Transaction.findByTransactionDate", query = "SELECT t FROM Transaction t WHERE t.transactionDate = :transactionDate"),
    @NamedQuery(name = "Transaction.findByCreditCardNbr", query = "SELECT t FROM Transaction t WHERE t.creditCardNbr = :creditCardNbr"),
    @NamedQuery(name = "Transaction.findByMobilePhoneNbr", query = "SELECT t FROM Transaction t WHERE t.mobilePhoneNbr = :mobilePhoneNbr"),
    @NamedQuery(name = "Transaction.findByPhoneCountryCode", query = "SELECT t FROM Transaction t WHERE t.phoneCountryCode = :phoneCountryCode"),
    @NamedQuery(name = "Transaction.findByBrokerPaymentID", query = "SELECT t FROM Transaction t WHERE t.brokerPaymentID = :brokerPaymentID"),
    @NamedQuery(name = "Transaction.findByBrokerTransactionDate", query = "SELECT t FROM Transaction t WHERE t.brokerTransactionDate = :brokerTransactionDate"),
    @NamedQuery(name = "Transaction.findBySignature", query = "SELECT t FROM Transaction t WHERE t.signature = :signature"),
    @NamedQuery(name = "Transaction.findByMessage", query = "SELECT t FROM Transaction t WHERE t.message = :message"),
    @NamedQuery(name = "Transaction.findByStatus", query = "SELECT t FROM Transaction t WHERE t.status = :status"),
    @NamedQuery(name = "Transaction.findByResult", query = "SELECT t FROM Transaction t WHERE t.result = :result"),
    @NamedQuery(name = "Transaction.findByTotal", query = "SELECT t FROM Transaction t WHERE t.total = :total")})
@Named
@RequestScoped
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "transactionID")
    private String transactionID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "transactionDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;
    @Size(max = 20)
    @Column(name = "creditCardNbr")
    private String creditCardNbr;
    @Size(max = 20)
    @Column(name = "mobilePhoneNbr")
    private String mobilePhoneNbr;
    @Size(max = 5)
    @Column(name = "phoneCountryCode")
    private String phoneCountryCode;
    @Size(max = 30)
    @Column(name = "brokerPaymentID")
    private String brokerPaymentID;
    @Size(max = 30)
    @Column(name = "brokerTransactionDate")
    private String brokerTransactionDate;
    @Size(max = 100)
    @Column(name = "signature")
    private String signature;
    @Size(max = 250)
    @Column(name = "message")
    private String message;
    @Size(max = 30)
    @Column(name = "status")
    private String status;
    @Size(max = 5)
    @Column(name = "result")
    private String result;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private Double total;
    @JoinColumn(name = "clientID", referencedColumnName = "clientID")
    @ManyToOne(optional = false)
    private Client client;
    @JoinColumn(name = "paymentID", referencedColumnName = "paymentID")
    @ManyToOne
    private Payment payment;

    public Transaction() {
    }

    public Transaction(String transactionID) {
        this.transactionID = transactionID;
    }

    public Transaction(String transactionID, Date transactionDate) {
        this.transactionID = transactionID;
        this.transactionDate = transactionDate;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getCreditCardNbr() {
        return creditCardNbr;
    }

    public void setCreditCardNbr(String creditCardNbr) {
        this.creditCardNbr = creditCardNbr;
    }

    public String getMobilePhoneNbr() {
        return mobilePhoneNbr;
    }

    public void setMobilePhoneNbr(String mobilePhoneNbr) {
        this.mobilePhoneNbr = mobilePhoneNbr;
    }

    public String getPhoneCountryCode() {
        return phoneCountryCode;
    }

    public void setPhoneCountryCode(String phoneCountryCode) {
        this.phoneCountryCode = phoneCountryCode;
    }

    public String getBrokerPaymentID() {
        return brokerPaymentID;
    }

    public void setBrokerPaymentID(String brokerPaymentID) {
        this.brokerPaymentID = brokerPaymentID;
    }

    public String getBrokerTransactionDate() {
        return brokerTransactionDate;
    }

    public void setBrokerTransactionDate(String brokerTransactionDate) {
        this.brokerTransactionDate = brokerTransactionDate;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
        hash += (transactionID != null ? transactionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        if ((this.transactionID == null && other.transactionID != null) || (this.transactionID != null && !this.transactionID.equals(other.transactionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.Transaction[ transactionID=" + transactionID + " ]";
    }
    
}
