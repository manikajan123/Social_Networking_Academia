package models;


import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

/**
 *  Db model to store the ServiceInformation
 */
@Entity
public class ServiceProvider extends Model{

    @Id
    private String serviceName;
    private String userId;
    private String job;
    private String education;
    private String project;
    private String publication;
    private String researchAreas;
    private String profServices;

    public ServiceProvider(String serviceName,String userid, String job, String education, String project,String publication, String researchAreas, String profServices) {
        this.serviceName =serviceName;
        this.userId = userid;
        this.job = job;
        this.education = education;
        this.project =project;
        this.publication = publication;
        this.researchAreas = researchAreas;
        this.profServices = profServices;
    }

    public String getserviceName() {
        return serviceName;
    }

    public void setserviceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getuserId() {
        return userId;
    }

    public void setuserId(String userId) {
        this.userId = userId;
    }


    public String getResearchAreas() {
        return researchAreas;
    }

    public void setResearchAreas(String researchAreas) {
        this.researchAreas = researchAreas;
    }

    public String getProfServices() {
        return profServices;
    }

    public void setProfServices(String profServices) {
        this.profServices = profServices;
    }


    /**
     * @return the job
     */
    public String getjob() {
        return job;
    }
    /**
     * @param job the job to set
     */
    public void setjob(String job) {
        this.job = job;
    }
    /**
     * @return the education
     */
    public String geteducation() {
        return education;
    }
    /**
     * @param education the education to set
     */
    public void seteducation(String education) {
        this.education = education;
    }
    /**
     * @return the publication
     */
    public String getpublication() {
        return publication;
    }
    /**
     * @param publication the publication to set
     */
    public void setpublication(String publication) {
        this.publication = publication;
    }


    public String getproject() {
        return project;
    }
    /**
     * @param publication the publication to set
     */
    public void setproject(String publication) {
        this.project = project;
    }


    public static Finder<String, ServiceProvider> find = new Finder<String, ServiceProvider>(ServiceProvider.class);

    public static List<ServiceProvider> searchSPByUserId(String userId){
        return find.where().eq("user_id", userId).findList();
    }


}
