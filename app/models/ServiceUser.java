package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class ServiceUser extends Model {


    @OneToOne(mappedBy = "userId")
    private String userId;
    private String requested_keywords;
    private String ongoing_projects;
    private String finished_projects;

    public ServiceUser(String userId, String requested_keywords, String ongoing_projects, String finished_projects) {
        this.userId = userId;
        this.requested_keywords = requested_keywords;
        this.ongoing_projects = ongoing_projects;
        this.finished_projects = finished_projects;
    }


    public String getUserId() {
        return userId;
    }

    public String getRequested_keywords() {
        return requested_keywords;
    }

    public String getOngoing_projects() {
        return ongoing_projects;
    }

    public String getFinished_projects() {
        return finished_projects;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setRequested_keywords(String requested_keywords) {
        this.requested_keywords = requested_keywords;
    }

    public void setOngoing_projects(String ongoing_projects) {
        this.ongoing_projects = ongoing_projects;
    }

    public void setFinished_projects(String finished_projects) {
        this.finished_projects = finished_projects;
    }


    public ServiceUser(String userId){
        this.userId = userId;
    }

    public static Finder<Integer, ServiceUser> find = new Finder<Integer, ServiceUser>(Integer.class, ServiceUser.class);

    public static List<ServiceUser> searchSUByUserId(String userId){
        return find.where().eq("user_id", userId).findList();
    }

}
