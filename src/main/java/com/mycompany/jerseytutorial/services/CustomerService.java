/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jerseytutorial.services;

import com.mycompany.jerseytutorial.database.Database;
import com.mycompany.jerseytutorial.models.Account;
import com.mycompany.jerseytutorial.models.Customer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PJMOR
 */
public class CustomerService {
    Database db = new Database();
    private List<Customer> list = db.getCustomerDB();
    private AccountService accountService = new AccountService();
    
    
    public Customer createCustomer(Customer c){
        c.setC_id(list.size()+1);
        
        Account a = accountService.createAccount();
        List<Account> accounts = new ArrayList<>();
        accounts.add(a);
        c.setAccounts(accounts);
        list.add(c);        
       return c;
    }
    
    public Customer getCustomer(String email){
        for(int i =0; i<list.size(); i++){
            if(list.get(i).getEmail().equalsIgnoreCase(email)){
                return list.get(i);
            }
        }
        return null;
    }
   
   
}
