package dev.mstiehr.de.fieldserviceapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import de.svenjacobs.loremipsum.LoremIpsum;
import dev.mstiehr.de.fieldserviceapplication.R;
import dev.mstiehr.de.fieldserviceapplication.json.Job;

public class ShowJobActivity extends AppCompatActivity
{
    private final static String TAG = ShowJobActivity.class.getSimpleName();

    Job currentJob;

    ImageButton iv_state;
    ImageButton ib_show_map;
    TextView tv_customer;
    TextView tv_address;
    TextView tv_city;
    TextView tv_product;
    TextView tv_url;
    TextView tv_comments;

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

        initUI();
        populateUI();

    }

    /**
     * initialize all the UI elements
     */
    private void initUI()
    {
        iv_state = (ImageButton) findViewById(R.id.iv_state);
        ib_show_map = (ImageButton) findViewById(R.id.ib_show_map);
        tv_customer = (TextView) findViewById(R.id.tv_customer);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_city = (TextView) findViewById(R.id.tv_city);
        tv_product = (TextView) findViewById(R.id.tv_product);
        tv_url = (TextView) findViewById(R.id.tv_url);
        tv_comments = (TextView) findViewById(R.id.tv_comments);

        ib_show_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // // TODO: 17.03.2016
            }
        });

        iv_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // // TODO: 17.03.2016
            }
        });

        tv_comments.setText(new LoremIpsum().getWords(1000));
    }

    private void populateUI()
    {
        tv_customer.setText(currentJob.getCustomer());
        tv_address.setText(currentJob.getAddress());
        tv_city.setText(currentJob.getZip() + " " + currentJob.getCity());

        tv_product.setText(currentJob.getProduct());
        tv_url.setText(currentJob.getProductUrl());

        tv_comments.setText(currentJob.getComments());
    }

    /**
     * updates the status of a job and its icon according to the current state
     */
    private void setState()
    {
        // // TODO: 17.03.2016
    }

    /**
     * opens dialog and asks for an status update
     */
    private void getState()
    {
        // // TODO: 17.03.2016

        setState();
    }
}
