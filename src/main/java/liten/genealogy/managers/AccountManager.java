/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.managers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import liten.genealogy.mainEntities.Account;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import liten.genealogy.utilitiesDB.DataManager;
import liten.genealogy.utilitiesDB.KeyGeneration;
import liten.genealogy.utilitiesDB.MainDAO;

/**
 *
 * @author FACULTY
 */
@Named
@RequestScoped
@Service
public class AccountManager implements Serializable {

    public AccountManager() {

    }

    public Account addNewAccount(Account account) {
        Account accountDB = null;
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            if (account != null && account.getAccountID() == null && account.getUserName()!=null && account.getPassword() !=null) {
                String accountID = KeyGeneration.generatePrimaryKey(Class.forName("mainEntities.Account"));
                account.setAccountID(accountID);
            }
            accountDB = connectionServiceFacade.mergeEntity(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountDB;
    }

    public Account updateAccount(Account account) {
        Account accountDB=null;
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            accountDB = MainDAO.findAccount(account.getAccountID());
            if (accountDB != null) {
                account = connectionServiceFacade.mergeEntity(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountDB;
    }

    public Account findAccount(String accountID) {
        Account account = null;
        try {
            account = MainDAO.findAccount(accountID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    public Account findAccountPerUsername(String userName) {
        return MainDAO.findAccountPerUsername(userName);
    }

    public Account findAccountPerCredentials(String userName, String password) {
        return MainDAO.findAccountPerCredentials(userName, password);
    }

    public List<Account> findAllAccount() {
        return MainDAO.findAllAccount();

    }

}
