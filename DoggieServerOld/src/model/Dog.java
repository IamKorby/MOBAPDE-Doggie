package model;

public class Dog
{
    private String dogId;
    private String userId;
    private String name;
    private String pictureURL;
    private boolean hasPaper;
    private boolean isStray;
    private boolean isLost;
    private boolean isStud;
    private boolean isAdopt;

    public Dog()
    {

    }

    public String getDogId()
    {
            return dogId;
    }

    public String getUserId()
    {
            return userId;
    }

    public String getName()
    {
            return name;
    }

    public String getPictureURL()
    {
            return pictureURL;
    }

    public boolean isHasPaper()
    {
            return hasPaper;
    }

    public boolean isStray()
    {
            return isStray;
    }

    public boolean isLost()
    {
            return isLost;
    }

    public boolean isStud()
    {
            return isStud;
    }

    public boolean isAdopt()
    {
            return isAdopt;
    }

    public void setDogId(String dogId)
    {
            this.dogId = dogId;
    }

    public void setUserId(String userId)
    {
            this.userId = userId;
    }

    public void setName(String name)
    {
            this.name = name;
    }

    public void setPictureURL(String pictureURL)
    {
            this.pictureURL = pictureURL;
    }

    public void setHasPaper(boolean hasPaper)
    {
            this.hasPaper = hasPaper;
    }

    public void setStray(boolean isStray)
    {
            this.isStray = isStray;
    }

    public void setLost(boolean isLost)
    {
            this.isLost = isLost;
    }

    public void setStud(boolean isStud)
    {
            this.isStud = isStud;
    }

    public void setAdopt(boolean isAdopt)
    {
            this.isAdopt = isAdopt;
    }
}
