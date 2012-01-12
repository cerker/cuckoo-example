package de.akquinet.jbosscc.cuckoo.example.ejb;

import de.akquinet.jbosscc.cuckoo.example.model.Customer;
import de.akquinet.jbosscc.cuckoo.example.model.CustomerSearchResult;
import de.akquinet.jbosscc.cuckoo.example.model.ReturnMessages;

import javax.ejb.Local;

@Local
public interface CustomerService
{
    CustomerSearchResult search( String nameSearchPattern, int maxRows );

    ReturnMessages store( Customer customer );
}
