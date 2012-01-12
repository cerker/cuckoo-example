package de.akquinet.jbosscc.cuckoo.example.ui;

import de.akquinet.jbosscc.cuckoo.example.ejb.CustomerService;
import de.akquinet.jbosscc.cuckoo.example.model.Customer;
import de.akquinet.jbosscc.cuckoo.example.model.CustomerStoreResult;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@SuppressWarnings( {"UnusedDeclaration"} )
@Named
@SessionScoped
public class CustomerEdit implements Serializable
{
    @EJB
    private CustomerService customerService;

    @Inject
    private CustomerSearch customerSearch;

    private Customer customer;

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer( Customer customer )
    {
        this.customer = customer;
    }

    public void store()
    {
        CustomerStoreResult result = customerService.store( customer );

        for ( String sapMessage : result.getReturnMessages() )
        {
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( sapMessage ) );
        }

        // Update the list of customers, which may or may not have been changed
        customerSearch.search();
    }
}
