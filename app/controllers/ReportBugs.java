package controllers;

import com.avaje.ebean.Model;
import models.Bug;
import models.ServiceProvider;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.ReportBugForm;
import views.formdata.ServiceProviderinfo;
import views.html.Reportbug;
import views.html.ServicePublication;

import java.sql.SQLException;
import java.util.List;

import static play.mvc.Controller.ctx;

/**
 * Created by manika on 11/28/2016.
 */
public class ReportBugs extends Controller {

    /**
     *  Display a form to report bugs
     */

    public static Result reportBug() {
        Form<ReportBugForm> formData = Form.form(ReportBugForm.class);
        return ok(Reportbug.render("ReportBugForm", Secured.isLoggedIn(ctx()),Secured.isServiceProvider(ctx()), Secured.getUserInfo(ctx()), formData));
    }

    /**
     *  Store the reported bugs in db and return to the Profile Page
     */
    public static Result postReportBug() {
        Form<ReportBugForm> formData = Form.form(ReportBugForm.class).bindFromRequest();
        Bug bug = new Bug(Secured.getUserInfo(ctx()).getUserId(),formData.get().description, formData.get().projectId,"Unresolved");
        bug.save();
        return redirect(routes.Application.profile());
    }


    /**
     *  Display all the bugs with the status from the Db
     */
    public static Result showAllBugs( )  throws SQLException {

        List<Bug> bugs = new Model.Finder(String.class, models.Bug.class).all();

        return ok(views.html.showAllBugs.render("ShowBugs", Secured.isLoggedIn(ctx()),Secured.isServiceProvider(ctx()), Secured.getUserInfo(ctx()),bugs));

    }


}
