package org.example.jobswapsystem.Models;

import java.util.List;

public class Company
{
    private int company_ID;
    private String name;

    private List<Adr_Comp> address_comps;

    public int getCompany_ID()
    {
        return company_ID;
    }

    public void setCompany_ID(int company_ID)
    {
        this.company_ID = company_ID;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Adr_Comp> getAddress_comps()
    {
        return address_comps;
    }

    public void setAddress_comps(List<Adr_Comp> address_comps)
    {
        this.address_comps = address_comps;
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
