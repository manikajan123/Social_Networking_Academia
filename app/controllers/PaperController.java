package controllers;

import com.avaje.ebean.Model;
import models.Paper;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.PaperFormData;
import views.html.AddPaper;
import views.html.Paperlist;

import java.util.List;

import static play.libs.Json.toJson;

/**
 * Created by surbhi on 12/8/2016.
 */
public class PaperController extends Controller {
    public static Result paperForm() {
        Form<PaperFormData> formData = Form.form(PaperFormData.class);
        return ok(AddPaper.render("AddPaper", Secured.isLoggedIn(ctx()),Secured.isServiceProvider(ctx()),
                Secured.getUserInfo(ctx()),formData));
    }

    public static Result listProject(){

        List<Paper> projects = new Model.Finder(String.class, Paper.class).all();

        return ok(Paperlist.render("List paper",Secured.isLoggedIn(ctx()), Secured.isServiceProvider(ctx()),
                Secured.getUserInfo(ctx()), projects));
    }
    /**
     *  Store the AddPaper Info in the Db
     */

    public static Result postAddPaper() {
        Form<PaperFormData> formData = Form.form(PaperFormData.class).bindFromRequest();
        Paper info1 = new Paper(formData.get().title, formData.get().authors, formData.get().pages,
                formData.get().channel);
        info1.save();
        return redirect(routes.PaperController.listProject());
    }


}
