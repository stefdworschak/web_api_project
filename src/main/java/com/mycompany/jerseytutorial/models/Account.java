/*
 **********************************************************************************************
 *** The codebase comes originally from the MyBlogSolutionWithCommentsAndDBStubFile lab file***
 ********************************************************************************************** 
 */
package com.mycompany.jerseytutorial.models;

import java.util.List;
import static org.eclipse.persistence.expressions.ExpressionOperator.any;

/**
 *
 * @author PJMOR
 */
public class Account {
    
    private int sortCode;
    private long accountNo;
    private double balance;
    private long creditCardNo;

    public long getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(long creditCardNo) {
        this.creditCardNo = creditCardNo;
    }
    private List<Transaction> transactions;

    public int getSortCode() {
        return sortCode;
    }

    public void setSortCode(int sortCode) {
        this.sortCode = sortCode;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Account(int sortCode, long accountNo, double balance, long creditCardNo, List<Transaction> transactions) {
        this.sortCode = sortCode;
        this.accountNo = accountNo;
        this.balance = balance;
        this.creditCardNo= creditCardNo;
        this.transactions = transactions;
    }
    

    public Account() {
    }
    
}
