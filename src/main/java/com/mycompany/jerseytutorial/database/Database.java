/*
 **********************************************************************************************
 *** The codebase comes originally from the MyBlogSolutionWithCommentsAndDBStubFile lab file***
 ********************************************************************************************** 
 */
package com.mycompany.jerseytutorial.database;

import com.mycompany.jerseytutorial.models.Account;
import com.mycompany.jerseytutorial.models.Customer;
import com.mycompany.jerseytutorial.models.Transaction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PJMOR
 */
public class Database {

    public static List<Customer> getCustomerDB() {
        return customerDB;
    }

    public static List<Account> getAccountDB() {
        return accountDB;
    }

    public static List<Transaction> getTransactionDB() {
        return transactionDB;
    }
    public static List<Customer> customerDB = new ArrayList<>();
    public static List<Account> accountDB = new ArrayList<>();
    public static List<Transaction> transactionDB = new ArrayList<>();
    public static boolean init = true;

    public Database() {
        if(init){
            
            Transaction t1 = new Transaction("Debit", "Rent", 450.00);
            Transaction t2 = new Transaction("Credit", "Salary", 2100.50);
            Transaction t3 = new Transaction("Credit", "Refund", 15.00);
            Transaction t4 = new Transaction("Debit", "Restaurant", 23.20);
            Transaction t5 = new Transaction("Credit", "Interest", 7.00);
            Transaction t6 = new Transaction("Debit", "Bills", 50.00);
            
            transactionDB.add(t1);
            transactionDB.add(t2);
            transactionDB.add(t3);
            transactionDB.add(t4);
            transactionDB.add(t5);
            transactionDB.add(t6);
            
            
            Account a1 = new Account(1979, 4567, 5000,1234567,transactionDB);
            Account a2 = new Account(1980, 5678, 5000,2345678,transactionDB);
            Account a3 = new Account(1981, 6789, 5000,3456789,transactionDB);
            Account a4 = new Account(1982, 7890, 5000,4567890,transactionDB);
            Account a5 = new Account(1983, 8901, 5000,5678901,transactionDB);
            Account a6 = new Account(1984, 9012, 5000,6789012,transactionDB);
            
            accountDB.add(a1);
            accountDB.add(a2);
            accountDB.add(a3);
            accountDB.add(a4);
            accountDB.add(a5);
            accountDB.add(a6);

            Customer c1 = new Customer ("Stefan Dworschak", "1 Mayor Square, Dublin","sd@yahoo.ie", "Passport", customerDB.size()+1, accountDB);
            Customer c2 = new Customer ("Patrick Moran", "10 Dublin Road, Meath","pm@yahoo.ie", "Licence", customerDB.size()+1, accountDB);
            Customer c3 = new Customer ("John Hughes", "5 High Street, Galway","jh@yahoo.ie", "Passport", customerDB.size()+1, accountDB);
            Customer c4 = new Customer ("Karen Smith", "51 Patrick Street, Cork","ks@yahoo.ie", "Licence", customerDB.size()+1, accountDB);
            Customer c5 = new Customer ("Mary Byrne", "6 Collins Ave, Dublin","mb@yahoo.ie", "Passport", customerDB.size()+1, accountDB);
            Customer c6 = new Customer ("Paul Peters", "Kilbride Manor, Wicklow","pp@yahoo.ie", "Licence", customerDB.size()+1, accountDB);
            
            customerDB.add(c1);
            customerDB.add(c2);
            customerDB.add(c3);
            customerDB.add(c4);
            customerDB.add(c5);
            customerDB.add(c6);
            
            init = false;
            
            
            
            
        }
    }
    
    
}
