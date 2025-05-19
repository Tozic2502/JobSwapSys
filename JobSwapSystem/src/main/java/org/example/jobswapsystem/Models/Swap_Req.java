package org.example.jobswapsystem.Models;

public class Swap_Req
{
    private int swap_Req_ID;
    private int user1_ID;
    private int user2_ID;
    private boolean user2_Accept;

    private User user1;
    private User user2;

    public int getSwap_Req_ID()
    {
        return swap_Req_ID;
    }

    public void setSwap_Req_ID(int swap_Req_ID)
    {
        this.swap_Req_ID = swap_Req_ID;
    }

    public int getUser1_ID()
    {
        return user1_ID;
    }

    public void setUser1_ID(int user1_ID)
    {
        this.user1_ID = user1_ID;
    }

    public int getUser2_ID()
    {
        return user2_ID;
    }

    public void setUser2_ID(int user2_ID)
    {
        this.user2_ID = user2_ID;
    }

    public boolean isUser2_Accept()
    {
        return user2_Accept;
    }

    public void setUser2_Accept(boolean user2_Accept)
    {
        this.user2_Accept = user2_Accept;
    }

    public User getUser1()
    {
        return user1;
    }

    public void setUser1(User user1)
    {
        this.user1 = user1;
    }

    public User getUser2()
    {
        return user2;
    }

    public void setUser2(User user2)
    {
        this.user2 = user2;
    }
}
