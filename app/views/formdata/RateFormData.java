package views.formdata;

import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;




/**
 * Backing class for the Service Provider's Rating form.
 */
public class RateFormData {


    public String userid = "";
    public int rate = 0;
    public String comments="";
    public String projectId ="";
    public int recommend = 0;
    public int avgRating = 0;

    public int getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(int avgRating) {
        this.avgRating = avgRating;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public RateFormData() {
    }

    public List<ValidationError> validate() {

        List<ValidationError> errors = new ArrayList<>();
        return (errors.size() > 0) ? errors : null;
    }
}
