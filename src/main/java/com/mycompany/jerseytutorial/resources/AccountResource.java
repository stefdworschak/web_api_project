/*
 **********************************************************************************************
 *** The codebase comes originally from the MyBlogSolutionWithCommentsAndDBStubFile lab file***
 ********************************************************************************************** 
 */
package com.mycompany.jerseytutorial.resources;

import com.mycompany.jerseytutorial.models.Account;
import com.mycompany.jerseytutorial.models.Customer;
import com.mycompany.jerseytutorial.services.AccountService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author PJMOR
 */
@Path("/accounts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {
    AccountService as = new AccountService();
    
    @GET
    public List<Account> getAccounts(@PathParam("email") String email){
        System.out.println(email);
        return as.getAccounts(email);
    } 
    
    @GET
    @Path("/{accountNo}")
    public Account getAccount(@PathParam("accountNo") long accountNo){
        return as.getAccount(accountNo);
    } 
    
    
    @GET
    @Path("/{accountNo}/balance")
    public String getBalance(@PathParam("accountNo") long accountNo) {
        return as.getBalance(accountNo);
        }
    
    @POST
    public Account addAccount(Customer c){
        return as.addNewAccount(c);
    }
    @Path("/{accountNo}/transactions")
    public TransactionResource getTransactionResource(){
        return new TransactionResource();
    }    
    
}
