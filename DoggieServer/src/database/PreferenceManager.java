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
	
	public PreferenceManager()
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
			
			while(rs.next())
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
}
