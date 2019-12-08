/*
 **********************************************************************************************
 *** The codebase comes originally from the MyBlogSolutionWithCommentsAndDBStubFile lab file***
 ********************************************************************************************** 
 */
package com.mycompany.jerseytutorial.models;

import java.util.Date;

/**
 *
 * @author PJMOR
 */
public class Transaction {
    
    private String type;
    private Date transactionDate;
    private String description;
    private double postTransBalance;

    public Transaction(String type, String description, double postTransBalance) {
        this.type = type;
        this.transactionDate = new Date();
        this.description = description;
        this.postTransBalance = postTransBalance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPostTransBalance() {
        return postTransBalance;
    }

    public void setPostTransBalance(double postTransBalance) {
        this.postTransBalance = postTransBalance;
    }
    

    public Transaction() {
    }
   
    
}
