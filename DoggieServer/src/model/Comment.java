package model;

import java.sql.Date;

public class Comment
{
	private String commentId;
	private String userId;
	private String postId;
	private String comment;
	private Date dateTime;
	
	public Comment()
	{
		
	}
	
	public String getCommentId()
	{
		return commentId;
	}

	public String getUserId()
	{
		return userId;
	}

	public String getPostId()
	{
		return postId;
	}

	public String getComment()
	{
		return comment;
	}

	public Date getDateTime()
	{
		return dateTime;
	}

	public void setCommentId(String commentId)
	{
		this.commentId = commentId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public void setPostId(String postId)
	{
		this.postId = postId;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public void setDateTime(Date dateTime)
	{
		this.dateTime = dateTime;
	}
}
