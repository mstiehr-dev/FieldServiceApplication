package dev.mstiehr.de.fieldserviceapplication.json;

import java.util.List;

/**
 * Created by Martin on 06.03.2016.
 */
public class JobTransferObject
{
    /**
     * example data: https://api.myjson.com/bins/4dse1 -> 29 jobs with unique IDs
     */
    private List<Job> joblist;

    public List<Job> getJobList ()
    {
        return joblist;
    }

    public void setJobList (List<Job> jobList)
    {
        this.joblist = jobList;
    }
}
