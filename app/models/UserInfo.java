package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *  Db model to store the New Users Credentials
 */


@Entity
public class UserInfo extends Model {

    @Id
    private String userId;

    private String email;
    private String password;
    private String role;
    private String securityQuestions;
    private String sanswer;
    private String status;


    public UserInfo(){

    }

    public UserInfo(String userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }


    public UserInfo(String userId, String email, String password, String role, String squestions, String sanswers){
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.role = role;
        this.securityQuestions = squestions;
        this.sanswer = sanswers;
        this.status ="active";
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String name) {
        this.userId = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public String getSecurityQuestions() {
        return securityQuestions;
    }

    public String getSanswers() {
        return sanswer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




    public static Model.Finder<String, UserInfo> find = new Model.Finder<String, UserInfo>(UserInfo.class);
}
