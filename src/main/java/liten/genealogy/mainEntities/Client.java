/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.mainEntities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "client")
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findByClientID", query = "SELECT c FROM Client c WHERE c.clientID = :clientID"),
    @NamedQuery(name = "Client.findByCode", query = "SELECT c FROM Client c WHERE c.code = :code"),
    @NamedQuery(name = "Client.findByNickName", query = "SELECT c FROM Client c WHERE c.nickName = :nickName"),
    @NamedQuery(name = "Client.findByMainPhone", query = "SELECT c FROM Client c WHERE c.mainPhone = :mainPhone"),
    @NamedQuery(name = "Client.findByAlternatePhone", query = "SELECT c FROM Client c WHERE c.alternatePhone = :alternatePhone"),
    @NamedQuery(name = "Client.findByEmail", query = "SELECT c FROM Client c WHERE c.email = :email"),
    @NamedQuery(name = "Client.findByCreatedDate", query = "SELECT c FROM Client c WHERE c.createdDate = :createdDate"),
    @NamedQuery(name = "Client.findByModifiedDate", query = "SELECT c FROM Client c WHERE c.modifiedDate = :modifiedDate")})
@Named
@RequestScoped
public class Client implements Serializable {

    @Size(max = 30)
    @Column(name = "code")
    private String code;
    @Size(max = 30)
    @Column(name = "nickName")
    private String nickName;
    @Size(max = 20)
    @Column(name = "mainPhone")
    private String mainPhone;
    @Size(max = 20)
    @Column(name = "alternatePhone")
    private String alternatePhone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "email")
    private String email;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "clientID")
    private String clientID;
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "modifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    @JsonBackReference
    private List<MobilePhone> mobilePhoneList;
    @JoinColumn(name = "clientTypeID", referencedColumnName = "clientTypeID")
    @ManyToOne
    @JsonManagedReference
    private ClientType clientType;
    @JoinColumn(name = "personID", referencedColumnName = "personID")
    @ManyToOne
    @JsonManagedReference
    private Person person;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    @JsonBackReference
    private List<Payment> paymentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    @JsonBackReference
    private List<Account> accountList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    @JsonBackReference
    private List<Transaction> transactionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    @JsonBackReference
    private List<Enrollment> enrollmentList;

    public Client() {
    }

    public Client(String clientID) {
        this.clientID = clientID;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
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

    public List<MobilePhone> getMobilePhoneList() {
        return mobilePhoneList;
    }

    public void setMobilePhoneList(List<MobilePhone> mobilePhoneList) {
        this.mobilePhoneList = mobilePhoneList;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
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
        hash += (clientID != null ? clientID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.clientID == null && other.clientID != null) || (this.clientID != null && !this.clientID.equals(other.clientID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mainEntities.Client[ clientID=" + clientID + " ]";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMainPhone() {
        return mainPhone;
    }

    public void setMainPhone(String mainPhone) {
        this.mainPhone = mainPhone;
    }

    public String getAlternatePhone() {
        return alternatePhone;
    }

    public void setAlternatePhone(String alternatePhone) {
        this.alternatePhone = alternatePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
