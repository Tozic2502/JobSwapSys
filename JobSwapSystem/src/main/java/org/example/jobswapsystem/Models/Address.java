package org.example.jobswapsystem.Models;

import java.util.List;

public class Address
{
    private int Address_ID;
    private String potalCode;
    private String address;
    private String city;

    private List<Adr_Comp> company_addresses;
    private List<User> users;

    public int getAddress_ID()
    {
        return Address_ID;
    }

    public void setAddress_ID(int address_ID)
    {
        Address_ID = address_ID;
    }

    public String getPotalCode()
    {
        return potalCode;
    }

    public void setPotalCode(String potalCode)
    {
        this.potalCode = potalCode;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public List<Adr_Comp> getCompany_addresses()
    {
        return company_addresses;
    }

    public void setCompany_addresses(List<Adr_Comp> company_addresses)
    {
        this.company_addresses = company_addresses;
    }

    public List<User> getUsers()
    {
        return users;
    }

    public void setUsers(List<User> users)
    {
        this.users = users;
    }
}
