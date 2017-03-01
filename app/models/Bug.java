package models;



import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 *  Db model to store reported bugs
 */


@Entity
public class Bug extends Model{


    public String userId;
    public String description;
    public String projectId;   //check if project Id is required
    public String status;

    public Bug(String userId, String description, String projectId,String status) {
        this.userId = userId;
        this.description = description;
        this.projectId = projectId;
        this.status = status;
    }

    public String getuserid() {
        return userId;
    }

    public void setuserid(String userid) {
        this.userId = userId;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }


    public String getprojectId() {
        return projectId;
    }

    public void setprojectId(String projectId) {
        this.projectId= projectId;
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status= status;
    }

    public static Finder<String, Bug> find = new Finder<String, Bug>(Bug.class);
}
