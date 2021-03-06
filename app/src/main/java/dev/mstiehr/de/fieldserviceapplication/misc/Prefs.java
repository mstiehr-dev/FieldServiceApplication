package dev.mstiehr.de.fieldserviceapplication.misc;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Martin on 04.03.2016.
 */
public class Prefs
{
    private static Prefs instance;

    public synchronized static Prefs getInstance(Context context)
    {
        if(null==instance)
        {
            if(null!=context)
            {
                instance = new Prefs(context);
            }
        }
        return instance;
    }

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private String userEmail = "anonymous";
//    private String serverAddress = "http://android12.msi-wireless.com/getjoblist.php";
//    private String serverAddress = "https://api.myjson.com/bins/4dse1";
    private String serverAddress = "https://jsonblob.com/api/jsonblob/56df3329e4b01190df536113";

    private final static String KEY_USERNAME = "USERNAME";
    private final static String KEY_SERVERADDR = "SERVER_ADDRESS";

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

    /**
     * writes changes to preferences file
     */
    public void save() {
        if (editor == null) {
            return;
        }
        saveUsername();
        saveServerAddress();

        editor.commit();
    }

    public String getSavedUsername()
    {
        return getValue(KEY_USERNAME, userEmail);
    }

    public String getSavedServerAddress()
    {
        return getValue(KEY_SERVERADDR, serverAddress);
    }

    public void saveUsername()
    {
        setValue(KEY_USERNAME, userEmail);
    }

    public void saveServerAddress()
    {
        setValue(KEY_SERVERADDR, serverAddress);
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
