package models;

import com.avaje.ebean.*;
import org.h2.engine.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

import static play.libs.Json.toJson;
import static play.mvc.Results.ok;

/**
 *  Db model to store the Service providers Details
 */


@Entity
public class Rating extends Model {

    /**
     *  userId ->Service UserId
     *
     */


    @Id
    private Long id;
    private String serviceProviderId;
    private String userid;
    private String projectId;
    private int rating;
    private String comments;
    private int recommend;


    public Rating(String serviceProviderId, String userid, String projectId , int rating, String comments, int recommend) {
        this.userid = userid;
        this.serviceProviderId = serviceProviderId;
        this.projectId = projectId;
        this.rating = rating;
        this.comments = comments;
        this.recommend = recommend;
    }

    public String getuserid() {
        return userid;
    }

    public void setuserid(String userid) {
        this.userid = userid;
    }

    public int getrating() {
        return rating;
    }

    public void setrating(int rating) {
        this.rating = rating;
    }

    public String getserviceProviderId() {
        return serviceProviderId;
    }

    public void setserviceProviderId(String serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    public String getprojectId() {
        return projectId;
    }

    public void setprojectId(String projectId) {
        this.projectId = projectId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    public int getrecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public static Finder<String, Rating> find = new Finder<String, Rating>(Rating.class);

    public static List<SPRating> averageRating(){
        String preparedQuery = "Select service_provider_id , avg(rating) as avgRating\n" +
                " From Rating group by service_provider_id order by avgField DESC;";


        RawSql rawSql = RawSqlBuilder.parse(preparedQuery).create();
        Query<SPRating> query = Ebean.find(SPRating.class);
        query.setRawSql(rawSql);

        return query.findList();
    }
}
