package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Preference;

public class PreferenceManager
{

    private static PreferenceManager preferenceManager = new PreferenceManager();
    private DatabaseConnector connection;

    private PreferenceManager()
    {
	connection = DatabaseConnector.getInstance();
    }

    public static PreferenceManager getInstance()
    {
	return preferenceManager;
    }

    // QUERY
    public ArrayList<Preference> getAllPreferences()
    {
	ArrayList<Preference> preferenceList = new ArrayList<>(0);
	PreparedStatement ps;
	String sql = "SELECT * FROM preference";

	try
	{
	    ps = connection.getConnection().prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();

	    while (rs.next())
	    {
		Preference p = new Preference(rs.getInt(1),
			rs.getString(2));
	
		preferenceList.add(p);
	    }
	}
	catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return (ArrayList<Preference>) preferenceList.clone();
    }

    public ArrayList<Preference> getAllUserPreferences(int id)
    {
	ArrayList<Preference> userPreferences = new ArrayList<>(0);
	PreparedStatement ps;
	String sql = "SELECT P.* FROM preference P, userpreference UP WHERE P.preference_id = UP.preference_id AND UP.user_id = ?;";

	try
	{
	    ps = connection.getConnection().prepareStatement(sql);
	    ps.setInt(1, id);

	    ResultSet rs = ps.executeQuery();

	    while (rs.next())
	    {
		Preference p = new Preference(rs.getInt("preference_id"),
			rs.getString("preference"));

		userPreferences.add(p);
	    }
	}
	catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return (ArrayList<Preference>) userPreferences.clone();
    }

    // UPDATE
    public boolean updateUserPreference(int id, ArrayList<Preference> preferences)
    {
	if (deleteUserPreferences(id))
	{
	    insertUserPreference(id, preferences);
	    return true;
	}
	else
	{
	    return false;
	}
    }

    // INSERT
    public void insertUserPreference(int id, ArrayList<Preference> preferences)
    {
	PreparedStatement ps;
	String sql = "INSERT INTO userpreference (user_id, preference_id) VALUES (?, ?);";

	try
	{
	    ps = connection.getConnection().prepareStatement(sql);

	    for (int i = 0; i < preferences.size(); i++)
	    {
		ps.setInt(1, id);
		ps.setInt(2, preferences.get(i).getId());

		ps.executeUpdate();
	    }

	}
	catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    // DELETE
    public boolean deleteUserPreferences(int id)
    {
	PreparedStatement ps;
	String sql = "DELETE FROM userpreference WHERE user_id = ?;";

	try
	{
	    ps = connection.getConnection().prepareStatement(sql);

	    ps.executeUpdate();

	    return true;
	}
	catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return false;
    }
}
