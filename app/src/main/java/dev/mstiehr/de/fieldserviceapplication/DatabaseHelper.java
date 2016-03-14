package dev.mstiehr.de.fieldserviceapplication;

import android.util.Log;
import com.activeandroid.query.From;
import com.activeandroid.query.Select;
import dev.mstiehr.de.fieldserviceapplication.json.Job;
import dev.mstiehr.de.fieldserviceapplication.misc.Constants;

import java.util.List;
import java.util.Observable;

/**
 * Created by Martin on 07.03.2016.
 */
public class DatabaseHelper extends Observable
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


    public List<Job> getAllJobs()
    {
        From from = new Select().from(Job.class);
        return from.execute();
    }

    public int getJobCount()
    {
        return new Select().from(Job.class).count();
    }

    public void reportNewJobs ()
    {
        setChanged();
        notifyObservers();
        Log.d(DatabaseHelper.class.getSimpleName(), "reportNewJobs: ");
    }


}
