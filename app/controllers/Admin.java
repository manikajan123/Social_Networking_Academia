package controllers;

import com.avaje.ebean.Model;
import models.UserInfo;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.AdminShowUsers;
import views.html.Paperlist;

import java.sql.SQLException;
import java.util.List;


public class Admin  extends Controller {

    /*
    *
    *  Show All the Service Users and Service Providers
    *
    */

    public static Result listAllUsers(){

        List<UserInfo> users = new Model.Finder(String.class, UserInfo.class).all();

        return ok(AdminShowUsers.render("Users",Secured.isLoggedIn(ctx()), Secured.isServiceProvider(ctx()),
                Secured.getUserInfo(ctx()), users));
    }


    public static Result setUserStatus(int status, String name) throws SQLException {

        String userStatus ="active";
        if(status == 1 )
            userStatus = "active";
        if(status == 2)
            userStatus = "inactive";

        //List<models.Project> projects = Project.find.all();
        List<models.UserInfo> users = UserInfo.find.where()
                .like("user_id", "%" + name + "%")
                .findList();

        if (users == null){
            return notFound("User not found!");
        }

        for (models.UserInfo u : users) {
            u.setStatus(userStatus);
            u.update();
        }
        List<UserInfo> users1 = new Model.Finder(String.class, UserInfo.class).all();

        return ok(AdminShowUsers.render("Users",Secured.isLoggedIn(ctx()), Secured.isServiceProvider(ctx()),
                Secured.getUserInfo(ctx()), users1));
    }
}
