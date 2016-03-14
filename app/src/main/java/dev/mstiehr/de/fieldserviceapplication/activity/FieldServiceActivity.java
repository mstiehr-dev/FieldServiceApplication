package dev.mstiehr.de.fieldserviceapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import dev.mstiehr.de.fieldserviceapplication.DatabaseHelper;
import dev.mstiehr.de.fieldserviceapplication.R;
import dev.mstiehr.de.fieldserviceapplication.json.LogEntry;
import dev.mstiehr.de.fieldserviceapplication.misc.Logger;
import dev.mstiehr.de.fieldserviceapplication.misc.Prefs;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class FieldServiceActivity extends Activity
        implements NavigationView.OnNavigationItemSelectedListener, Observer
{
    final int ACTIVITY_REFRESH_JOBS = 1;
    final int ACTIVITY_LIST_JOBS = 2;
    final int ACTIVITY_SETTINGS = 3;

    Prefs myPrefs = null;
    Logger logger;
    DatabaseHelper dbHelper;

    TextView tvLog;
    Button btnListJobs;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_service);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        myPrefs = new Prefs(this.getApplicationContext());
        logger = Logger.getInstance();
        dbHelper = DatabaseHelper.getInstance();

        refreshUserInfo();
        final Button btnRefresh = (Button) findViewById(R.id.getjobs);
        btnRefresh.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                try
                {
                    startActivityForResult(new Intent(v.getContext(), RefreshJobsActivity.class),
                            ACTIVITY_REFRESH_JOBS);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        btnListJobs = (Button) findViewById(R.id.listjobs);
        btnListJobs.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
//                uninstall();
                try
                {
                    startActivityForResult(new Intent(v.getContext(), ListJobsActivity.class), ACTIVITY_LIST_JOBS);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        final Button btnSettings = (Button) findViewById(R.id.settings);
        btnSettings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                try
                {
                    startActivityForResult(new Intent(v.getContext(), SettingsTransientActivity.class),
                            ACTIVITY_SETTINGS);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        TextView tvUserName = (TextView) findViewById(R.id.tv_username);
        if ("".equals(myPrefs.getSavedUsername()))
        {
            tvUserName.setVisibility(View.GONE);
        }
        else
        {
            String mail = myPrefs.getSavedUsername();
            String name;
            if(mail!=null && mail.contains("@"))
            {
                name = mail.substring(0, mail.indexOf('@'));
            }
            else
            {
                name = mail;
            }
            tvUserName.setText(name);
        }

        tvLog = (TextView) findViewById(R.id.infobox);

        dbHelper.addObserver(this);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        //super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case ACTIVITY_REFRESH_JOBS:
                break;
            case ACTIVITY_LIST_JOBS:
                break;
            case ACTIVITY_SETTINGS:
                refreshUserInfo();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed ()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected (MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera)
        {
            // Handle the camera action
        }
        else if (id == R.id.nav_gallery)
        {

        }
        else if (id == R.id.nav_slideshow)
        {

        }
        else if (id == R.id.nav_manage)
        {

        }
        else if (id == R.id.nav_share)
        {

        }
        else if (id == R.id.nav_send)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void refreshUserInfo ()
    {

    }

    private void refreshLogBox ()
    {
        if (tvLog == null)
        {
            return;
        }
        List<LogEntry> entries = logger.getEntries();
        StringBuilder sb = new StringBuilder();
        for (LogEntry entry : entries)
        {
            sb.append(entry.getMessage() + "\n");
        }
        tvLog.setText(sb.toString());
    }

    @Override
    protected void onResume ()
    {
        super.onResume();
        refreshLogBox();
    }

    public void uninstall()
    {
        final Uri packageURI = Uri.parse("package:" + "de.powercommerce.mde.dsofklkw");
        final Intent uninstallIntent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE, packageURI);
        uninstallIntent.putExtra("android.intent.extra.UNINSTALL_ALL_USERS", true);
        startActivity(uninstallIntent);
    }



    @Override
    public void update (Observable observable, Object data)
    {
        int count = DatabaseHelper.getInstance().getJobCount();
        btnListJobs.setText("List Jobs" + "(" + count + ")");
        Log.d(FieldServiceActivity.class.getSimpleName(), "update: ");
    }
}
