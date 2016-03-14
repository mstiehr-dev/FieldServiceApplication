package dev.mstiehr.de.fieldserviceapplication.json;

import android.os.Bundle;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import dev.mstiehr.de.fieldserviceapplication.misc.Constants;

/**
 * Created by Martin on 03.03.2016.
 */
@Table(name = Constants.TABLE_JOBS)
public class Job extends Model
{
    @Column(name = "_id", index=true)
    private String id;

    @Column(name = "_status")
    private String status;

    @Column(name = "_customer")
    private String customer;

    @Column(name = "_address")
    private String address;

    @Column(name = "_city")
    private String city;

    @Column(name = "_state")
    private String state;

    @Column(name = "_zip")
    private String zip;

    @Column(name = "_product")
    private String product;

    @Column(name = "_producturl")
    private String productUrl;

    @Column(name = "_comments")
    private String comments;

    public void setId (String id)
    {
        this.id = id;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getCustomer ()
    {
        return customer;
    }

    public void setCustomer (String customer)
    {
        this.customer = customer;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public String getZip ()
    {
        return zip;
    }

    public void setZip (String zip)
    {
        this.zip = zip;
    }

    public String getProduct ()
    {
        return product;
    }

    public void setProduct (String product)
    {
        this.product = product;
    }

    public String getProductUrl ()
    {
        return productUrl;
    }

    public void setProductUrl (String productUrl)
    {
        this.productUrl = productUrl;
    }

    public String getComments ()
    {
        return comments;
    }

    public void setComments (String comments)
    {
        this.comments = comments;
    }

    public Job ()
    {
        super();
    }

    public Job (String id, String status, String customer, String address, String city, String state, String zip,
            String product, String productUrl, String comments)
    {
        super();
        this.id = id;
        this.status = status;
        this.customer = customer;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.product = product;
        this.productUrl = productUrl;
        this.comments = comments;
    }

    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putString("jobid", this.id);
        b.putString("status", this.status);
        b.putString("customer", this.customer);
        b.putString("address", this.address);
        b.putString("city",this.city);
        b.putString("state",this.state);
        b.putString("zip", this.zip);
        b.putString("product", this.product);
        b.putString("producturl", this.productUrl);
        b.putString("comments", this.comments);

        return b;
    }
    public static Job fromBundle(Bundle b) {
        Job job = new Job();
        job.setId(b.getString("jobid"));
        job.setStatus(b.getString("status"));
        job.setCustomer(b.getString("customer"));
        job.setAddress(b.getString("address"));
        job.setCity(b.getString("city"));
        job.setState(b.getString("state"));
        job.setZip(b.getString("zip"));
        job.setProduct(b.getString("product"));
        job.setProductUrl(b.getString("producturl"));
        job.setComments(b.getString("comments"));

        return job;
    }
}
