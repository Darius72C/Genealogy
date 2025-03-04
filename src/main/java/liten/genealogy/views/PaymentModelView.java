/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.views;

import liten.genealogy.cinetPay.MobilePayment;
import liten.genealogy.dpo.VerifyToken;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import liten.genealogy.mainEntities.MobilePhone;
import liten.genealogy.mainEntities.Payment;
import liten.genealogy.mainEntities.PaymentMethod;
import liten.genealogy.mainEntities.Transaction;

/**
 *
 * @author darre
 */
@Named
@SessionScoped
public class PaymentModelView implements Serializable{

    String paymentID;
    String PaymentMethodID;
    String searchPaymentDate;
    Payment payment;
    Transaction transaction;
    PaymentMethod paymentMethod;
    MobilePayment mobilePayment;
    MobilePhone mobilePhone;
    List<MobilePhone> mobilePhoneList;
    List<Payment> paymentList;
    List<Payment> allDailyPaymentList;
    List<PaymentMethod> paymentMethodList;
    List<Transaction> transactionList;
    List<Payment> completedPaymentList;
    private boolean paidTransaction = false;
    private boolean nonPaidTransaction = false;
    VerifyToken verifyToken;

    public PaymentModelView() {
        payment = new Payment();
        payment.setTotal(0.0);
        paymentMethod = new PaymentMethod();
        transaction = new Transaction();
        mobilePayment = new MobilePayment();
        paymentList = new ArrayList<Payment>();
        allDailyPaymentList = new ArrayList<Payment>();
        paymentMethodList = new ArrayList<PaymentMethod>();
        transactionList = new ArrayList<Transaction>();
        completedPaymentList = new ArrayList<Payment>();
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getPaymentMethodID() {
        return PaymentMethodID;
    }

    public void setPaymentMethodID(String PaymentMethodID) {
        this.PaymentMethodID = PaymentMethodID;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public List<PaymentMethod> getPaymentMethodList() {
        return paymentMethodList;
    }

    public void setPaymentMethodList(List<PaymentMethod> paymentMethodList) {
        this.paymentMethodList = paymentMethodList;
    }

    public void setSearchPaymentDate(String searchPaymentDate) {
        this.searchPaymentDate = searchPaymentDate;
    }

    public String getSearchPaymentDate() {
        return this.searchPaymentDate;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public MobilePayment getMobilePayment() {
        return mobilePayment;
    }

    public void setMobilePayment(MobilePayment mobilePayment) {
        this.mobilePayment = mobilePayment;
    }

    public MobilePhone getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(MobilePhone mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public List<Payment> getAllDailyPaymentList() {
        return this.allDailyPaymentList;
    }

    public List<Transaction> getTransactionList() {
        return this.transactionList;
    }

    public List<Payment> getCompletedPaymentList() {
        return this.completedPaymentList;
    }

    public void setCompletedPaymentList(List<Payment> completedPaymentList) {
        this.completedPaymentList = completedPaymentList;
    }

    public void setAllDailyPaymentList(List<Payment> allDailyPaymentList) {
        this.allDailyPaymentList = allDailyPaymentList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public void setPaidTransaction(boolean paidTransaction) {
        this.paidTransaction = paidTransaction;
    }

    public boolean getPaidTransaction() {
        return this.paidTransaction;
    }

    public void setNonPaidTransaction(boolean nonPaidTransaction) {
        this.nonPaidTransaction = nonPaidTransaction;
    }

    public boolean getNonPaidTransaction() {
        return this.nonPaidTransaction;
    }

    public VerifyToken getVerifyToken() {
        return verifyToken;
    }

    public void setVerifyToken(VerifyToken verifyToken) {
        this.verifyToken = verifyToken;
    }

}
