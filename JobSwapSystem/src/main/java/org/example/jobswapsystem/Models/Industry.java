package org.example.jobswapsystem.Models;

import java.util.ArrayList;
import java.util.List;

public class Industry
{
    private int industry_ID;
    private String name;

    private List<Position_Industry> position_industries = new ArrayList<Position_Industry>();

    public int getIndustry_ID()
    {
        return industry_ID;
    }

    public void setIndustry_ID(int industry_ID)
    {
        this.industry_ID = industry_ID;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Position_Industry> getPosition_industries()
    {
        return position_industries;
    }

    public void setPosition_industries(List<Position_Industry> position_industries)
    {
        this.position_industries = position_industries;
    }
}
