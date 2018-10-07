package io.adsonwheels.adsonwheels.config;

import android.content.Context;
import android.content.SharedPreferences;

public class AdsOnWheelsPref {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "AdsOnWheels";
    private static final String SESSION_TOKEN = "SESSION-TOKEN";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    // Constructor
    public AdsOnWheelsPref(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * To Check First Time Launch or Not
     * */
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    /**
     * Get stored user signup data
     * */
    public void setLoginSessionToken(String sessionToken) {
        editor.putString(SESSION_TOKEN, sessionToken);
        editor.commit();
    }

    public String getLoginSessionToken (){return pref.getString(SESSION_TOKEN,"");}
}
