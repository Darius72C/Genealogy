/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.webServices;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import java.util.Date;
import java.util.List;
import liten.genealogy.mainEntities.Account;
import liten.genealogy.managers.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bayomock.a
 */
@RestController
public class AccountWS {

    @Autowired
    private AccountManager accountManager;

    public AccountWS() {

    }
    
    public AccountManager getAccountManager(){
        return this.accountManager;
    }
    
    @GetMapping(path = "/account/getAccountList")
    /* public List<Account> getAccountList() {
        return List.of(
                new Account("ACCT_1", "acbayo", "geogeo", new Date()),
                new Account("ACCT_2", "mbidmi", "maololo", new Date()));

    }*/

    public List<Account> findAllAccount() {
        return accountManager.findAllAccount();
    }

}
