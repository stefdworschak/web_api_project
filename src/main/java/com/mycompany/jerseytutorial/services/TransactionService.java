/*
 **********************************************************************************************
 *** The codebase comes originally from the MyBlogSolutionWithCommentsAndDBStubFile lab file***
 ********************************************************************************************** 
 */
package com.mycompany.jerseytutorial.services;

import com.mycompany.jerseytutorial.database.Database;
import com.mycompany.jerseytutorial.models.Account;
import com.mycompany.jerseytutorial.models.Customer;
import com.mycompany.jerseytutorial.models.NumberGen;
import com.mycompany.jerseytutorial.models.Transaction;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Transaction n = new Transaction("Credit", "Lodgement", 0.0);
        t_list.add(n);
        Account a = null;
        for(int i =0; i<list.size(); i++){
            a = (Account) list.get(i);
            if(a.getCreditCardNo() == creditCardNo){
                List<Transaction> t = a.getTransactions();
                n.setPostTransBalance(a.getBalance() + amount);
                t.add(n);
                a.setBalance(a.getBalance()+ amount);
                a.setTransactions(t);
                list.set(i, a);
            }
        }
        
        for(int j =0; j< c_list.size(); j++){
            Customer c = (Customer) c_list.get(j);
            for(int k = 0; k < c.getAccounts().size(); k++){
                Account a2 = (Account) c.getAccounts().get(k);
                if(a2.getAccountNo() == a.getAccountNo()){
                    List<Account> c_account = c.getAccounts();
                    c_account.set(k, a);
                    c.setAccounts(c_account);
                    c_list.set(j, c);
                    return n;
                }
            }
        }
    
        return n;
    }    
public Transaction createWithdrawal(double amount, long creditCardNo){
    Transaction n = new Transaction("Debit", "Withdrawal", 0);
        t_list.add(n);
        Account a = null;
        for(int i =0; i<list.size(); i++){
            a = (Account) list.get(i);
            System.out.println(a.getCreditCardNo());
            System.out.println(creditCardNo);   
            if(a.getCreditCardNo() == creditCardNo){
                List<Transaction> t = a.getTransactions();
                n.setPostTransBalance(a.getBalance() - amount);
                t.add(n);
                a.setBalance(a.getBalance() - amount);
                a.setTransactions(t);
                list.set(i, a);
            }
        }
        for(int j =0; j< c_list.size(); j++){
            Customer c = (Customer) c_list.get(j);
            for(int k =0; k < c.getAccounts().size(); k++){
                Account a2 = (Account) c.getAccounts().get(k);
                if(a2.getAccountNo() == a.getAccountNo()){
                    List<Account> c_account = c.getAccounts();
                    c_account.set(k, a);
                    c.setAccounts(c_account);
                    c_list.set(j, c);
                }
            }
        }
    
        return n;
    } 
    public List<Transaction> createTransfer(double amount, long accountNoTo,long accountNoFrom){
        long creditCardNoFrom = this.findCreditCardFromAccount(accountNoFrom);
        long creditCardNoTo = this.findCreditCardFromAccount(accountNoTo);
        List<Transaction> tl = new ArrayList<>();
        
        if(creditCardNoFrom !=0 && creditCardNoTo !=0){
            Transaction from = this.createWithdrawal(amount, creditCardNoFrom);
            Transaction to = this.createLodgement(amount, creditCardNoTo);
            tl.add(to);
            tl.add(from);
            return tl;
        }
        return tl;
    }
    public long findCreditCardFromAccount(long accountNo){
        for(int i = 0; i<list.size(); i++){
            Account a = list.get(i);
            if((long) a.getAccountNo() == accountNo){
                return a.getCreditCardNo();
            }         
        }
        return 0;
    }
    
    public List<Transaction> getTransactions(String email,long accountNo){
        AccountService as = new AccountService();
        CustomerService cs = new CustomerService();
        Account a = as.getAccount(accountNo);
        Customer c = cs.getCustomer(email);
        if(a!=null && c!=null){
            return a.getTransactions();
        }
        List<Transaction> tl = new ArrayList<>();
        return tl;
        
    }    
}
