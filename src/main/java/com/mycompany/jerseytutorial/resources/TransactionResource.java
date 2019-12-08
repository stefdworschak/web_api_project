/*
 **********************************************************************************************
 *** The codebase comes originally from the MyBlogSolutionWithCommentsAndDBStubFile lab file***
 ********************************************************************************************** 
 */
package com.mycompany.jerseytutorial.resources;

import com.mycompany.jerseytutorial.models.Account;
import com.mycompany.jerseytutorial.models.Transaction;
import com.mycompany.jerseytutorial.services.TransactionService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author PJMOR
 */
@Path("/transactions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TransactionResource {
    
    TransactionService ts = new TransactionService();   
    @GET
    public List<Transaction> getTransactions(@PathParam("email") String email, @PathParam("accountNo") long accountNo){
        return ts.getTransactions(email, accountNo);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/lodgement")
    public Transaction createLodgement(Account a){
        double amount = a.getBalance();
        long creditCardNo = a.getCreditCardNo();
        return ts.createLodgement(amount, creditCardNo);
    }
    @POST
    @Path("/withdrawal")
    public Transaction createWithdrawal(Account a){
        double amount = a.getBalance();
        long creditCardNo = a.getCreditCardNo();
        return ts.createWithdrawal(amount, creditCardNo);
    }
    
    @POST
    public List<Transaction> createTransfer(List<Account> al){
        double amount = al.get(0).getBalance();
        long accountNoFrom = al.get(1).getAccountNo();
        long accountNoTo = al.get(0).getAccountNo();
        return ts.createTransfer(amount, accountNoTo,accountNoFrom);
    }    
    
}
