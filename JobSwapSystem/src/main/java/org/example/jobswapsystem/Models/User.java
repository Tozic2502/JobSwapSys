package org.example.jobswapsystem.Models;

import java.util.List;

public class User
{
    private int user_ID;
    private String name;
    private String email;
    private String password;
    private int address_ID;
    private int company_ID;
    private int role_ID;
    private int position_ID;

    private Address address;
    private Role role;
    private Company company;
    private Position position;
    private List<Swap_Req> swap_req;

    public int getUser_ID()
    {
        return user_ID;
    }

    public void setUser_ID(int user_ID)
    {
        this.user_ID = user_ID;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
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

    public int getRole_ID()
    {
        return role_ID;
    }

    public void setRole_ID(int role_ID)
    {
        this.role_ID = role_ID;
    }

    public int getPosition_ID()
    {
        return position_ID;
    }

    public void setPosition_ID(int position_ID)
    {
        this.position_ID = position_ID;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    public Company getCompany()
    {
        return company;
    }

    public void setCompany(Company company)
    {
        this.company = company;
    }

    public Position getPosition()
    {
        return position;
    }

    public void setPosition(Position position)
    {
        this.position = position;
    }

    public List<Swap_Req> getSwap_req()
    {
        return swap_req;
    }

    public void setSwap_req(List<Swap_Req> swap_req)
    {
        this.swap_req = swap_req;
    }
}
