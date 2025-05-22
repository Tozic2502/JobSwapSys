package org.example.jobswapsystem.Models;

public class Position_Industry
{
    private int position_Industry_ID;
    private int position_ID;
    private int industry_ID;

    private Position position;
    private Industry industry;

    public int getPosition_Industry_ID()
    {
        return position_Industry_ID;
    }

    public void setPosition_Industry_ID(int position_Industry_ID)
    {
        this.position_Industry_ID = position_Industry_ID;
    }

    public int getPosition_ID()
    {
        return position_ID;
    }

    public void setPosition_ID(int position_ID)
    {
        this.position_ID = position_ID;
    }

    public int getIndustry_ID()
    {
        return industry_ID;
    }

    public void setIndustry_ID(int industry_ID)
    {
        this.industry_ID = industry_ID;
    }

    public Position getPosition()
    {
        return position;
    }

    public void setPosition(Position position)
    {
        this.position = position;
    }

    public Industry getIndustry()
    {
        return industry;
    }

    public void setIndustry(Industry industry)
    {
        this.industry = industry;
    }
}
