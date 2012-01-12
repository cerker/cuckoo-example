package de.akquinet.jbosscc.cuckoo.example.model;

import java.io.Serializable;

public class Customer implements Serializable
{
    // CUSTOMERID
    private String id;

    // CUSTNAME
    private String name;

    // FORM
    private String formOfAddress;

    // STREET
    private String street;

    // POBOX
    private String poBox;

    // POSTCODE
    private String postalCode;

    // CITY
    private String city;

    // COUNTR_ISO
    private String countryKeyIso;

    // REGION
    private String region;

    // PHONE
    private String phoneNumber;

    // EMAIL
    private String email;

    public String getCity()
    {
        return city;
    }

    public void setCity( String city )
    {
        this.city = city;
    }

    public String getCountryKeyIso()
    {
        return countryKeyIso;
    }

    public void setCountryKeyIso( String countryKeyIso )
    {
        this.countryKeyIso = countryKeyIso;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getFormOfAddress()
    {
        return formOfAddress;
    }

    public void setFormOfAddress( String formOfAddress )
    {
        this.formOfAddress = formOfAddress;
    }

    public String getId()
    {
        return id;
    }

    public void setId( String id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber( String phoneNumber )
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPoBox()
    {
        return poBox;
    }

    public void setPoBox( String poBox )
    {
        this.poBox = poBox;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode( String postalCode )
    {
        this.postalCode = postalCode;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion( String region )
    {
        this.region = region;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet( String street )
    {
        this.street = street;
    }

    @Override
    public String toString()
    {
        return "Customer{" +
                "city='" + city + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", formOfAddress='" + formOfAddress + '\'' +
                ", street='" + street + '\'' +
                ", poBox='" + poBox + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", countryKeyIso='" + countryKeyIso + '\'' +
                ", region='" + region + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
