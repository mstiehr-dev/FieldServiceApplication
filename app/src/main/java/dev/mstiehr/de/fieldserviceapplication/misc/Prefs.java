package dev.mstiehr.de.fieldserviceapplication.misc;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Martin on 04.03.2016.
 */
public class Prefs
{
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private String userEmail;
    private String serverAddress = "http://android12.msi-wireless.com/getjoblist.php";

    public Prefs(Context context)
    {
        prefs = context.getSharedPreferences("PREFS_PRIVATE", Context.MODE_PRIVATE);
        if(null!=prefs)
        {
            editor = prefs.edit();
        }
    }

    public String getValue(String key,String defaultvalue){
        if (prefs == null) {
            return "Unknown";
        }
        return prefs.getString(key,defaultvalue);
    }
    public void setValue(String key,String value) {
        if (editor == null) {
            return;
        }
        editor.putString(key,value);
    }

    public String getUserEmail ()
    {
        return userEmail;
    }

    public void setUserEmail (String userEmail)
    {
        this.userEmail = userEmail;
    }

    public void save() {
        if (editor == null) {
            return;
        }
        editor.commit();
    }

    public String getServerAddress ()
    {
        return serverAddress;
    }

    public void setServerAddress (String serverAddress)
    {
        this.serverAddress = serverAddress;
    }
}
