/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.managers;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import liten.genealogy.mainEntities.Payment;
import liten.genealogy.mainEntities.PaymentMethod;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import liten.genealogy.mainEntities.Transaction;
import liten.genealogy.utilitiesDB.DataManager;
import liten.genealogy.utilitiesDB.KeyGeneration;
import liten.genealogy.utilitiesDB.MainDAO;

/**
 *
 * @author darre
 */
@Named
@SessionScoped
public class PaymentManager implements Serializable {

    public PaymentManager() {

    }

    public Payment addNewPayment(Payment payment) {
        Payment paymentDB = null;
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            if (payment != null && payment.getPaymentID() == null) {
                String paymentID = KeyGeneration.generatePrimaryKey(Class.forName("mainEntities.Payment"));
                paymentDB.setPaymentID(paymentID);
            }
            paymentDB = connectionServiceFacade.mergeEntity(payment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paymentDB;
    }

    public PaymentMethod addNewPaymentMethod(PaymentMethod paymentMethod) {
        PaymentMethod paymentMethodDB = null;
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            if (paymentMethod != null && paymentMethod.getPaymentMethodID() == null) {
                String paymentMethodID = KeyGeneration.generatePrimaryKey(Class.forName("mainEntities.PaymentMethod"));
                paymentMethodDB.setPaymentMethodID(paymentMethodID);
            }
            paymentMethodDB = connectionServiceFacade.mergeEntity(paymentMethod);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paymentMethodDB;
    }

    public Transaction addNewTransaction(Transaction transaction) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            String transactionID = transaction.getTransactionID();
            if (transactionID == null) {
                transactionID = KeyGeneration.generatePrimaryKey(Class.forName("mainEntities.Transaction"));
                transaction.setTransactionID(transactionID);
            }
            transaction = connectionServiceFacade.mergeEntity(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transaction;
    }

    public Transaction updateTransaction(Transaction transaction) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            Transaction transactionDB = MainDAO.findTransaction(transaction.getTransactionID());
            if (transactionDB != null && !transaction.equals(transactionDB)) {
                transaction = connectionServiceFacade.mergeEntity(transaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transaction;
    }

    public void updatePayment(Payment payment) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            Payment paymentDB = MainDAO.findPayment(payment.getPaymentID());
            if (paymentDB != null) {
                paymentDB = connectionServiceFacade.mergeEntity(payment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePaymentMethod(PaymentMethod paymentMethod) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            PaymentMethod paymentMethodDB = MainDAO.findPaymentMethod(paymentMethod.getPaymentMethodID());
            if (paymentMethodDB != null) {
                paymentMethodDB = connectionServiceFacade.mergeEntity(paymentMethod);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePayment(String paymentID) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            Payment paymentDB = MainDAO.findPayment(paymentID);
            if (paymentDB != null) {
                connectionServiceFacade.removeEntity(paymentDB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePaymentMethod(String paymentMethodID) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            PaymentMethod paymentMethodDB = MainDAO.findPaymentMethod(paymentMethodID);
            if (paymentMethodDB != null) {
                connectionServiceFacade.removeEntity(paymentMethodDB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Payment findPayment(String paymentID) {
        Payment payment = null;
        try {
            payment = MainDAO.findPayment(paymentID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payment;
    }

    public PaymentMethod findPaymentMethod(String paymentMethodID) {
        PaymentMethod paymentMethod = null;
        try {
            paymentMethod = MainDAO.findPaymentMethod(paymentMethodID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paymentMethod;
    }

    public List<Payment> findAllPayment() {
        List<Payment> myPaymentList = MainDAO.findAllPayment();
        if (myPaymentList == null) {
            myPaymentList = new ArrayList<Payment>();
        }
        return myPaymentList;
    }

    public List<PaymentMethod> findAllPaymentMethod() {
        List<PaymentMethod> myPaymentMethodList = MainDAO.findAllPaymentMethod();
        if (myPaymentMethodList == null) {
            myPaymentMethodList = new ArrayList<PaymentMethod>();
        }
        return myPaymentMethodList;
    }
}
