package dev.mstiehr.de.fieldserviceapplication;

import android.app.Application;
import com.activeandroid.ActiveAndroid;

import java.util.HashMap;

/**
 * Created by Martin on 04.03.2016.
 */
public class FieldServiceApplication extends Application
{
    @Override
    public void onCreate ()
    {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
