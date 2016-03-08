package dev.mstiehr.de.fieldserviceapplication.activity;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import dev.mstiehr.de.fieldserviceapplication.R;
import dev.mstiehr.de.fieldserviceapplication.json.Job;
import dev.mstiehr.de.fieldserviceapplication.json.JobTransferObject;
import dev.mstiehr.de.fieldserviceapplication.misc.Constants;
import dev.mstiehr.de.fieldserviceapplication.misc.HttpResultInterpreter;
import dev.mstiehr.de.fieldserviceapplication.misc.JobDownloadTask;
import dev.mstiehr.de.fieldserviceapplication.misc.Prefs;

import java.util.List;

public class RefreshJobsActivity extends AppCompatActivity
{
    private static final String TAG = RefreshJobsActivity.class.getSimpleName();

    Prefs mPrefs;

    HttpResultInterpreter resultInterpreter = new HttpResultInterpreter()
    {
        @Override
        public void interpretResult (String result)
        {
            Log.d(TAG, "interpretResult: " + result);
            Gson gson = new Gson();
            try
            {
                JobTransferObject jobTransferObject = gson.fromJson(result, JobTransferObject.class);
                List<Job> jobs = jobTransferObject.getJobList();
                for(Job job : jobs)
                {
                    long id = job.save();
                    Log.d(TAG, "id: " + id);
                }
            }
            catch (JsonSyntaxException e)
            {
                e.printStackTrace();
                return;
            }
        }
    };

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_jobs);

        mPrefs = Prefs.getInstance(this.getApplicationContext());

        JobDownloadTask jobDownloadTask = new JobDownloadTask(getApplicationContext(), resultInterpreter);
        jobDownloadTask.execute();

        setResult(Constants.RESULT_OK);
        finish();
    }
}
