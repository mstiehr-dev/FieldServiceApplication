package dev.mstiehr.de.fieldserviceapplication.misc;

import android.content.Context;
import android.os.AsyncTask;
import okhttp3.*;

import java.io.IOException;

/**
 * Created by Martin on 07.03.2016.
 */
public class JobDownloadTask extends AsyncTask<Void, Void, String>
{
    OkHttpClient client;
    Prefs mPrefs;
    Context mContext;
    HttpResultInterpreter mResultInterpreter;

    Logger logger;

    public JobDownloadTask(Context context, HttpResultInterpreter resultInterpreter)
    {
        mContext = context;
        mResultInterpreter = resultInterpreter;
        logger = Logger.getInstance();
    }

    @Override
    protected void onPreExecute ()
    {
        mPrefs = Prefs.getInstance(mContext);

        client = new OkHttpClient();
        logger.put("start fetching jobs");
    }

    @Override
    protected String doInBackground (Void... params)
    {
        //RequestBody formBody = new FormBody.Builder().
        Request request = new Request.Builder().url(mPrefs.getSavedServerAddress()).build();
        String responseString = "";
        try
        {
            Response response = client.newCall(request).execute();
            if(null!=response)
            {
                responseString = new String(response.body().bytes());
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return responseString;
    }

    @Override
    protected void onPostExecute (String response)
    {
        mResultInterpreter.interpretResult(response);
    }
}
