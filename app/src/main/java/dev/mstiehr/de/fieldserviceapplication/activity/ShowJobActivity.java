package dev.mstiehr.de.fieldserviceapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import de.svenjacobs.loremipsum.LoremIpsum;
import dev.mstiehr.de.fieldserviceapplication.R;
import dev.mstiehr.de.fieldserviceapplication.json.Job;

public class ShowJobActivity extends AppCompatActivity
{
    private final static String TAG = ShowJobActivity.class.getSimpleName();
    Job currentJob;

    TextView tvComments;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_job);

        Intent currentIntent = getIntent();
        if(null==currentIntent || null==currentIntent.getExtras())
        {
            return;
        }
        currentJob = Job.fromBundle(currentIntent.getExtras());
        Log.d(TAG, "currentJob: " + currentJob);

        tvComments = (TextView) findViewById(R.id.tv_comments);
        tvComments.setText(new LoremIpsum().getWords(1000));
    }
}
