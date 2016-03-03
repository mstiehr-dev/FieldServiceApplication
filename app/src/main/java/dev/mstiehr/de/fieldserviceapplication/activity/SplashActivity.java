package dev.mstiehr.de.fieldserviceapplication.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import dev.mstiehr.de.fieldserviceapplication.R;

public class SplashActivity extends Activity
{

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Hide Actionbar
        ActionBar ab = getActionBar();
        if(null!=ab)
        {
            ab.hide();
        }

        setContentView(R.layout.activity_splash);
        Handler x = new Handler();
        x.postDelayed(new SplashHandler(), 2000);
    }

    class SplashHandler implements Runnable
    {

        @Override
        public void run ()
        {
            startActivity(new Intent(getApplication(), MainActivity.class));
            SplashActivity.this.finish();
        }
    }
}
