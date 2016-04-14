package model;

public class Post
{
    private String postId;
    private String userId;
    private String username;
    private String post;
    private String imageURL;
    private int numFavorite;
    private int numComment;
    private double latitude;
    private double longitude;
    private boolean isDeleted;

    public Post()
    {

    }

    public String getPostId()
    {
	return postId;
    }

    public String getUserId()
    {
	return userId;
    }
    
    public String getUsername()
    {
	return username;
    }
    
    public String getPost()
    {
	return post;
    }
    
    public String getImageURL()
    {
	return imageURL;
    }
    
    public int getNumFavorite()
    {
	return numFavorite;
    }
    
    public int getNumComment()
    {
	return numComment;
    }
    
    public double getLatitude()
    {
	return latitude;
    }
    
    public double getLongitude()
    {
	return longitude;
    }
    
    public boolean isIsDeleted()
    {
	return isDeleted;
    }
    
    public void setPostId(String postId)
    {
	this.postId = postId;
    }

    public void setUserId(String userId)
    {
	this.userId = userId;
    }

    public void setUsername(String username)
    {
	this.username = username;
    }

    public void setPost(String post)
    {
	this.post = post;
    }

    public void setImageURL(String imageURL)
    {
	this.imageURL = imageURL;
    }

    public void setNumFavorite(int numFavorite)
    {
	this.numFavorite = numFavorite;
    }

    public void setNumComment(int numComment)
    {
	this.numComment = numComment;
    }

    public void setLatitude(double latitude)
    {
	this.latitude = latitude;
    }

    public void setLongitude(double longitude)
    {
	this.longitude = longitude;
    }

    public void setIsDeleted(boolean isDeleted)
    {
	this.isDeleted = isDeleted;
    }    
}
