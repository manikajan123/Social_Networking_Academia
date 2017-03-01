package controllers;

import com.avaje.ebean.Model;
import models.ServiceProvider;
import models.UserInfo;
import models.UserInfoDB;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.LoginFormData;
import views.formdata.RegisterFormData;
import views.formdata.ServiceProviderinfo;

import views.html.ServicePublication;
import views.html.editSPprofile;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by manika on 11/28/2016.
 */
public class ServicePublish extends Controller {

    /**
     *  Display form for the Service Provider to publish a Service
     */

    public static Result publishService() {
        Form<ServiceProviderinfo> formData = Form.form(ServiceProviderinfo.class);
        return ok(ServicePublication.render("ServicePublication", Secured.isLoggedIn(ctx()),Secured.isServiceProvider(ctx()), Secured.getUserInfo(ctx()), formData));
    }

    /**
     *  Store the service details in the db and return to the Profile Page
     */
    public static Result postPublishService() {
        Form<ServiceProviderinfo> formData = Form.form(ServiceProviderinfo.class).bindFromRequest();
        ServiceProvider sp1 = new ServiceProvider(formData.get().servicename,Secured.getUserInfo(ctx()).getUserId(),formData.get().job, formData.get().education, formData.get().project,
                formData.get().publication, formData.get().researchAreas, formData.get().profServices);
        sp1.save();
        return redirect(routes.Application.profile());
    }

    /**
     *  Show all the Service Providers
     */

    public static Result showInfo( )  throws SQLException {

        List<ServiceProvider> providers = new Model.Finder(String.class, models.ServiceProvider.class).all();

        return ok(views.html.serviceProviderInfo.render("ServiceProviders List", Secured.isLoggedIn(ctx()),Secured.isServiceProvider(ctx()), Secured.getUserInfo(ctx()),providers));

    }

    /**
     *  Edit Service Provider
     */

    public static Result editSP( )  throws SQLException {

        return ok(editSPprofile.render("Edit user info",Secured.isLoggedIn(ctx()),Secured.isServiceProvider(ctx()), Secured.getUserInfo(ctx())));

    }

    /**
     *  Show all the Service Providers
     */
    public static Result showExpertise( String expertise)  throws SQLException {
        System.out.println("expertise");

        List<ServiceProvider> providers = ServiceProvider.find.where()
                .like("prof_services", "%" + expertise + "%")
                .findList();
        return ok(views.html.serviceProviderInfo.render("ServiceProviders List", Secured.isLoggedIn(ctx()),Secured.isServiceProvider(ctx()), Secured.getUserInfo(ctx()),providers));

    }

    /**
     *  Show all the Service Providers which match a keyword
     */
    public static Result showKeyWords(String keyword1)  throws SQLException {
        System.out.println(keyword1);
        return showTopicSearchServices(keyword1);
        /*
        //String keyword= "MS";
        System.out.println("the keyword is"+keyword1);

        //
        List<ServiceProvider> providers = new Model.Finder(String.class, models.ServiceProvider.class).all();

        return ok(views.html.serviceProviderInfo.render("ServiceProviders List", Secured.isLoggedIn(ctx()),Secured.isServiceProvider(ctx()), Secured.getUserInfo(ctx()),providers));
        */
    }

    /**
     * Update Service User
     */
     public static Result updateSP( String userID)  throws SQLException {

        Form<ServiceProviderinfo> formData = Form.form(ServiceProviderinfo.class).bindFromRequest();
        ServiceProvider userFromDB = ServiceProvider.find.where().eq("user_id", userID).findUnique();
         userFromDB.setuserId(formData.get().userID);
         userFromDB.setserviceName(formData.get().servicename);
         userFromDB.setjob(formData.get().job);
         userFromDB.seteducation(formData.get().education);
         userFromDB.setproject(formData.get().project);
         userFromDB.setpublication(formData.get().publication);
         userFromDB.setResearchAreas(formData.get().researchAreas);
         userFromDB.setProfServices(formData.get().profServices);
         userFromDB.save();
         return ok();
    }


    /**
     *  Show keyword based search Service Providers
     */
    public static Result showTopicSearchServices(String topic)  throws SQLException {
        System.out.println(topic);
        List<ServiceProvider> providers = new Model.Finder(String.class, models.ServiceProvider.class).all();
        List<ServiceProvider> retList = new ArrayList<ServiceProvider>();

        for(ServiceProvider p : providers) {
            if(p.getserviceName().toString().contains(topic)) {
                retList.add(p);
            }
        }

        return ok(views.html.serviceProviderInfo.render("ServiceProviders List", Secured.isLoggedIn(ctx()),Secured.isServiceProvider(ctx()), Secured.getUserInfo(ctx()), retList));
    }

}
