package models;

import com.avaje.ebean.Model;

/**
 * Created by manika on 12/8/2016.
 */
public class SPRating extends Model {
    private String userid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }

    private float avgRating;
}
