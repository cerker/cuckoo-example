package de.akquinet.jbosscc.cuckoo.example.ui;

import de.akquinet.jbosscc.cuckoo.example.model.Customer;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class CustomerList implements Serializable
{
    private static final long serialVersionUID = -2403138958014741653L;

    private final List<Customer> customers = new ArrayList<Customer>();

    public List<Customer> getCustomers()
    {
        return customers;
    }

    public void setCustomers( List<Customer> customers )
    {
        this.customers.clear();
        this.customers.addAll( customers );
    }
}
