package dev.mstiehr.de.fieldserviceapplication;

import com.activeandroid.query.From;
import com.activeandroid.query.Select;
import dev.mstiehr.de.fieldserviceapplication.json.Job;
import dev.mstiehr.de.fieldserviceapplication.misc.Constants;

import java.util.List;

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


    public List<Job> getAllJobs()
    {
        From from = new Select().from(Job.class);
        return from.execute();
    }
}
