package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.User;

public class UserManager
{
    private static UserManager userManager = new UserManager();
    private PreferenceManager preferenceManager = PreferenceManager.getInstance();
    private DatabaseConnector connection;

    private UserManager()
    {
        connection = DatabaseConnector.getInstance();
    }

    public static UserManager getInstance()
    {
        return userManager;
    }

    // QUERY
    public User getUser( String username, String password )
    {
        PreparedStatement ps;
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?;";

        try
        {
            ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            rs.next();

            User user = new User(Integer.toString(rs.getInt("user_id")),
                                    rs.getString("first_name"),
                                    rs.getString("last_name"),
                                    rs.getString("email"),
                                    rs.getString("mobile_number"),
                                    rs.getString("username"),
                                    rs.getString("password"),
                                    rs.getDouble("location_latitude"),
                                    rs.getDouble("location_longitude"));

            user.setUserPreferences(preferenceManager.getAllUserPreferences(Integer.parseInt(user.getUserId())));    
            
            return user;
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
    
    // INSERT
    public int insertUser( User user )
    {
	PreparedStatement ps;
	String sql = "INSERT INTO user (first_name, last_name, email, mobile_number, username, password, location_latitude, location_longitude)\n" +
	    "VALUES( ?, ?, ?, ?, ?, ?, ?, ? );";
	try
	{
	    ps = connection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	    ps.setString(1, user.getFirstName());
	    ps.setString(2, user.getLastName());
	    ps.setString(3, user.getEmail());
	    ps.setString(4, user.getMobileNumber());
	    ps.setString(5, user.getUsername());
	    ps.setString(6, user.getPassword());
	    ps.setDouble(7, user.getLatitude());
	    ps.setDouble(8, user.getLongitude());
	    
	    int affectedRows = ps.executeUpdate();
	    int genId;

	    if (affectedRows == 0) 
	    {
		throw new SQLException("Creating user failed, no rows affected.");
	    }
	    
	    try (ResultSet generatedKeys = ps.getGeneratedKeys()) 
	    {
		if (generatedKeys.next()) 
		{
		    genId = generatedKeys.getInt(1);
		    
		    preferenceManager.insertUserPreference(genId, user.getUserPreferences());
		    
		    return genId;
		}
		else 
		{
		    throw new SQLException("Creating user failed, no ID obtained.");
		}
	    }
	    catch(SQLException e)
	    {
		    e.printStackTrace();
	    }
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	return -999;
    }
    
    // UPDATE
    public void updateUser( User user )
    {
	PreparedStatement ps;
	String sql = "UPDATE user SET first_name = ?, last_name = ?, email = ?, mobile_number = ?, username = ?, password = ?, "
		+ "location_latitude = ?, location_longitude = ? WHERE user_id = ?;";
	
	try
	{
	    ps = connection.getConnection().prepareStatement(sql);
	    ps.setString(1, user.getFirstName());
	    ps.setString(2, user.getLastName());
	    ps.setString(3, user.getEmail());
	    ps.setString(4, user.getMobileNumber());
	    ps.setString(5, user.getUsername());
	    ps.setString(6, user.getPassword());
	    ps.setDouble(7, user.getLatitude());
	    ps.setDouble(8, user.getLongitude());
	    ps.setInt(9, Integer.parseInt(user.getUserId()));
	    
	    ps.executeUpdate();
	    
	    preferenceManager.updateUserPreference(Integer.parseInt(user.getUserId()), user.getUserPreferences());
	    
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
    }
}
