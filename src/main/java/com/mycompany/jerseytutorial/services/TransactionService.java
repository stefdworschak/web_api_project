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
import java.util.List;

/**
 *
 * @author PJMOR
 */
public class TransactionService {
 Database db = new Database();
    private List<Account> list = db.getAccountDB();
    private List<Customer> c_list = db.getCustomerDB();
    private List<Transaction> t_list = db.getTransactionDB();
    NumberGen gen = new NumberGen();
  

public Transaction createLodgement(double amount, long creditCardNo){
    Transaction n = new Transaction("Credit", "Lodgement", amount);
        t_list.add(n);
        Account a = null;
        for(int i =0; i<list.size(); i++){
            a = (Account) list.get(i);
            if(a.getCreditCardNo()== creditCardNo){
                List<Transaction> t = a.getTransactions();
                t.add(n);
                a.setBalance(a.getBalance()+ amount);
                a.setTransactions(t);
                list.set(i, a);
            }
        }
        for(int j =0; j< c_list.size(); j++){
            Customer c = (Customer) c_list.get(j);
            for(int k =0; j< c.getAccounts().size(); k++){
                Account a2 = (Account) c.getAccounts().get(k);
                if(a2.getAccountNo() == a.getAccountNo()){
                    List<Account> c_account = c.getAccounts();
                        c_account.add(a);
                        c.setAccounts(c_account);
                        c_list.set(j, c);
                }
            }
        }
    
        return n;
    }    
    
}
