package dev.mstiehr.de.fieldserviceapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import dev.mstiehr.de.fieldserviceapplication.DatabaseHelper;
import dev.mstiehr.de.fieldserviceapplication.R;
import dev.mstiehr.de.fieldserviceapplication.json.Job;
import dev.mstiehr.de.fieldserviceapplication.misc.Prefs;

import java.util.List;

public class ListJobsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    private final static String TAG = ListJobsActivity.class.getSimpleName();

    Prefs mPrefs;
    ListView listView;
    TextView tvHeader;
    List<Job> jobs;
    JobListAdapter adapter;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_jobs);

        mPrefs = Prefs.getInstance(this.getApplicationContext());
        tvHeader = (TextView) findViewById(R.id.job_list_header);
        listView = (ListView) findViewById(R.id.job_listview);

        jobs = DatabaseHelper.getInstance().getAllJobs();
        adapter = new JobListAdapter();

        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick (AdapterView<?> parent, View view, int position, long id)
    {

    }
    
    class JobListAdapter extends BaseAdapter
    {

        @Override
        public int getCount ()
        {
            if(jobs==null)
            {
                return 0;
            }
            return jobs.size();
        }

        @Override
        public Object getItem (int position)
        {
            if(jobs==null)
            {
                return null;
            }
            return jobs.get(position);
        }

        @Override
        public long getItemId (int position)
        {
            if(jobs!=null && jobs.get(position)!=null)
            {
                return ((Job)jobs.get(position)).getId();
            }
            return 0;
        }

        @Override
        public View getView (int position, View convertView, ViewGroup parent)
        {
            // // TODO: 08.03.2016 inflate layout
            final Job job = jobs.get(position);
            if(convertView==null)
            {
                convertView = View.inflate(getApplicationContext(), R.layout.joblist_item, null);
            }
            if(job==null)
            {
                return convertView;
            }
            TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            tvTitle.setText(job.getProduct());
            TextView tvId = (TextView) convertView.findViewById(R.id.tv_jobid);
            tvId.setText(""+job.getId());


            return convertView;
        }
    }
}
