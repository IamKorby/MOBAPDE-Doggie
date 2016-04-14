package controller;

import java.util.ArrayList;

import database.PreferenceManager;
import database.UserManager;
import model.Preference;
import model.User;

public class Controller
{
    private PreferenceManager preferenceManager;
    private UserManager userManager;

    public Controller()
    {
        // change to preferenceManager.getInstance();
        preferenceManager = PreferenceManager.getInstance();
        userManager = UserManager.getInstance();
    }

    public ArrayList<Preference> getAllPreferences()
    {
        return preferenceManager.getAllPreferences();
    }
    
    public ArrayList<Preference> getAllUserPreferences( int id )
    {
	return preferenceManager.getAllUserPreferences(id);
    }
    
    public User getUserByCredentials( String username, String password )
    {
	User user = userManager.getUser(username, password);
	user.setUserPreferences(getAllUserPreferences(Integer.parseInt(user.getUserId())));
	return user;
    }
    
    public void insertUser( User user )
    {
	userManager.insertUser(user);
    }
}
