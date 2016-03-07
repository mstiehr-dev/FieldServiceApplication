package dev.mstiehr.de.fieldserviceapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import dev.mstiehr.de.fieldserviceapplication.R;
import dev.mstiehr.de.fieldserviceapplication.misc.Constants;
import dev.mstiehr.de.fieldserviceapplication.misc.HttpResultInterpreter;
import dev.mstiehr.de.fieldserviceapplication.misc.JobDownloadTask;
import dev.mstiehr.de.fieldserviceapplication.misc.Prefs;

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
