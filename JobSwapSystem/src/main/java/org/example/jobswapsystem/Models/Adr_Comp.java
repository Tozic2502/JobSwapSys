package org.example.jobswapsystem.Models;

import java.util.List;

public class Adr_Comp
{
    private int adr_comp_ID;
    private int address_ID;
    private int company_ID;

    private Address address;
    private Company company;

    public int getAdr_comp_ID()
    {
        return adr_comp_ID;
    }

    public void setAdr_comp_ID(int adr_comp_ID)
    {
        this.adr_comp_ID = adr_comp_ID;
    }

    public int getAddress_ID()
    {
        return address_ID;
    }

    public void setAddress_ID(int address_ID)
    {
        this.address_ID = address_ID;
    }

    public int getCompany_ID()
    {
        return company_ID;
    }

    public void setCompany_ID(int company_ID)
    {
        this.company_ID = company_ID;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public Company getCompany()
    {
        return company;
    }

    public void setCompany(Company company)
    {
        this.company = company;
    }
}
