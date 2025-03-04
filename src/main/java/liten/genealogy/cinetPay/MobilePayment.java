/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liten.genealogy.cinetPay;

import java.io.Serializable;
import java.util.Date;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

/**
 *
 * @author FACULTY
 */
@Named
@RequestScoped
public class MobilePayment implements Serializable {

    private double amount;
    private String currency = "CFA";
    private String siteID = "900172";
    private String transactionID;
    private Date transactionDate = new Date();
    private String paymentConfig = "SINGLE";
    private String pageAction = "PAYMENT";
    private String version = "V1";
    private String language = "fr";
    private String designation = "Achats personnels";
    private String custom;
    private String apiKey = "17267196495c9f96c3474930.52972895";
    private String signature;
    private String notifyURL = "https://www.sbo3c.com/cinetPay/mobilePaymentProcess.xhtml";
    ;
    private String returnURL = "https://www.sbo3c.com";
    private String cancelURL = "https://www.sbo3c.com/payment/processingError.xhtml";
    private String cellPhoneNbr;
    private String phonePrefixe;
    private String paymentMethod;

    public static String SIGN_URL = "https://api.cinetpay.com/v1/?method=getSignatureByPost";
    public static String PAYMENT_URL = "https://secure.cinetpay.com/";
    public static String VERIFICATION_URL = "https://api.cinetpay.com/v1/?method=checkPayStatus";

    public MobilePayment() {

    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
     public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setPaymentConfig(String paymentConfig) {
        this.paymentConfig = paymentConfig;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setSiteID(String siteID) {
        this.siteID = siteID;
    }

    public void setPageAction(String pageAction) {
        this.pageAction = pageAction;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setNotifyURL(String notifyURL) {
        this.notifyURL = notifyURL;
    }

    public void setReturnURL(String returnURL) {
        this.returnURL = returnURL;
    }

    public void setCancelURL(String cancelURL) {
        this.cancelURL = cancelURL;
    }

    public void setCellPhoneNbr(String cellPhoneNbr) {
        this.cellPhoneNbr = cellPhoneNbr;
    }

    public void setPhonePrefixe(String phonePrefixe) {
        this.phonePrefixe = phonePrefixe;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getSiteID() {
        return siteID;
    }

    public String getTransactionID() {
        return this.transactionID;
    }

    public Date getTransactionDate() {
        return this.transactionDate;
    }

    public String getPaymentConfig() {
        return this.paymentConfig;
    }

    public String getPageAction() {
        return this.pageAction;
    }

    public String getVersion() {
        return this.version;
    }

    public String getLanguage() {
        return this.language;
    }

    public String getDesignation() {
        return this.designation;
    }

    public String getCustom() {
        return this.custom;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public String getSignature() {
        return this.signature;
    }

    public String getNotifyURL() {
        return this.notifyURL;
    }

    public String getReturnURL() {
        return this.returnURL;
    }

    public String getCancelURL() {
        return this.cancelURL;
    }

    public String getCellPhoneNbr() {
        return this.cellPhoneNbr;
    }

    public String getPhonePrefixe() {
        return this.phonePrefixe;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }
}
