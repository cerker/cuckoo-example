package de.akquinet.jbosscc.cuckoo.example.ejb;

import de.akquinet.jbosscc.cuckoo.example.model.Customer;
import de.akquinet.jbosscc.cuckoo.example.model.CustomerSearchResult;
import de.akquinet.jbosscc.cuckoo.example.model.ReturnMessages;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.resource.ResourceException;
import javax.resource.cci.Connection;
import javax.resource.cci.ConnectionFactory;
import javax.resource.cci.IndexedRecord;
import javax.resource.cci.Interaction;
import javax.resource.cci.MappedRecord;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class CustomerServiceBean implements CustomerService
{
    private static final Logger LOGGER = Logger.getLogger( CustomerServiceBean.class.getName() );

    @Resource( mappedName = "java:jboss/eis/NSP" )
    private ConnectionFactory connectionFactory;

    public CustomerSearchResult search( String nameSearchPattern, int maxRows )
    {
        Connection connection = null;
        Interaction interaction = null;

        try
        {
            // Fill in the input parameters that will be passed on to SAP
            MappedRecord input = connectionFactory.getRecordFactory().createMappedRecord( "BAPI_FLCUST_GETLIST" );
            input.put( "CUSTOMER_NAME", nameSearchPattern.replace( '?', '+' ) );
            input.put( "MAX_ROWS", maxRows );

            connection = connectionFactory.getConnection();
            interaction = connection.createInteraction();

            // Actually call the SAP remote function module
            MappedRecord output = ( MappedRecord ) interaction.execute( null, input );

            // Read in the data that was returned by the function module
            CustomerSearchResult result = new CustomerSearchResult();

            IndexedRecord returnTable = ( IndexedRecord ) output.get( "RETURN" );
            for ( Object row : returnTable )
            {
                MappedRecord record = ( MappedRecord ) row;
                result.addReturnMessage( ( String ) record.get( "MESSAGE" ) );
            }

            IndexedRecord customerTable = ( IndexedRecord ) output.get( "CUSTOMER_LIST" );
            for ( Object row : customerTable )
            {
                MappedRecord record = ( MappedRecord ) row;

                Customer customer = new Customer();
                customer.setCity( ( String ) record.get( "CITY" ) );
                customer.setCountryKeyIso( ( String ) record.get( "COUNTR_ISO" ) );
                customer.setEmail( ( String ) record.get( "EMAIL" ) );
                customer.setFormOfAddress( ( String ) record.get( "FORM" ) );
                customer.setId( ( String ) record.get( "CUSTOMERID" ) );
                customer.setName( ( String ) record.get( "CUSTNAME" ) );
                customer.setPhoneNumber( ( String ) record.get( "PHONE" ) );
                customer.setPoBox( ( String ) record.get( "POBOX" ) );
                customer.setPostalCode( ( String ) record.get( "POSTCODE" ) );
                customer.setRegion( ( String ) record.get( "REGION" ) );
                customer.setStreet( ( String ) record.get( "STREET" ) );
                result.addCustomer( customer );
            }
            return result;
        }
        catch ( ResourceException e )
        {
            String message = "Error while talking to SAP";
            LOGGER.log( Level.SEVERE, message, e );
            throw new RuntimeException( message, e );
        }
        finally
        {
            closeInteraction( interaction );
            closeConnection( connection );
        }
    }

    public ReturnMessages store( Customer customer )
    {
        LOGGER.info( "Storing customer " + customer );

        Connection connection = null;
        Interaction interaction = null;

        try
        {
            // Fill in the input parameters that will be passed on to SAP
            MappedRecord input = connectionFactory.getRecordFactory().createMappedRecord( "BAPI_FLCUST_CHANGE" );
            input.put( "CUSTOMERNUMBER", customer.getId() );

            MappedRecord data = connectionFactory.getRecordFactory().createMappedRecord( null );
            input.put( "CUSTOMER_DATA", data );

            MappedRecord dataX = connectionFactory.getRecordFactory().createMappedRecord( null );
            input.put( "CUSTOMER_DATA_X", dataX );

            data.put( "CITY", customer.getCity() );
            dataX.put( "CITY", "X" );
            data.put( "COUNTR_ISO", customer.getCountryKeyIso() );
            dataX.put( "COUNTR_ISO", "X" );
            data.put( "EMAIL", customer.getEmail() );
            dataX.put( "EMAIL", "X" );
            data.put( "FORM", customer.getFormOfAddress() );
            dataX.put( "FORM", "X" );
            data.put( "CUSTNAME", customer.getName() );
            dataX.put( "CUSTNAME", "X" );
            data.put( "PHONE", customer.getPhoneNumber() );
            dataX.put( "PHONE", "X" );
            data.put( "POBOX", customer.getPoBox() );
            dataX.put( "POBOX", "X" );
            data.put( "POSTCODE", customer.getPostalCode() );
            dataX.put( "POSTCODE", "X" );
            data.put( "REGION", customer.getRegion() );
            dataX.put( "REGION", "X" );
            data.put( "STREET", customer.getStreet() );
            dataX.put( "STREET", "X" );

            connection = connectionFactory.getConnection();
            interaction = connection.createInteraction();

            // Actually call the SAP remote function module
            MappedRecord output = ( MappedRecord ) interaction.execute( null, input );

            // Read in the necessary data that was returned by the function module
            ReturnMessages result = new ReturnMessages();

            IndexedRecord returnTable = ( IndexedRecord ) output.get( "RETURN" );
            for ( Object row : returnTable )
            {
                MappedRecord record = ( MappedRecord ) row;
                String message = ( String ) record.get( "MESSAGE" );
                String type = ( String ) record.get( "TYPE" );
                result.addReturnMessage( message, ReturnMessages.Severity.fromSapType( type.charAt( 0 ) ) );
            }

            return result;
        }
        catch ( ResourceException e )
        {
            String message = "Error while talking to SAP";
            LOGGER.log( Level.SEVERE, message, e );
            throw new RuntimeException( message, e );
        }
        finally
        {
            closeInteraction( interaction );
            closeConnection( connection );
        }
    }

    private void closeConnection( Connection connection )
    {
        if ( connection != null )
        {
            try
            {
                connection.close();
            }
            catch ( ResourceException closeException )
            {
                LOGGER.log( Level.WARNING,
                        "Error closing the connection. " +
                                "Open connections might remain in the connection pool" +
                                " which results in the connection pool being exhausted.",
                        closeException );
            }
        }
    }

    private void closeInteraction( Interaction interaction )
    {
        if ( interaction != null )
        {
            try
            {
                interaction.close();
            }
            catch ( ResourceException closeException )
            {
                LOGGER.log( Level.WARNING, "Error closing the interaction.", closeException );
            }
        }
    }
}
