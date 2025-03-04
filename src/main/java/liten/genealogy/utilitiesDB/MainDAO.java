/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liten.genealogy.utilitiesDB;

/**
 *
 * @author FACULTY
 */
import liten.genealogy.mainEntities.MobileOperator;
import liten.genealogy.mainEntities.LicenseType;
import liten.genealogy.mainEntities.AdminFatherTree;
import liten.genealogy.mainEntities.PersonPicture;
import liten.genealogy.mainEntities.KnownUnions;
import liten.genealogy.mainEntities.MobilePhone;
import liten.genealogy.mainEntities.PersonDiploma;
import liten.genealogy.mainEntities.EthnicGroup;
import liten.genealogy.mainEntities.FatherChildren;
import liten.genealogy.mainEntities.PersonResume;
import liten.genealogy.mainEntities.Client;
import liten.genealogy.mainEntities.Account;
import liten.genealogy.mainEntities.Enrollment;
import liten.genealogy.mainEntities.Person;
import liten.genealogy.mainEntities.Transaction;
import liten.genealogy.mainEntities.PaymentMethod;
import liten.genealogy.mainEntities.AdminFamilyTree;
import liten.genealogy.mainEntities.Genre;
import liten.genealogy.mainEntities.MotherChildren;
import liten.genealogy.mainEntities.Payment;
import liten.genealogy.mainEntities.EndofLife;
import liten.genealogy.mainEntities.FamilyTree;
import liten.genealogy.mainEntities.AdminMotherTree;
import liten.genealogy.mainEntities.Country;
import liten.genealogy.mainEntities.ClientType;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import org.primefaces.model.FilterMeta;
import org.springframework.stereotype.Repository;

/**
 *
 * @author FACULTY
 */
@Named
@SessionScoped
@Repository
public class MainDAO implements Serializable {

    public static Account findAccount(String myAccountID) {
        Account myAccount = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            myAccount = myEntityManager.find(Account.class,
                    myAccountID);
            myEntityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myAccount;
    }

    public static List<Account> findAllAccount() {
        List<Account> myAccountList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select a from Account a", Account.class);
            myAccountList = (List<Account>) query.getResultList();
            myEntityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myAccountList;
    }

    public static Account findAccountPerPerson(String personID) {
        Account myAccount = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select a from Account a where a.person.personID="
                            + "'" + personID + "'", Account.class
                    );
            myAccount = (Account) getSingleResult(query);
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myAccount;
    }
    
    public static Account findAccountPerClient(String clientID) {
        Account myAccount = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select a from Account a where a.client.clientID"
                            + "'" + clientID + "'", Account.class
                    );
            myAccount = (Account) getSingleResult(query);
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myAccount;
    }

    public static Account findAccountPerUsername(String userName) {
        Account myAccount = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select a from Account a where a.userName="
                            + "'" + userName + "'", Account.class
                    );
            myAccount = (Account) getSingleResult(query);
            myEntityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myAccount;
    }

    public static Account findAccountPerCredentials(String userName, String password) {
        Account myAccount = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select a from Account a where a.userName="
                            + "'" + userName + "'" + " and a.password =" + "'" + password + "'", Account.class
                    );
            myAccount = (Account) getSingleResult(query);
            myEntityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myAccount;
    }

    public static EthnicGroup findEthnicGroup(String ethnicGroupID) {
        EthnicGroup myEthnicGroup = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            myEthnicGroup = myEntityManager.find(EthnicGroup.class, ethnicGroupID);
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myEthnicGroup;
    }

    public static EthnicGroup findEthnicGroupPerName(String name) {
        EthnicGroup myEthnicGroup = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from EthnicGroup e where e.name ="
                            + "'" + name + "'", EthnicGroup.class);
            myEthnicGroup = (EthnicGroup) getSingleResult(query);
            myEntityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myEthnicGroup;
    }

    public static List<EthnicGroup> findAllEthnicGroupPerCountry(String countryID) {
        List<EthnicGroup> myEthnicGroupList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from EthnicGroup e e.country.countryID='" + countryID + "'", EthnicGroup.class);
            myEthnicGroupList = (List<EthnicGroup>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myEthnicGroupList;
    }

    public static List<EthnicGroup> findAllEthnicGroup() {
        List<EthnicGroup> myEthnicGroupList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from EthnicGroup e", EthnicGroup.class);
            myEthnicGroupList = (List<EthnicGroup>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myEthnicGroupList;
    }

    public static Genre findGenre(String myGenreID) {
        Genre myGenre = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            myGenre = myEntityManager.find(Genre.class,
                    myGenreID);
            myEntityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myGenre;
    }

    public static List<Genre> findAllGenre() {
        List<Genre> myGenreList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select g from Genre g", Genre.class);
            myGenreList = (List<Genre>) query.getResultList();
            myEntityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myGenreList;
    }

    public static EndofLife findEndofLife(String myPersonID) {
        EndofLife myEndofLife = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            myEndofLife = myEntityManager.find(EndofLife.class,
                    myPersonID);
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myEndofLife;
    }

    public static List<EndofLife> findAllEndofLife() {
        List<EndofLife> myEndofLifeList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from EndofLife e", EndofLife.class);
            myEndofLifeList = (List<EndofLife>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myEndofLifeList;
    }

    public static EndofLife findEndofLifePerUsername(String userName) {
        EndofLife myEndofLife = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select a.person.endofLife from Account a where a.userName="
                            + "'" + userName + "'", EndofLife.class
                    );
            myEndofLife = (EndofLife) getSingleResult(query);
            myEntityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myEndofLife;
    }

    public static EndofLife findEndofLifePerCredentials(String userName, String password) {
        EndofLife myEndofLife = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select a.person.endofLife from Account a where a.userName="
                            + "'" + userName + "'" + " and a.password =" + "'" + password + "'", EndofLife.class
                    );
            myEndofLife = (EndofLife) getSingleResult(query);
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myEndofLife;
    }

    public static Client findClient(String myClientID) {
        Client myClient = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            myClient = myEntityManager.find(Client.class,
                    myClientID);
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myClient;
    }

    public static Client findClientPerEmail(String email) {
        Client myClient = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select c from Client c", Client.class);
            myClient = (Client) getSingleResult(query);
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myClient;
    }

    public static Client findClientPerUsername(String userName) {
        Client myClient = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select a.client from Account a where a.userName="
                            + "'" + userName + "'", Client.class
                    );
            myClient = (Client) getSingleResult(query);
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myClient;
    }

    public static Client findClientPerCredentials(String userName, String password) {
        Client myClient = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select a.client from Account a where a.userName="
                            + "'" + userName + "'" + " and a.password =" + "'" + password + "'", Client.class
                    );
            myClient = (Client) getSingleResult(query);
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myClient;
    }

    public static Client findClientPerNickName(String nickName) {
        Client myClient = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select c from Client c where c.nickName="
                            + "'" + nickName + "'", Client.class
                    );
            myClient = (Client) getSingleResult(query);
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myClient;
    }

    public static List<Client> findAllClient() {
        List<Client> myClientList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select c from Client c", Client.class);
            myClientList = (List<Client>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myClientList;
    }

    public static List<Person> findAllChildrenPerFather(String fatherID) {
        List<Person> myChildrenList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select fc.child from FatherChildren fc where fc.father.personID='" + fatherID + "'", Person.class);
            myChildrenList = (List<Person>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myChildrenList;
    }

    public static List<Person> findAllAliveChildrenPerFather(String fatherID) {
        List<Person> myChildrenList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select fc.child from FatherChildren fc where fc.father.personID='" + fatherID + "' and fc.child.personID !=fc.child.endofLife.personID", Person.class);
            myChildrenList = (List<Person>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myChildrenList;
    }

    public static List<Person> findAllChildrenPerMother(String motherID) {
        List<Person> myChildrenList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select mc.child from MotherChildren mc where mc.mother.personID='" + motherID + "'", Person.class);
            myChildrenList = (List<Person>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myChildrenList;
    }

    public static List<Person> findAllAliveChildrenPerMother(String motherID) {
        List<Person> myChildrenList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select mc.child from MotherChildren mc where mc.mother.personID='" + motherID + "' and mc.child.personID !=mc.child.endofLife.personID", Person.class);
            myChildrenList = (List<Person>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myChildrenList;
    }

    public static List<Person> findAllParentPerChild(String childID) {
        List<Person> myParentList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select mc.mother from MotherChildren mc where mc.child.personID='" + childID + "' union select fc.father from FatherChildren fc where fc.child.personID='" + childID + "'", Person.class);
            myParentList = (List<Person>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myParentList;
    }

    public static ClientType findClientType(String myClientTypeID) {
        ClientType myClientType = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            myClientType = myEntityManager.find(ClientType.class,
                    myClientTypeID);
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myClientType;
    }

    public static List<ClientType> findAllClientType() {
        List<ClientType> myClientTypeList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select ct from ClientType ct", ClientType.class);
            myClientTypeList = (List<ClientType>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myClientTypeList;
    }

    public static Country findCountry(String countryID) {
        Country myCountry = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query = myEntityManager.createQuery("Select c from Country c where c.countryID='" + countryID + "'");
            myCountry = (Country) getSingleResult(query);
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myCountry;
    }

    public static Country findCountryPerName(String myCountryName) {
        Country myCountry = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select c from Country c where c.name=" + "'" + myCountryName + "'",
                            Country.class);
            myCountry = (Country) getSingleResult(query);
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myCountry;
    }

    public static List<Country> findAllCountry() {
        List<Country> myCountryList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select c from Country c", Country.class);
            myCountryList = (List<Country>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myCountryList;
    }

    public static Payment findMyLatestPayment(String clientID) {
        Payment myPayment = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select distinct e.payment from Enrollment e where e.client.clientID=" + "'" + clientID + "' having max(e.payment.paymentDate)=e.payment.paymentDate", Payment.class
                    );
            myPayment = (Payment) query.getSingleResult();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myPayment;
    }

    public static List<Payment> findAllPayment() {
        List<Payment> myPaymentList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select p from Payment p", Payment.class
                    );
            myPaymentList = (List<Payment>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myPaymentList;
    }

    public static List<Payment> findAllDailyPayment(Date curDate) {
        List<Payment> myPaymentList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select p from Payment p where function('DATE_FORMAT',p.paymentDate,'%Y-%m-%d')=" + convertShortDate(curDate), Payment.class
                    );
            myPaymentList = (List<Payment>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myPaymentList;
    }

    public static List<Enrollment> findAllEnrollmentPerClient(String clientID) {
        List<Enrollment> myEnrollmentList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from Enrollment e where e.client.clientID='" + clientID + "'", Enrollment.class);
            myEnrollmentList = (List<Enrollment>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myEnrollmentList;
    }

    public static List<Enrollment> findAllEnrollmentPerPayment(String paymentID) {
        List<Enrollment> myEnrollmentList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from Enrollment e where e.payment.paymentID=" + "'" + paymentID + "'", Enrollment.class
                    );
            myEnrollmentList = (List<Enrollment>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myEnrollmentList;
    }

    public static List<Enrollment> findAllEnrollmentPerTransaction(String transactionID) {
        List<Enrollment> myEnrollmentList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from Enrollment e where e.payment.transactionList.transactionID" + "'" + transactionID + "'", Enrollment.class
                    );
            myEnrollmentList = (List<Enrollment>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myEnrollmentList;
    }

    public static Transaction findTransaction(String transactionID) {
        Transaction myTransaction = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            myTransaction = myEntityManager.find(Transaction.class,
                    transactionID);
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myTransaction;
    }

    public static Transaction findTransactionPerTransRef(String transRef) {
        Transaction myTransaction = null;
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            Query query = connectionServiceFacade.getEntityManager().createQuery("select t from Transaction t where t.brokerPaymentID=" + "'" + transRef + "'", Transaction.class);
            myTransaction = (Transaction) getSingleResult(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return myTransaction;
    }

    public static List<Transaction> findAllTransactionPerClient(String clientID) {
        List<Transaction> myTransactionList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select t from Transaction t t.client.clientID=" + "'" + clientID + "'", Transaction.class
                    );
            myTransactionList = (List<Transaction>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myTransactionList;
    }

    public static List<Transaction> findAllTransaction() {
        List<Transaction> myTransactionList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select t from Transaction t", Transaction.class
                    );
            myTransactionList = (List<Transaction>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myTransactionList;
    }

    public static Payment findPayment(String paymentID) {
        Payment myPayment = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            myPayment = myEntityManager.find(Payment.class,
                    paymentID);
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myPayment;
    }

    public static PaymentMethod findPaymentMethod(String paymentMethodID) {
        PaymentMethod myPaymentMethod = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            myPaymentMethod = myEntityManager.find(PaymentMethod.class,
                    paymentMethodID);
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myPaymentMethod;
    }

    public static List<PaymentMethod> findAllPaymentMethod() {
        List<PaymentMethod> myPaymentMethodList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select pm from PaymentMethod pm", PaymentMethod.class
                    );
            myPaymentMethodList = (List<PaymentMethod>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myPaymentMethodList;
    }

    public static LicenseType findLicenseType(String licenseTypeID) {
        LicenseType myLicenseType = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            myLicenseType = myEntityManager.find(LicenseType.class, licenseTypeID);
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myLicenseType;
    }

    public static List<LicenseType> findAllLicenseType() {
        List<LicenseType> myLicenseTypeList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select dl from LicenseType dl", LicenseType.class);
            myLicenseTypeList = (List<LicenseType>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myLicenseTypeList;
    }

    public static Enrollment findEnrollment(String enrollmentID) {
        Enrollment myEnrollment = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            myEnrollment = myEntityManager.find(Enrollment.class, enrollmentID);
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myEnrollment;
    }

    public static Enrollment findActiveEnrollment(String clientID) {
        Enrollment myEnrollment = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from Enrollment e where e.client.clientID='" + clientID + "' and e.isValid=1", Enrollment.class);
            myEnrollment = (Enrollment) getSingleResult(query);
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myEnrollment;
    }

    public static Enrollment findLastEnrollment(String clientID) {
        Enrollment myEnrollment = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from Enrollment e where e.client.clientID='" + clientID + "' having max(e.endDate)=e.endDate", Enrollment.class);
            myEnrollment = (Enrollment) getSingleResult(query);
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myEnrollment;
    }

    public static Enrollment findEnrollmentPerEndofLifeAndDates(String clientID, Date startDate, Date endDate) {
        Enrollment myEnrollment = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from Enrollment e where e.client.clientID='" + clientID + "' and e.startDate)='" + startDate + "' and e.endDate)='" + endDate + "'", Enrollment.class);
            myEnrollment = (Enrollment) getSingleResult(query);
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myEnrollment;
    }

    public static List<Enrollment> findAllEnrollmentPerEndofLife(String clientID) {
        List<Enrollment> myEnrollmentList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from Enrollment e where e.client.clientID='" + clientID + "'", Enrollment.class);
            myEnrollmentList = (List<Enrollment>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myEnrollmentList;
    }

    public static List<Enrollment> findAllEnrollment() {
        List<Enrollment> myEnrollmentList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from Enrollment e ", Enrollment.class);
            myEnrollmentList = (List<Enrollment>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myEnrollmentList;
    }

    //------------------Complete
    public static AdminFamilyTree findAdminFamilyTree(String myAdminID) {
        AdminFamilyTree myAdminFamilyTree = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            myAdminFamilyTree = myEntityManager.find(AdminFamilyTree.class,
                    myAdminID);
            myEntityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myAdminFamilyTree;
    }

    public static AdminFatherTree findAdminFatherTree(String myAdminID, String myFatherFamilyTreeID) {
        AdminFatherTree myAdminFatherTree = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from AdminFatherTree e where e.adminFatherTreePK.adminID = '" + myAdminID + "' and e.adminFatherTreePK.familyTreeID = " + myFatherFamilyTreeID + "'", AdminFatherTree.class);
            myAdminFatherTree = (AdminFatherTree) query.getSingleResult();
            myEntityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myAdminFatherTree;
    }

    public static List<AdminFatherTree> findAllAdminFatherTree() {
        List<AdminFatherTree> myAdminFatherTreeList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from AdminFatherTree e", AdminFatherTree.class);
            myAdminFatherTreeList = (List<AdminFatherTree>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myAdminFatherTreeList;
    }

    public static AdminMotherTree findAdminMotherTree(String myAdminID, String myMotherFamilyTreeID) {
        AdminMotherTree myAdminMotherTree = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from AdminMotherTree e where e.adminMotherTreePK.adminID = '" + myAdminID + "' and e.adminMotherTreePK.familyTreeID = " + myMotherFamilyTreeID + "'", AdminMotherTree.class);
            myAdminMotherTree = (AdminMotherTree) query.getSingleResult();
            myEntityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myAdminMotherTree;
    }

    public static List<AdminMotherTree> findAllAdminMotherTree() {
        List<AdminMotherTree> myAdminMotherTreeList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from AdminMotherTree e", AdminMotherTree.class);
            myAdminMotherTreeList = (List<AdminMotherTree>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myAdminMotherTreeList;
    }

    public static FamilyTree findFamilyTree(String myFamilyTreeID) {
        FamilyTree myFamilyTree = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            myFamilyTree = myEntityManager.find(FamilyTree.class,
                    myFamilyTreeID);
            myEntityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myFamilyTree;
    }

    public static List<FamilyTree> findAllFamilyTree() {
        List<FamilyTree> myFamilyTreeList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from FamilyTree e", FamilyTree.class
                    );
            myFamilyTreeList = (List<FamilyTree>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myFamilyTreeList;
    }

    public static FatherChildren findFatherChildren(String myFatherID, String myChildID) {
        FatherChildren myFatherChildren = null;
        //FatherChildren myChild = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from FatherChildren e where e.father.personID = '" + myFatherID + "' and e.child.personID = " + myChildID + "'", FatherChildren.class);
            myFatherChildren = (FatherChildren) query.getSingleResult();
            myEntityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myFatherChildren;
    }

    public static KnownUnions findKnownUnion(String myHusbandID, String myWifeID) {
        KnownUnions myKnownUnions = null;
        //FatherChildren myChild = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            myKnownUnions = myEntityManager.find(KnownUnions.class,
                    new KnownUnions(myHusbandID, myWifeID));
            myEntityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myKnownUnions;
    }

    public static MobileOperator findMobileOperator(String myMobileOperatorID) {
        MobileOperator myMobileOperator = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            myMobileOperator = myEntityManager.find(MobileOperator.class,
                    myMobileOperatorID);
            myEntityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myMobileOperator;
    }

    public static List<MobileOperator> findAllMobileOperator() {
        List<MobileOperator> myMobileOperatorList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from MobileOperator e ", MobileOperator.class);
            myMobileOperatorList = (List<MobileOperator>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myMobileOperatorList;
    }

    public static MobilePhone findMobilePhone(String myMobilePhoneID) {
        MobilePhone myMobilePhone = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            myMobilePhone = myEntityManager.find(MobilePhone.class,
                    myMobilePhoneID);
            myEntityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myMobilePhone;
    }

    public static List<MobilePhone> findAllMobilePhonePerMobileNbr(String mobileNbr) {
        List<MobilePhone> myMobilePhoneList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from MobilePhone e where e.mobileNbr = '" + mobileNbr + "'", MobilePhone.class);
            myMobilePhoneList = (List<MobilePhone>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myMobilePhoneList;
    }

    public static MotherChildren findMotherChildren(String myMotherID, String myChildID) {
        MotherChildren myMotherChildren = null;
        //FatherChildren myChild = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from MotherChildren e where e.mother.personID = '" + myMotherID + "' and e.child.personID = " + myChildID + "'", MotherChildren.class);
            myMotherChildren = (MotherChildren) query.getSingleResult();
            myEntityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myMotherChildren;
    }

    public static Person findPerson(String myPersonID) {
        Person myPerson = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            myPerson = myEntityManager.find(Person.class,
                    myPersonID);
            myEntityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myPerson;
    }

    public static Person findPersonPerFilter(String firstName, String lastName, Date dob, String pob,
            String fnFather, String lnFather, String fnMother, String lnMother) {
        Person myPerson = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from Person e where e.firstName like '%" + firstName + "%' and e.lastName like '%" + lastName
                            + "%' and function('DATE_FORMAT',e.dateOfBirth,'%Y-%m-%d')=" + convertShortDate(dob) + " and e.placeOfBirth like %'" + pob
                            + " and e.father.firstName like '%" + fnFather + "%' and e.father.lastName like '%" + lnFather + "%' and e.mother.firstName like '%" + fnMother
                            + "%' and e.mother.lastName like '%" + lnMother + "%'", Person.class);
            myPerson = (Person) getSingleResult(query);
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myPerson;
    }

    public static PersonDiploma findPersonDiploma(String myPersonID) {
        PersonDiploma myPersonDiploma = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            myPersonDiploma = myEntityManager.find(PersonDiploma.class,
                    myPersonID);
            myEntityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myPersonDiploma;
    }

    public static PersonPicture findPersonPicture(String myPersonID) {
        PersonPicture myPersonPicture = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            myPersonPicture = myEntityManager.find(PersonPicture.class,
                    myPersonID);
            myEntityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myPersonPicture;
    }

    public static PersonResume findPersonResume(String myPersonID) {
        PersonResume myPersonResume = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            myPersonResume = myEntityManager.find(PersonResume.class,
                    myPersonID);
            myEntityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myPersonResume;
    }

    public static List<PersonResume> findAllResumePerPerson(String personID) {
        List<PersonResume> myPersonResumeList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from PersonResume e where e.personID = '" + personID + "'", PersonResume.class);
            myPersonResumeList = (List<PersonResume>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myPersonResumeList;
    }

    public static List<PersonPicture> findAllPicturePerPerson(String personID) {
        List<PersonPicture> myPersonPictureList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from PersonPicture e where e.personID = '" + personID + "'", PersonPicture.class);
            myPersonPictureList = (List<PersonPicture>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myPersonPictureList;
    }

    public static List<PersonDiploma> findAllDiplomaPerPerson(String personID) {
        List<PersonDiploma> myPersonDiplomaList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from PersonDiploma e where e.personID = '" + personID + "'", PersonDiploma.class);
            myPersonDiplomaList = (List<PersonDiploma>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myPersonDiplomaList;
    }

    public static List<Person> findAllPerson() {
        List<Person> myPersonList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from Person e", Person.class);
            myPersonList = (List<Person>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myPersonList;
    }

    public static List<KnownUnions> findAllKnownUionsPerPerson(String personID) {
        List<KnownUnions> myKnownUnionsList = null;
        try {
            EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query query
                    = myEntityManager.createQuery("select e from KbnownUnions e where e.personID  = '" + personID + "'", KnownUnions.class);
            myKnownUnionsList = (List<KnownUnions>) query.getResultList();
            myEntityManager.close();
            //emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myKnownUnionsList;
    }

    public static int findAllClientsPerTypeQueryCount(Map<String, FilterMeta> supplierMap) {
        int totalRowCount = -1;
        try {
            String clientTypeID = (String) supplierMap.get("clientTypeID").getFilterValue();
            final EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();

            Query queryCountRows
                    = myEntityManager.createQuery("select count(p) "
                            + "from Client p "
                            + "where p.clientType.clientTypeID ='" + clientTypeID + "'"
                    );
            totalRowCount = ((Long) queryCountRows.getSingleResult()).intValue();
            myEntityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalRowCount;
    }

    public static Map<String, FilterMeta> findAllClientsPerTypeQueries(Map<String, FilterMeta> clientTypeMap) {
        try {
            String clientTypeID = (String) clientTypeMap.get("clientTypeID").getFilterValue();
            final EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
            EntityManager myEntityManager = emf.createEntityManager();
            Query queryList
                    = myEntityManager.createQuery("select p "
                            + "from Client p "
                            + "where   p.clientType.clientTypeID  ='" + clientTypeID + "'"
                            + "group by p.clientID order by p.clientID asc ", Client.class
                    );

            Query queryCountRows
                    = myEntityManager.createQuery("select count(p) "
                            + "from Client p "
                            + "where p.clientType.clientTypeID ='" + clientTypeID + "'"
                    );
            int totalRowCount = ((Long) queryCountRows.getSingleResult()).intValue();
            FilterMeta queryListMeta = new FilterMeta();
            queryListMeta.setFilterValue(queryList);
            clientTypeMap.put("queryList", queryListMeta);
            FilterMeta queryCountRowsMeta = new FilterMeta();
            queryCountRowsMeta.setFilterValue(queryCountRows);
            clientTypeMap.put("queryCountRows", queryCountRowsMeta);
            FilterMeta totalRowCountMeta = new FilterMeta();
            totalRowCountMeta.setFilterValue(totalRowCount);
            clientTypeMap.put("totalRowCount", totalRowCountMeta);
            myEntityManager.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientTypeMap;
    }

    //---------------------------------
    public static String convertTime(long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static String convertDate(Date date) {
        Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static String convertShortDate(Date date) {
        Format format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static Timestamp convertTimeStamp(Date date) {
        Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp tmstmp = Timestamp.valueOf(format.format(date));
        System.out.println(tmstmp);
        return tmstmp;
    }

    public static Timestamp convertTimeStamp(String date) {
        Timestamp tmstmp = Timestamp.valueOf(date);
        System.out.println(tmstmp);
        return tmstmp;
    }

    public static Date convertStringToDate(String date) {
        Date convertDate = null;
        try {
            Format format = new SimpleDateFormat("dd-MM-yyyy");
            convertDate = (Date) format.parseObject(date);
            System.out.println(convertDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertDate;
    }

    public static boolean isInteger(String s) {
        return isInteger(s, 10);
    }

    public static boolean isInteger(String s, int radix) {
        if (s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) {
                    return false;
                } else {
                    continue;
                }
            }
            if (Character.digit(s.charAt(i), radix) < 0) {
                return false;
            }
        }
        return true;
    }

    public static <T> T getSingleResult(Query query) {
        query.setMaxResults(1);
        List<T> list = query.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    public static void main(String[] args) {

        EntityManagerFactory emf = DataManager.getInstance().getEntityManagerFactory();
        EntityManager myEntityManager = emf.createEntityManager();
        System.out.println(findClient("CLT_1").getPerson().getLastName());
        List<Account> pmList = findAllAccount();
        System.out.println(pmList.size());
        for (Account pd : pmList) {
            System.out.println(pd.getUserName());
        }
        System.out.println(MainDAO.findGenre("GNR_1"));
        /*Query query
                = myEntityManager.createQuery("select p, prtdt, s  "
                            + "from ProductSupplier ps "
                            + "join ps.product p "
                            + "join ps.supplier s  "
                            + "left join "
                            + " PromotionDetails prtdt "
                            + "inner join prtdt.promotion prt "
                            + "where p.productID=prtdt.product.productID and s.supplierID=prt.supplier.supplierID  group by p.productID, s.supplierID");
        List<Object[]> myResultSet = (List<Object[]>) query.getResultList();*/
        // List<ProductEnhanced> productEnhancedList = findAllPromotedProductEnhanceds();

        //       List<ProductEnhanced> productEnhancedList = findAllProductEnhancedPerDivision("PD_000002");
        //       System.out.println(productEnhancedList.size());
        // System.out.println(findAllPromotedProductEnhanceds());
        // SellDetails sd = findSellDetails("SELL_2", "PRD_00000000001");
        //  List<Employee> employeeList = findAllEmployee();
        //ProductEnhanced pe = findProductEnhanced("PRD_00000000001"); 
        /* Employee emp = findEmployee("EMP_00000001");
        System.out.println(emp.getFirstName() + " " + emp.getLastName() + " " + emp.getMainPhoneNumber());

        for (WaitingTime waitingTime : findAllWaitingTime()) {
            System.out.println(waitingTime.getTimeRange());
       
        List<Product> pdList = findAllProducts();
        for (Product pd : pdList) {
            System.out.println(pd.getName());
        }
        List<PaymentMethod> pmList = findAllPaymentMethod();
        System.out.println(pmList.size());
        for (PaymentMethod pd : pmList) {
            System.out.println(pd.getName());
        }*/
        // findAllBestBuyProductEnhanced(2);
        // findProduct("product1");
        //System.out.println(findAllBillingEthnicGroupPerClient("client2").size());
    }
}
