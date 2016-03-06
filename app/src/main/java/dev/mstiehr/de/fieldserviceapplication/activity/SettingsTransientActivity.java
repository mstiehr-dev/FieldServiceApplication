package dev.mstiehr.de.fieldserviceapplication.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import dev.mstiehr.de.fieldserviceapplication.R;
import dev.mstiehr.de.fieldserviceapplication.misc.Prefs;

public class SettingsTransientActivity extends AppCompatActivity
{
    private EditText inputUsername, inputServerAddr;
    private Button btnSave;

    Prefs mPrefs;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_transient);

        btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                if(mPrefs==null)
                {
                    return;
                }

                mPrefs.setServerAddress(inputServerAddr.getText().toString());
                mPrefs.setUserEmail(inputUsername.getText().toString());
                mPrefs.save();

                finish();
            }
        });

        inputUsername = (EditText) findViewById(R.id.input_username);
        inputServerAddr = (EditText) findViewById(R.id.input_server_add);
        mPrefs = new Prefs(this.getApplicationContext());

        if("".equals(mPrefs.getSavedUsername()))
        {   // set hint
            inputUsername.setHint("enter username");
        }
        else
        {   // set text
            inputUsername.setText(mPrefs.getSavedUsername());
        }
        if("".equals(mPrefs.getSavedServerAddress()))
        {   // set hint
            inputServerAddr.setHint("enter server address");
        }
        else
        {
            inputServerAddr.setText(mPrefs.getSavedServerAddress());
        }


    }
}
