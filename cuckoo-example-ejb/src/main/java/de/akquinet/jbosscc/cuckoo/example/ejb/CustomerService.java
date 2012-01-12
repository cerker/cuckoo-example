package de.akquinet.jbosscc.cuckoo.example.ejb;

import de.akquinet.jbosscc.cuckoo.example.model.Customer;
import de.akquinet.jbosscc.cuckoo.example.model.CustomerSearchResult;
import de.akquinet.jbosscc.cuckoo.example.model.CustomerStoreResult;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CustomerService
{
    CustomerSearchResult search( String nameSearchPattern, int maxRows );

    CustomerStoreResult store( Customer customer );
}
