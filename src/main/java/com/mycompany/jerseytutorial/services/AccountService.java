/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jerseytutorial.services;

import com.mycompany.jerseytutorial.database.Database;
import com.mycompany.jerseytutorial.models.Account;
import com.mycompany.jerseytutorial.models.Customer;
import com.mycompany.jerseytutorial.models.NumberGen;
import com.mycompany.jerseytutorial.models.Transaction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PJMOR
 */
public class AccountService {
    Database db = new Database();
    private List<Account> list = db.getAccountDB();
    private List<Customer> c_list = db.getCustomerDB();
    NumberGen gen = new NumberGen();
    
    
    public Account createAccount(){
        List <Transaction> t = new ArrayList<>();
        Account a = new Account(1979, gen.randomInt(4), 100.0,gen.randomLong(9),t);
        list.add(a);
        return a;
    }
    
    public Account addNewAccount(String email){
        Account a = this.createAccount();
        for(int i=0;i <c_list.size(); i++){
            Customer c = (Customer) c_list.get(i);
            if(c.getEmail()== email){
                List<Account> c_account = c.getAccounts();
                c_account.add(a);
                c.setAccounts(c_account);
                c_list.set(i, c);
            }
        }
        return a;
    }
    
    
}
