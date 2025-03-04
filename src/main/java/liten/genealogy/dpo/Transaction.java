/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liten.genealogy.dpo;

import java.io.Serializable;
import java.util.Date;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FACULTY
 */
@XmlRootElement(name = "Transaction")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction implements Serializable {

    @XmlElement
    String Request = "createToken";
    @XmlElement
    Double PaymentAmount = 0.0;
    @XmlElement
    String PaymentCurrency = "USD";
    @XmlElement
    String CompanyRef = "";
    @XmlElement
    String RedirectURL = "https://www.sbo3c.com/payment/redirectTransaction.xhtml";
    @XmlElement
    String BackURL = "https://www.sbo3c.com/payment/paymentProcessing.xhtml";
    @XmlElement
    String DeclinedURL = "https://www.sbo3c.com/payment/processingError.xhtml";
    @XmlElement
    Integer CompanyRefUnique = 1;
    @XmlElement
    Integer PTL = 5;
    @XmlElement
    String PTLtype = "minutes";
    @XmlElement
    Integer TransactionChargeType = 1;
    @XmlElement
    Date TransactionAutoChargeDate = null;
    @XmlElement
    String customerEmail = "";
    @XmlElement
    String customerFirstName = "";
    @XmlElement
    String customerLastName = "";
    @XmlElement
    String customerAddress = "";
    @XmlElement
    String customerCity = "";
   /* @XmlElement
    String customerCountry = "CI";
    @XmlElement
    String customerDialCode = "CI";
    @XmlElement*/
    Long customerPhone;
    @XmlElement
    String customerZip = "";
    @XmlElement
    Integer DemandPaymentbyTraveler = 0;
    @XmlElement
    Integer EmailTransaction = 1;//Boolean
    @XmlElement
    String CompanyAccRef = null;
  /*  @XmlElement
    String userToken = "";*/
    @XmlElement
    String DefaultPayment = "MO";
    /*   @XmlElement
    String DefaultPaymentCountry = "Ivory Coast";
    @XmlElement
    String DefaultPaymentMNO = "orange";*/
    @XmlElement
    Integer TransactionToPrep = 0;//Boolean
    @XmlElement
    Integer AllowRecurrent = 0;//Boolean
    @XmlElement
    Integer FraudTimeLimit = 15;
   /* @XmlElement
    Integer Voidable = 0;*/

    public Transaction() {

    }

    public void setRequest(String Request) {
        this.Request = Request;
    }

    public void setPaymentAmount(Double PaymentAmount) {
        this.PaymentAmount = PaymentAmount;
    }

    public void setPaymentCurrency(String PaymentCurrency) {
        this.PaymentCurrency = PaymentCurrency;
    }

    public void setCompanyRef(String CompanyRef) {
        this.CompanyRef = CompanyRef;
    }

    public void setRedirectURL(String RedirectURL) {
        this.RedirectURL = RedirectURL;
    }

    public void setBackURL(String BackURL) {
        this.BackURL = BackURL;
    }

    public void setDeclinedURL(String DeclinedURL) {
        this.DeclinedURL = DeclinedURL;
    }

    public void setCompanyRefUnique(Integer CompanyRefUnique) {
        this.CompanyRefUnique = CompanyRefUnique;
    }

    public void setPTL(Integer PTL) {
        this.PTL = PTL;
    }

    public void setPTLtype(String PTLtype) {
        this.PTLtype = PTLtype;
    }

    public void setTransactionChargeType(Integer TransactionChargeType) {
        this.TransactionChargeType = TransactionChargeType;
    }

    public void setTransactionAutoChargeDate(Date TransactionAutoChargeDate) {
        this.TransactionAutoChargeDate = TransactionAutoChargeDate;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

/*    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    public void setCustomerDialCode(String customerDialCode) {
        this.customerDialCode = customerDialCode;
    }*/

    public void setCustomerPhone(Long customerPhone) {
        this.customerPhone = customerPhone;
    }

    public void setCustomerZip(String customerZip) {
        this.customerZip = customerZip;
    }

    public void setDemandPaymentbyTraveler(Integer DemandPaymentbyTraveler) {
        this.DemandPaymentbyTraveler = DemandPaymentbyTraveler;
    }

    public void setEmailTransaction(Integer EmailTransaction) {
        this.EmailTransaction = EmailTransaction;
    }

    public void setCompanyAccRef(String CompanyAccRef) {
        this.CompanyAccRef = CompanyAccRef;
    }

    /* public void setUserToken(String userToken) {
        this.userToken = userToken;
    }*/
    public void setDefaultPayment(String DefaultPayment) {
        this.DefaultPayment = DefaultPayment;
    }

    /*   public void setDefaultPaymentCountry(String DefaultPaymentCountry) {
        this.DefaultPaymentCountry = DefaultPaymentCountry;
    }

    public void setDefaultPaymentMNO(String DefaultPaymentMNO) {
        this.DefaultPaymentMNO = DefaultPaymentMNO;
    }*/
    public void setTransactionToPrep(Integer TransactionToPrep) {
        this.TransactionToPrep = TransactionToPrep;
    }

    public void setAllowRecurrent(Integer AllowRecurrent) {
        this.AllowRecurrent = AllowRecurrent;
    }

    public void setFraudTimeLimit(Integer FraudTimeLimit) {
        this.FraudTimeLimit = FraudTimeLimit;
    }

   /* public void setVoidable(Integer Voidable) {
        this.Voidable = Voidable;
    }*/

    public String getRequest() {
        return this.Request;
    }

    public Double getPaymentAmount() {
        return this.PaymentAmount;
    }

    public String getPaymentCurrency() {
        return this.PaymentCurrency;
    }

    public String getCompanyRef() {
        return this.CompanyRef;
    }

    public String getRedirectURL() {
        return this.RedirectURL;
    }

    public String getBackURL() {
        return this.BackURL;
    }

    public String getDeclinedURL() {
        return this.DeclinedURL;
    }

    public Integer getCompanyRefUnique() {
        return this.CompanyRefUnique;
    }

    public Integer getPTL() {
        return this.PTL;
    }

    public String getPTLtype() {
        return this.PTLtype;
    }

    public Integer getTransactionChargeType() {
        return this.TransactionChargeType;
    }

    public Date getTransactionAutoChargeDate() {
        return this.TransactionAutoChargeDate;
    }

    public String getCustomerEmail() {
        return this.customerEmail;
    }

    public String getCustomerFirstName() {
        return this.customerFirstName;
    }

    public String getCustomerLastName() {
        return this.customerLastName;
    }

    public String getCustomerAddress() {
        return this.customerAddress;
    }

    public String getCustomerCity() {
        return this.customerCity;
    }

 /*   public String getCustomerCountry() {
        return this.customerCountry;
    }

    public String getCustomerDialCode() {
        return this.customerDialCode;
    }
*/
    public Long getCustomerPhone() {
        return this.customerPhone;
    }

    public String getCustomerZip() {
        return this.customerZip;
    }

    public Integer getDemandPaymentbyTraveler() {
        return this.DemandPaymentbyTraveler;
    }

    public Integer getEmailTransaction() {
        return this.EmailTransaction;
    }

    public String getCompanyAccRef(String CompanyAccRef) {
        return this.CompanyAccRef = CompanyAccRef;
    }

    /*   public String getUserToken() {
        return this.userToken;
    }*/
    public String setDefaultPayment() {
        return this.DefaultPayment;
    }

    /*   public void setDefaultPaymentCountry(String DefaultPaymentCountry) {
        this.DefaultPaymentCountry = DefaultPaymentCountry;
    }

    public void setDefaultPaymentMNO(String DefaultPaymentMNO) {
        this.DefaultPaymentMNO = DefaultPaymentMNO;
    }*/
    public Integer getTransactionToPrep() {
        return this.TransactionToPrep;
    }

    public Integer getAllowRecurrent() {
        return this.AllowRecurrent;
    }

    public Integer getFraudTimeLimit() {
        return this.FraudTimeLimit;
    }

   /* public Integer getVoidable() {
        return this.Voidable;
    }*/
}
