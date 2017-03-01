package controllers;

import models.Mail;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.EmailFormData;
import views.html.Email;

import java.util.List;

/**
 * Implements the controllers for this application.
 */
public class EmailController extends Controller {

    /**
     * Provides the email page.
     * @return The email page.
     */
    public static Result email() {
        Form<EmailFormData> formData = Form.form(EmailFormData.class);
        List<Mail> emails = Mail.find.where()
                .eq("target", Secured.getUserInfo(ctx()).getUserId())
                .findList();
        return ok(Email.render("emailform", Secured.isLoggedIn(ctx()),Secured.isServiceProvider(ctx()), Secured.getUserInfo(ctx()), formData, emails));
    }

    public static Result postEmail() {
        Form<EmailFormData> formData = Form.form(EmailFormData.class).bindFromRequest();
        Mail mail  = new Mail(Secured.getUserInfo(ctx()).getUserId(), formData.get().target, formData.get().subject,formData.get().content);
        mail.save();
        return redirect(routes.EmailController.email());
    }




}