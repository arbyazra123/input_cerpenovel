package com.zracoder13.smeandigitallibrary.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;



public class SaveSharedPreference {
    public static final String LOGGED_IN_PREF = "logged_in_status";
    public static final String MY_PREFS_NAME = "users_prefs_file";
    static SharedPreferences getPreferences(Context context) {

        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Set the Login Status
     * @param context
     * @param loggedIn
     */
    public static void setLoggedIn(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();

        editor.putBoolean(LOGGED_IN_PREF, loggedIn);
        editor.apply();
    }

    /**
     * Get the Login Status
     * @param context
     * @return boolean: login status
     */
    public static boolean getLoggedStatus(Context context) {
        return getPreferences(context).getBoolean(LOGGED_IN_PREF, false);
    }


}
