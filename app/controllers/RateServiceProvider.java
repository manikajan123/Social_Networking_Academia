package controllers;

import models.Project;
import models.Rating;
import models.UserInfo;
import play.data.Form;
import play.db.Database;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.RateFormData;
import views.html.RateSP;

import java.util.List;


public class RateServiceProvider extends Controller {
   //Id for Service Provider Id
    public static String  UseridSP;

    /**
     *  Display a form to rate the Service Provider
     */
    public static Result register(String userId) {

        UseridSP = userId;
        System.out.println("the Service Provider is "+userId);
        Form<RateFormData> formData = Form.form(RateFormData.class);
        return ok(views.html.RateSP.render("Rating", Secured.isLoggedIn(ctx()),Secured.isServiceProvider(ctx()), Secured.getUserInfo(ctx()),formData));

    }

    /*
    After Registering.Store in the Db and return to the show Service Provider page
     */
    public static Result postRatings(){
        Form<RateFormData> formData = Form.form(RateFormData.class).bindFromRequest();
        Rating sp  = new Rating(UseridSP,Secured.getUserInfo(ctx()).getUserId(), formData.get().projectId, formData.get().rate,formData.get().comments,
                formData.get().recommend);
        sp.save();
        return redirect(routes.ServicePublish.showInfo());
    }

    /*
     * View the Ratings of each of teh Service Provider
    */
    public static Result viewRatings(String serviceproviderId){

        List<Rating> rating = Rating.find.where().like("service_provider_id", "%" + serviceproviderId + "%").findList();
        if (rating == null){
            return notFound("User not found!");
        }
        else{

        }
        return ok(views.html.ShowALLRating.render("Showrating", Secured.isLoggedIn(ctx()),Secured.isServiceProvider(ctx()), Secured.getUserInfo(ctx()),rating));

    }

}
