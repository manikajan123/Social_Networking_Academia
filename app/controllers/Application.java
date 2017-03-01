package controllers;

import com.avaje.ebean.Model;
import models.ServiceProvider;
import models.ServiceUser;
import models.UserInfo;
import models.UserInfoDB;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.*;
import views.formdata.LoginFormData;
import play.mvc.Security;

import java.lang.String;
import java.sql.SQLException;
import java.util.List;

import static models.ServiceProvider.searchSPByUserId;
import static models.ServiceUser.searchSUByUserId;

/**
 * Implements the controllers for this application.
 */
public class Application extends Controller {

    /**
     * Provides the Index page.
     * @return The Index page.
     */
    public static Result index() {
        return ok(Index.render("Home", Secured.isLoggedIn(ctx()),Secured.isServiceProvider(ctx()), Secured.getUserInfo(ctx())));
    }


    public static Result login(  ) {
        Form<LoginFormData> formData = Form.form(LoginFormData.class);
        return ok(Login.render("Login", Secured.isLoggedIn(ctx()),Secured.isServiceProvider(ctx()), Secured.getUserInfo(ctx()), formData));
    }


    /**
     * Processes a login form submission from an unauthenticated user.
     * First we bind the HTTP POST data to an instance of LoginFormData.
     * The binding process will invoke the LoginFormData.validate() method.
     * If errors are found, re-render the page, displaying the error data.
     * If errors not found, render the page with the good data.
     * @return The index page with the results of validation.
     */
    public static Result postLogin() {

        // Get the submitted form data from the request object, and run validation.
        Form<LoginFormData> formData = Form.form(LoginFormData.class).bindFromRequest();

        if (formData.hasErrors()) {
            flash("error", "Login credentials not valid.");
            return badRequest(Login.render("Login", Secured.isLoggedIn(ctx()),Secured.isServiceProvider(ctx()), Secured.getUserInfo(ctx()), formData));
        }
        else {
            // email/password OK, so now we set the session variable and only go to authenticated pages.
            session().clear();
            session("email", formData.get().email);
            return redirect(routes.Application.profile());
        }
    }

    /**
     * Logs out (only for authenticated users) and returns them to the Index page.
     * @return A redirect to the Index page.
     */
    @Security.Authenticated(Secured.class)
    public static Result logout() {
        session().clear();
        return redirect(routes.Application.index());
    }

    /**
     * Provides the Profile page (only to authenticated users).
     * @return The Profile page.
     */
    @Security.Authenticated(Secured.class)
    public static Result profile() throws SQLException{

        List<ServiceUser> profileSUuser ;
        List <ServiceProvider> profileSPuser;
        if(Secured.getUserInfo(ctx()).getRole().equalsIgnoreCase("service user")) {
            profileSUuser = getUserProfile(Secured.getUserInfo(ctx()).getUserId());
            return ok(SUProfile.render("Service User Profile", Secured.isLoggedIn(ctx()),Secured.isServiceProvider(ctx()), Secured.getUserInfo(ctx()), profileSUuser));

        }
        if(Secured.getUserInfo(ctx()).getRole().equalsIgnoreCase("service provider")) {
            profileSPuser = getSPUserProfile(Secured.getUserInfo(ctx()).getUserId());
            return ok(SPProfile.render("Service Provider Profile", Secured.isLoggedIn(ctx()),Secured.isServiceProvider(ctx()), Secured.getUserInfo(ctx()), profileSPuser));

        }
        if(Secured.getUserInfo(ctx()).getRole().equalsIgnoreCase("admin")) {
            profileSUuser = new Model.Finder(String.class, models.ServiceUser.class).all();
            profileSPuser = new Model.Finder(String.class, models.ServiceProvider.class).all();
            return ok(adminProfile.render("Admin Profile", Secured.isLoggedIn(ctx()),Secured.isServiceProvider(ctx()), Secured.getUserInfo(ctx()), profileSPuser, profileSUuser));

        }
        return ok(Profile.render("Profile", Secured.isLoggedIn(ctx()),Secured.isServiceProvider(ctx()), Secured.getUserInfo(ctx())));
    }


    public static List <ServiceUser> getUserProfile(String userId) throws SQLException {

        List <ServiceUser> user = searchSUByUserId(userId);
        return user;

    }

    public static List<ServiceProvider> getSPUserProfile(String userId) throws SQLException {
        List <ServiceProvider> user = searchSPByUserId(userId);
        return user;
    }






}
