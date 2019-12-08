/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jerseytutorial.resources;

import com.mycompany.jerseytutorial.models.Customer;
import com.mycompany.jerseytutorial.services.CustomerService;
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
@Path("/customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {
    CustomerService cs = new CustomerService();
    AccountResource as = new AccountResource();

    @POST
    public Customer createCustomer(Customer c){
        return cs.createCustomer(c);   
    }
    
    @GET
    public List<Customer> getCustomers(){
        return cs.getCustomers();
    }
    
    @GET
    @Path("/{email}")
    public Customer getCustomer(@PathParam("email") String email){
        System.out.println(email);
        return cs.getCustomer(email);
    }
    @Path("/{email}/accounts")
    public AccountResource getAccountResource(){
        return new AccountResource();
    }
    
}
