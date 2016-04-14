package controller;

import java.util.ArrayList;

import database.PreferenceManager;
import database.UserManager;
import model.Preference;

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
}
