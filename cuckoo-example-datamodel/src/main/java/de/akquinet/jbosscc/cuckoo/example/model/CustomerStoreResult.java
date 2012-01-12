package de.akquinet.jbosscc.cuckoo.example.model;

import java.util.ArrayList;
import java.util.List;

public class CustomerStoreResult
{
    private final List<String> returnMessages = new ArrayList<String>(  );

    public List<String> getReturnMessages()
    {
        return returnMessages;
    }

    public void addReturnMessage(String message)
    {
        returnMessages.add( message );
    }
}
