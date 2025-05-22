package org.example.jobswapsystem.Models;

import java.util.ArrayList;
import java.util.List;

public class Position
{
    private int position_ID;
    private String job_Title;

    private List<Position_Industry> position_IndustryList = new ArrayList<Position_Industry>();
    private User user;

    public int getPosition_ID()
    {
        return position_ID;
    }

    public void setPosition_ID(int position_ID)
    {
        this.position_ID = position_ID;
    }

    public String getJob_Title()
    {
        return job_Title;
    }

    public void setJob_Title(String job_Title)
    {
        this.job_Title = job_Title;
    }

    public List<Position_Industry> getPosition_IndustryList()
    {
        return position_IndustryList;
    }

    public void setPosition_IndustryList(List<Position_Industry> position_IndustryList)
    {
        this.position_IndustryList = position_IndustryList;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
