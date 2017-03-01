package models;



import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 *  Db model to store Project Details
 */


@Entity
    public class Project extends Model{
    /**
     *  name -Name of the Project ->Primary Key,Due Date not getting Stored in the Db
     */

    @Id
        public String name;
        public String userId;
        public String description;
        public int cost;
        public String expertise;
        public Date dueDate;
        public String status;

    public Project(String name,String userId, String description, int cost, String expertise, Date dueDate, String status) {
        this.name = name;
        this.userId =userId;
        this.description = description;
        this.cost = cost;
        this.expertise = expertise;
        this.dueDate = dueDate;
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setuserId(String userId) {
        this.userId = userId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getName() {
        return name;
    }

    public String getuserId() {
        return userId;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }

    public String getExpertise() {
        return expertise;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getStatus() {
        return status;
    }

        public static Finder<Integer, Project> find = new Finder<Integer, Project>(Integer.class, Project.class);
    }


