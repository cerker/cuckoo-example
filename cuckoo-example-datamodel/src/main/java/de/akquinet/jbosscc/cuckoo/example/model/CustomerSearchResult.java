package de.akquinet.jbosscc.cuckoo.example.model;

import java.util.ArrayList;
import java.util.List;

public class CustomerSearchResult
{
    private final List<Customer> customers = new ArrayList<Customer>(  );

    private final List<String> returnMessages = new ArrayList<String>(  );

    public List<Customer> getCustomers()
    {
        return customers;
    }

    public List<String> getReturnMessages()
    {
        return returnMessages;
    }

    public void addCustomer(Customer customer)
    {
        customers.add( customer );
    }

    public void addReturnMessage(String message)
    {
        returnMessages.add( message );
    }
}
