package controllers;

import models.UserInfo;
import models.UserInfoDB;
import play.data.Form;
import play.db.Database;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.RegisterFormData;
import views.html.*;
//import views.html.Register;


import javax.inject.Inject;


public class UserRegister  extends Controller {

    /**
     *  Form for the new User to register
     */

    public static Result register() {
        Form<RegisterFormData> formData = Form.form(RegisterFormData.class);
        return ok(Register.render("Register", null, formData));
    }

    /**
     *  Store the User Info in the Db and redirect to the Login Page
     **/
    public static Result postRegister() {
        Form<RegisterFormData> formData = Form.form(RegisterFormData.class).bindFromRequest();
        UserInfo info1 = new UserInfo(formData.get().userId, formData.get().email, formData.get().password,
                formData.get().role, formData.get().question, formData.get().sanswer);
        info1.save();
        return redirect(routes.Application.login());
    }

}
