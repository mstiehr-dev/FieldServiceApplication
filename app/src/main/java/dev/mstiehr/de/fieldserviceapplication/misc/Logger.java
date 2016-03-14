package dev.mstiehr.de.fieldserviceapplication.misc;

import com.activeandroid.query.From;
import com.activeandroid.query.Select;
import dev.mstiehr.de.fieldserviceapplication.json.LogEntry;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by Martin on 09.03.2016.
 */
public class Logger
{
    private final int MAX_ENTRIES = 3;
    private static Logger instance;

    public static synchronized Logger getInstance()
    {
        if(null==instance)
        {
            instance = new Logger();
        }
        return instance;
    }

    public void cleanUp()
    {
        long oldest;
        From query = new Select().from(LogEntry.class)
                .limit(3)
                .orderBy("timestamp" + " DESC ");
        List<LogEntry> entries = query.execute();
        if(entries==null || entries.size()<=3)
        {   // nothing to do
            return;
        }
        for(LogEntry entry : entries)
        {
            if(entries.indexOf(entry)>MAX_ENTRIES)
            {
                entry.delete();
            }
        }
    }

    public long put(String msg)
    {
        synchronized (Logger.this)
        {
            long timestamp = System.currentTimeMillis();
            LogEntry entry = new LogEntry();
            entry.setTimestamp(timestamp);
            entry.setMessage(msg);
            return entry.save();
        }
    }

    public LogEntry get(long id)
    {
        From query = new Select().from(LogEntry.class)
                .where("_id" + " = " + id);
        return query.executeSingle();
    }

    public List<LogEntry> getEntries()
    {
        From query = new Select().from(LogEntry.class)
                .orderBy("timestamp" + " DESC ")
                .limit(MAX_ENTRIES);
        return query.execute();
    }

//    public static String getDatestringByTimestamp(long timestamp)
//    {
////        DateFormat dateFormat = DateFormat.getInstance().
//    }
}
