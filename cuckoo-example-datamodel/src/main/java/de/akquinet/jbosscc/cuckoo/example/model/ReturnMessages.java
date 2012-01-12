package de.akquinet.jbosscc.cuckoo.example.model;

import java.util.ArrayList;
import java.util.List;

public class ReturnMessages
{
    private final List<ReturnMessage> returnMessages = new ArrayList<ReturnMessage>();

    public List<ReturnMessage> getReturnMessages()
    {
        return returnMessages;
    }

    public void addReturnMessage( String message, Severity severity )
    {
        returnMessages.add( new ReturnMessage( message, severity ) );
    }

    public static class ReturnMessage
    {
        private String message;
        private Severity severity;

        public ReturnMessage( String message, Severity severity )
        {
            this.message = message;
            this.severity = severity;
        }

        public String getMessage()
        {
            return message;
        }

        public Severity getSeverity()
        {
            return severity;
        }
    }

    public enum Severity
    {
        INFO( 'I' ), SUCCESS( 'S' ), WARNING( 'W' ), ERROR( 'E' ), ABORT( 'A' ), OTHER( ' ' );

        private char sapType;

        private Severity( char sapType )
        {
            this.sapType = sapType;
        }

        public static Severity fromSapType( char sapType )
        {
            for ( Severity severity : Severity.values() )
            {
                if ( severity.sapType == sapType )
                {
                    return severity;
                }
            }
            return OTHER;
        }
    }
}
