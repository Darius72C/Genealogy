/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liten.genealogy.dpo;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FACULTY
 */
@XmlRootElement(name = "API3G")
@XmlAccessorType(XmlAccessType.FIELD)
public class VerificationResponse implements Serializable {

    @XmlElement
    private String Result;
    @XmlElement
    private String ResultExplanation;
    @XmlElement
    private String CustomerName;
    @XmlElement
    private String CustomerCredit;
    @XmlElement
    private String CustomerCreditType;
    @XmlElement
    private String TransactionApproval;
    @XmlElement
    private String TransactionCurrency;
    @XmlElement
    private String TransactionAmount;
    @XmlElement
    private String FraudAlert;
    @XmlElement
    private String FraudExplanation;
    @XmlElement
    private String TransactionNetAmount;
    @XmlElement
    private String TransactionSettlementDate;
    @XmlElement
    private String CustomerPhone;
    @XmlElement
    private String CustomerCountry;
    @XmlElement
    private String CustomerAddress;
    @XmlElement
    private String CustomerCity;
    @XmlElement
    private String CustomerZip;
    @XmlElement
    private String MobilePaymentRequest;

    public VerificationResponse() {

    }

    public void setResult(String result) {
        this.Result = result;
    }

    public String getResult() {
        return this.Result;
    }

    public void setResultExplanation(String resultExplanation) {
        this.ResultExplanation = resultExplanation;
    }

    public String getResultExplanation() {
        return this.ResultExplanation;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerCredit(String customerCredit) {
        CustomerCredit = customerCredit;
    }

    public String getCustomerCredit() {
        return CustomerCredit;
    }

    public void setCustomerCreditType(String customerCreditType) {
        CustomerCreditType = customerCreditType;
    }

    public String getCustomerCreditType() {
        return CustomerCreditType;
    }

    public void setTransactionApproval(String transactionApproval) {
        TransactionApproval = transactionApproval;
    }

    public String getTransactionApproval() {
        return TransactionApproval;
    }

    public void setTransactionCurrency(String transactionCurrency) {
        TransactionCurrency = transactionCurrency;
    }

    public String getTransactionCurrency() {
        return TransactionCurrency;
    }

    public void setTransactionAmount(String transactionAmount) {
        TransactionAmount = transactionAmount;
    }

    public String getTransactionAmount() {
        return TransactionAmount;
    }

    public void setFraudAlert(String fraudAlert) {
        FraudAlert = fraudAlert;
    }

    public String getFraudAlert() {
        return FraudAlert;
    }

    public void setFraudExplanation(String fraudExplanation) {
        FraudExplanation = fraudExplanation;
    }

    public String getFraudExplanation() {
        return FraudExplanation;
    }

    public void setTransactionNetAmount(String transactionNetAmount) {
        TransactionNetAmount = transactionNetAmount;
    }

    public String getTransactionNetAmount() {
        return TransactionNetAmount;
    }

    public void setTransactionSettlementDate(String transactionSettlementDate) {
        TransactionSettlementDate = transactionSettlementDate;
    }

    public String getTransactionSettlementDate() {
        return TransactionSettlementDate;
    }

    public void setCustomerPhone(String customerPhone) {
        CustomerPhone = customerPhone;
    }

    public String getCustomerPhone() {
        return CustomerPhone;
    }

    public void setCustomerCountry(String customerCountry) {
        CustomerCountry = customerCountry;
    }

    public String getCustomerCountry() {
        return CustomerCountry;
    }

    public void setCustomerAddress(String customerAddress) {
        CustomerAddress = customerAddress;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerCity(String customerCity) {
        CustomerCity = customerCity;
    }

    public String getCustomerCity() {
        return CustomerCity;
    }

    public void setCustomerZip(String customerZip) {
        CustomerZip = customerZip;
    }

    public String getCustomerZip() {
        return CustomerZip;
    }

    public void setMobilePaymentRequest(String mobilePaymentRequest) {
        MobilePaymentRequest = mobilePaymentRequest;
    }

    public String getMobilePaymentRequest() {
        return MobilePaymentRequest;
    }

}
