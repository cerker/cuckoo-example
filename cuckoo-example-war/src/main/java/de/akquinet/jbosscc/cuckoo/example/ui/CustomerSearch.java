package de.akquinet.jbosscc.cuckoo.example.ui;

import de.akquinet.jbosscc.cuckoo.example.ejb.CustomerService;
import de.akquinet.jbosscc.cuckoo.example.model.CustomerSearchResult;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.regex.Pattern;

@Named
@SessionScoped
public class CustomerSearch implements Serializable
{
    private static final long serialVersionUID = -2403138958014741653L;

    @EJB
    private CustomerService customerService;

    @Inject
    private CustomerList customerList;

    private String namePattern = "*";

    private int maxRows = 10;

    public int getMaxRows()
    {
        return maxRows;
    }

    public void setMaxRows( int maxRows )
    {
        this.maxRows = maxRows;
    }

    public String getNamePattern()
    {
        return namePattern;
    }

    public void setNamePattern( String namePattern )
    {
        this.namePattern = namePattern;
    }

    public void search()
    {
        CustomerSearchResult result = customerService.search( namePattern, maxRows );

        customerList.setCustomers( result.getCustomers() );

        for ( String sapMessage : result.getReturnMessages() )
        {
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( sapMessage ) );
        }

        String msg = result.getCustomers().size() + " Customer(s) found.";
        FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( msg ) );
    }
}
