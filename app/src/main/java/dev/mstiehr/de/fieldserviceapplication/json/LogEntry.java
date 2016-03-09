package dev.mstiehr.de.fieldserviceapplication.json;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Martin on 09.03.2016.
 */
@Table(name = "LogEntries")
public class LogEntry extends Model
{
    @Column(name = "_id", index = true)
    private long id;

    @Column(name = "timestamp")
    private long timestamp;

    @Column(name = "message")
    private String message;

    public long getTimestamp ()
    {
        return timestamp;
    }

    public void setTimestamp (long timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }
}
