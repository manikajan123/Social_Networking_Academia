package controllers;

import com.avaje.ebean.Model;
import models.ServiceProvider;
import models.ServiceUser;
import play.data.Form;
import play.db.Database;
import play.mvc.Result;
import views.formdata.ServiceProviderinfo;
import views.formdata.ServiceUserFormData;
import views.html.editSUprofile;
import views.html.serviceUserInfo;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;
import static play.mvc.Controller.ctx;
import static play.mvc.Results.ok;

/**
 * Created by surbhibhatnagar on 11/21/16.
 */
public class ServiceUserInfo {

    private static Database db;

    @Inject
    public ServiceUserInfo(Database db){
        this.db = db;
    }

    public static Result showInfo() throws SQLException{

        List<models.ServiceUser> users = new Model.Finder(String.class, models.ServiceUser.class).all();
        return ok(serviceUserInfo.render("Service user info",Secured.isLoggedIn(ctx()),Secured.isServiceProvider(ctx()), Secured.getUserInfo(ctx()), users));

    }

    /**
     *  Edit Service Provider
     */

    public static Result editSU( )  throws SQLException {

        return ok(editSUprofile.render("Edit user info",Secured.isLoggedIn(ctx()),Secured.isServiceProvider(ctx()), Secured.getUserInfo(ctx())));

    }

    public static Result updateSU(String userID) throws SQLException{
        Form<ServiceUserFormData> formData = Form.form(ServiceUserFormData.class).bindFromRequest();
        ServiceUser userFromDB = ServiceUser.find.where().eq("user_id", userID).findUnique();
        userFromDB.setUserId(formData.get().userID);
        userFromDB.setRequested_keywords(formData.get().reqKeywords);
        userFromDB.setOngoing_projects(formData.get().ongoing);
        userFromDB.setFinished_projects(formData.get().finished);
        userFromDB.save();
        return ok();
    }





}
