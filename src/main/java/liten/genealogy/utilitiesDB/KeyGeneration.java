/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liten.genealogy.utilitiesDB;

import liten.genealogy.mainEntities.MobileOperator;
import liten.genealogy.mainEntities.PaymentMethod;
import liten.genealogy.mainEntities.LicenseType;
import liten.genealogy.mainEntities.AdminFamilyTree;
import liten.genealogy.mainEntities.MobilePhone;
import liten.genealogy.mainEntities.EthnicGroup;
import liten.genealogy.mainEntities.Payment;
import liten.genealogy.mainEntities.Client;
import liten.genealogy.mainEntities.Account;
import liten.genealogy.mainEntities.FamilyTree;
import liten.genealogy.mainEntities.Person;
import liten.genealogy.mainEntities.Enrollment;
import liten.genealogy.mainEntities.Transaction;
import liten.genealogy.mainEntities.Country;
import liten.genealogy.mainEntities.ClientType;
import jakarta.persistence.Query;
import java.net.InetAddress;

/**
 *
 * @author FACULTY
 */
public class KeyGeneration {

    public KeyGeneration() {

    }

    public static String generatePrimaryKey(Class myClass) {
        String primaryKey = "";
        String keyPrefix = "";
        String keySuffix = "";
        String nextKeySuffix = "";
        Query query = null;
        try {
            String tableName = myClass.getSimpleName();
            DataManager dataManager = DataManager.getInstance();
            if (tableName.equals("Account")) {
                query = dataManager.getEntityManager().createQuery("Select a From Account a Order By a.accountID DESC", Account.class);
                keyPrefix = "ACCT_";
                if (query.getResultList().size() != 0) {
                    query.setMaxResults(1);
                    Account lastRecord = (Account) query.getSingleResult();
                    keySuffix = lastRecord.getAccountID().substring(keyPrefix.length(), lastRecord.getAccountID().length());
                }
            } else if (tableName.equals("AdminFamilyTree")) {
                query = dataManager.getEntityManager().createQuery("Select a From AdminFamilyTree a Order By a.adminID DESC", AdminFamilyTree.class);
                keyPrefix = "AFT_";
                if (query.getResultList().size() != 0) {
                    query.setMaxResults(1);
                    AdminFamilyTree lastRecord = (AdminFamilyTree) query.getSingleResult();
                    keySuffix = lastRecord.getAdminID().substring(keyPrefix.length(), lastRecord.getAdminID().length());
                }
            } else if (tableName.equals("Client")) {
                query = dataManager.getEntityManager().createQuery("Select c from Client c Order By length(c.clientID) DESC, c.clientID DESC", Client.class);
                System.out.println(query.getResultList());
                keyPrefix = "CLI_";
                if (query.getResultList().size() != 0) {
                    query.setMaxResults(1);
                    Client lastRecord = (Client) query.getSingleResult();
                    keySuffix = lastRecord.getClientID().substring(keyPrefix.length(), lastRecord.getClientID().length());
                }
            } else if (tableName.equals("ClientType")) {
                query = dataManager.getEntityManager().createQuery("Select ct from ClientType ct Order By length(ct.clientTypeID) DESC, ct.clientTypeID DESC", ClientType.class);
                System.out.println(query.getResultList());
                keyPrefix = "CLIT_";
                if (query.getResultList().size() != 0) {
                    query.setMaxResults(1);
                    ClientType lastRecord = (ClientType) query.getSingleResult();
                    keySuffix = lastRecord.getClientTypeID().substring(keyPrefix.length(), lastRecord.getClientTypeID().length());
                }
            } else if (tableName.equals("Country")) {
                query = dataManager.getEntityManager().createQuery("Select c from Country c Order By length(c.countryID) DESC, c.countryID DESC", Country.class);
                System.out.println(query.getResultList());
                keyPrefix = "CTRY_";
                if (query.getResultList().size() != 0) {
                    query.setMaxResults(1);
                    Country lastRecord = (Country) query.getSingleResult();
                    keySuffix = lastRecord.getCountryID().substring(keyPrefix.length(), lastRecord.getCountryID().length());
                }
            } else if (tableName.equals("Enrollment")) {
                query = dataManager.getEntityManager().createQuery("Select e From Enrollment e Order By length(e.enrollmentID) DESC, e.enrollmentID DESC LIMIT 1", Enrollment.class);
                keyPrefix = "ERMT_";
                if (query.getResultList().size() != 0) {
                    Enrollment lastRecord = (Enrollment) query.getSingleResult();
                    keySuffix = lastRecord.getEnrollmentID().substring(keyPrefix.length(), lastRecord.getEnrollmentID().length());
                }
            } else if (tableName.equals("LicenseType")) {
                query = dataManager.getEntityManager().createQuery("Select lt From LicenseType lt Order By length(lt.licenseTypeID) DESC, lt.licenseTypeID DESC", LicenseType.class);
                keyPrefix = "LCT_";
                if (query.getResultList().size() != 0) {
                    query.setMaxResults(1);
                    LicenseType lastRecord = (LicenseType) query.getSingleResult();
                    keySuffix = lastRecord.getLicenseTypeID().substring(keyPrefix.length(), lastRecord.getLicenseTypeID().length());
                }
            } else if (tableName.equals("EthnicGroup")) {
                query = dataManager.getEntityManager().createQuery("Select eg From EthnicGroup eg Order By length(eg.ethnicGroupID) DESC, eg.ethnicGroupID DESC", EthnicGroup.class);
                keyPrefix = "EG_";
                if (query.getResultList().size() != 0) {
                    query.setMaxResults(1);
                    EthnicGroup lastRecord = (EthnicGroup) query.getSingleResult();
                    keySuffix = lastRecord.getEthnicGroupID().substring(keyPrefix.length(), lastRecord.getEthnicGroupID().length());
                }
            } else if (tableName.equals("FamilyTree")) {
                query = dataManager.getEntityManager().createQuery("Select f From FamilyTree f Order By length(f.familyTreeID) DESC, f.familyTreeID DESC", FamilyTree.class);
                keyPrefix = "FT_";
                if (query.getResultList().size() != 0) {
                    query.setMaxResults(1);
                    FamilyTree lastRecord = (FamilyTree) query.getSingleResult();
                    keySuffix = lastRecord.getFamilyTreeID().substring(lastRecord.getFamilyTreeID().length() - keyPrefix.length(), lastRecord.getFamilyTreeID().length());
                }
            } else if (tableName.equals("MobileOperator")) {
                query = dataManager.getEntityManager().createQuery("Select mo from MobileOperator mo Order By length(mo.mobileOperatorID) DESC, mo.mobileOperatorID DESC", MobileOperator.class);
                System.out.println(query.getResultList());
                keyPrefix = "MO_";
                if (query.getResultList().size() != 0) {
                    query.setMaxResults(1);
                    MobileOperator lastRecord = (MobileOperator) query.getSingleResult();
                    keySuffix = lastRecord.getMobileOperatorID().substring(keyPrefix.length(), lastRecord.getMobileOperatorID().length());
                }
            } else if (tableName.equals("MobilePhone")) {
                query = dataManager.getEntityManager().createQuery("Select m from MobilePhone m Order By length(m.mobilePhoneID) DESC, m.mobilePhoneID DESC", MobilePhone.class);
                System.out.println(query.getResultList());
                keyPrefix = "MP_";
                if (query.getResultList().size() != 0) {
                    query.setMaxResults(1);
                    MobilePhone lastRecord = (MobilePhone) query.getSingleResult();
                    keySuffix = lastRecord.getMobilePhoneID().substring(keyPrefix.length(), lastRecord.getMobilePhoneID().length());
                }
            } else if (tableName.equals("Payment")) {
                query = dataManager.getEntityManager().createQuery("Select p from Payment tpOrder By length(p.paymentID) DESC, p.paymentID DESC", Payment.class);
                System.out.println(query.getResultList());
                keyPrefix = "PAY_";
                if (query.getResultList().size() != 0) {
                    query.setMaxResults(1);
                    Payment lastRecord = (Payment) query.getSingleResult();
                    keySuffix = lastRecord.getPaymentID().substring(keyPrefix.length(), lastRecord.getPaymentID().length());
                }
            } else if (tableName.equals("PaymentMethod")) {
                query = dataManager.getEntityManager().createQuery("Select pm from PaymentMethod pm Order By length(pm.paymentMethodID) DESC, pm.paymentMethodID DESC", PaymentMethod.class);
                System.out.println(query.getResultList());
                keyPrefix = "PMT_";
                if (query.getResultList().size() != 0) {
                    query.setMaxResults(1);
                    PaymentMethod lastRecord = (PaymentMethod) query.getSingleResult();
                    keySuffix = lastRecord.getPaymentMethodID().substring(keyPrefix.length(), lastRecord.getPaymentMethodID().length());
                }
            } else if (tableName.equals("Person")) {
                query = dataManager.getEntityManager().createQuery("Select p from Person p Order By length(p.personID) DESC, p.personID DESC", Person.class);
                System.out.println(query.getResultList());
                keyPrefix = "PERS_";
                if (query.getResultList().size() != 0) {
                    query.setMaxResults(1);
                    Person lastRecord = (Person) query.getSingleResult();
                    keySuffix = lastRecord.getPersonID().substring(keyPrefix.length(), lastRecord.getPersonID().length());
                }
            } else if (tableName.equals("Transaction")) {
                query = dataManager.getEntityManager().createQuery("Select t from Transaction t Order By length(t.transactionID), t.transactionID DESC", Transaction.class);
                System.out.println(query.getResultList());
                keyPrefix = "TRT_";
                if (query.getResultList().size() != 0) {
                    query.setMaxResults(1);
                    Transaction lastRecord = (Transaction) query.getSingleResult();
                    keySuffix = lastRecord.getTransactionID().substring(keyPrefix.length(), lastRecord.getTransactionID().length());
                }
            }
            if (query.getResultList().size() != 0) {
                nextKeySuffix = getNextKeySuffix(keySuffix, keySuffix.length());
                primaryKey = keyPrefix + nextKeySuffix;
            } else {
                primaryKey = keyPrefix + 1;
            }
            System.out.println(primaryKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return primaryKey;
    }

    public static String getNextKeySuffix(String keySuffix, int suffixLength) {
        String nextKeySuffix = "";
        int nbrOfZeros = 0;
        try {
            System.out.println(keySuffix);
            System.out.println(suffixLength);
            int keySuffixVal = Integer.parseInt(keySuffix);
            int digitCount = countDigit(1 + keySuffixVal);
            if (digitCount - countDigit(keySuffixVal) == 1) {
                nbrOfZeros = suffixLength - digitCount;
            } else {
                nbrOfZeros = suffixLength - digitCount - 1;
            }
            String zeros = addZeros(nbrOfZeros);
            nextKeySuffix = zeros + String.valueOf(++keySuffixVal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nextKeySuffix;
    }

    public static int countDigit(int counter) {
        int digitCount = 0;
        int curCounter = counter;
        while ((curCounter / 10) != 0) {
            curCounter = curCounter / 10;
            ++digitCount;
        }
        if (digitCount > 0) {
            ++digitCount;
        }
        return digitCount;
    }

    public static String addZeros(int nbrOfZeros) {
        String zeros = "";
        for (int i = 0; i < nbrOfZeros; i++) {
            zeros = zeros + "0";
        }
        return zeros;
    }

    public static void main(String[] args) {

        try {
            InetAddress IP = InetAddress.getLocalHost();
            System.out.println("IP of my system is := " + IP.getHostAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
