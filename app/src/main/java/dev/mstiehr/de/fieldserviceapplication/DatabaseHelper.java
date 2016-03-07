package dev.mstiehr.de.fieldserviceapplication;

/**
 * Created by Martin on 07.03.2016.
 */
public class DatabaseHelper
{
    private static DatabaseHelper instance;

    public synchronized static DatabaseHelper getInstance()
    {
        if(null==instance)
        {
            instance = new DatabaseHelper();
        }
        return instance;
    }
}
