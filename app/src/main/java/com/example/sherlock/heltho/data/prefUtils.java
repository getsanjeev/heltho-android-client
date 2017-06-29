package com.example.sherlock.heltho.data;

/**
 * Created by sanjeev on 3/4/17.
 */

import android.content.Context;

public class prefUtils {

    public static void setCurrentUser(userData currentUser, Context ctx){
        complexPreferences mycomplexPreferences = complexPreferences.getcomplexPreferences(ctx, "user_prefs", 0);
        mycomplexPreferences.putObject("current_user_value", currentUser);
        mycomplexPreferences.commit();
    }

    public static userData getCurrentUser(Context ctx){
        complexPreferences mycomplexPreferences = complexPreferences.getcomplexPreferences(ctx, "user_prefs", 0);
        userData currentUser = mycomplexPreferences.getObject("current_user_value", userData.class);
        return currentUser;
    }

    public static void clearCurrentUser( Context ctx){
        complexPreferences mycomplexPreferences = complexPreferences.getcomplexPreferences(ctx, "user_prefs", 0);
        mycomplexPreferences.clearObject();
        mycomplexPreferences.commit();
    }


}