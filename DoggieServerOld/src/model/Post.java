package model;

public class Post
{
    private String postId;
    private String userId;
    private String post;
    private String imageURL;
    private int numFavorite;
    private int numComment;
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

    public boolean isDeleted()
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

    public void setDeleted(boolean isDeleted)
    {
            this.isDeleted = isDeleted;
    }
}
