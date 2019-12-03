/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jerseytutorial.models;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import static org.eclipse.persistence.expressions.ExpressionOperator.any;

/**
 *
 * @author PJMOR
 */
@XmlRootElement
public class Customer {
    
    private String name;
    private String address;
    private String email;
    private String credentials;
    private int c_id;
    private List<Account> accounts; 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Customer(String name, String address, String email, String credentials, int c_id, List<Account> accounts) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.credentials = credentials;
        this.c_id = c_id;
        this.accounts = accounts;
    }
    

    public Customer() {
        
    }
    
}
