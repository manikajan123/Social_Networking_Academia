package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import models.Project;
import models.ServiceProvider;
import models.UserInfo;
import play.data.Form;
import play.db.Database;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.ProjectFormData;
import views.formdata.ServiceProviderinfo;
import views.html.ServicePublication;
import views.html.serviceProject;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

import static play.libs.Json.toJson;

/**
 * Implements the controllers for this application.
 */
public class ProjectController extends Controller {

    /**
     * Displays the Project Index Page
     */
    public static Result index() {
        return ok(views.html.Project.render("Project1", Secured.isLoggedIn(ctx()),Secured.isServiceProvider(ctx()), Secured.getUserInfo(ctx())));
    }

    /**
     * Form to Add a New Project
     */
    public static Result getForm() {
        Form<ProjectFormData> formData = Form.form(ProjectFormData.class);
        return ok(serviceProject.render("Projectform", Secured.isLoggedIn(ctx()),Secured.isServiceProvider(ctx()), Secured.getUserInfo(ctx()), formData));


    }


    /**
     * Store into the Db and return to the profile page
     */
    public static Result addProject(){
        Form<ProjectFormData> formData = Form.form(ProjectFormData.class).bindFromRequest();
        Project project = new Project(formData.get().name,Secured.getUserInfo(ctx()).getUserId(), formData.get().desc, formData.get().cost,
                formData.get().expertise, formData.get().duedate, formData.get().status);
        project.save();
        return redirect(routes.Application.profile());
    }

    /**
     * Display the Project based on their status.(finished,ongoing,new)
     */
    public static Result searchProjectByStatus(int i)  throws SQLException{
        String status = "new";
        if(i == 1 )
            status = "finished";
        if( i ==2)
            status = "ongoing";

        List<models.Project> projects = Project.find.where()
                .like("status", "%" + status + "%")
                .findList();
        if (projects == null){
            return notFound("User not found!");
        }
        return ok(views.html.Projectlist.render("Project List", Secured.isLoggedIn(ctx()),Secured.isServiceProvider(ctx()), Secured.getUserInfo(ctx()),projects));

    }


    public static Result setProjectStatus(int status, String name) throws SQLException{

        String cur_status = "new";
        if(status == 1 )
            cur_status = "finished";
        if(status == 2)
            cur_status = "ongoing";

        //List<models.Project> projects = Project.find.all();
        List<models.Project> projects = Project.find.where()
                .like("name", "%" + name + "%")
                .findList();

        if (projects == null){
            return notFound("User not found!");
        }

        for (models.Project p : projects) {
            p.setStatus(cur_status);
            p.update();
        }

        return ok(views.html.Project.render("Project1", Secured.isLoggedIn(ctx()),Secured.isServiceProvider(ctx()), Secured.getUserInfo(ctx())));
    }

    /**
     * Not Using
     */
    public static Result listProject(){

        List<Project> projects = new Model.Finder(String.class, Project.class).all();

        return ok(toJson(projects));
    }

    /**
     * Not Used
     */
    public static Result getProject(int id){

        Project project = Project.find.byId(id);

        if (project == null){
            return notFound("User not found!");
        }

        return ok(toJson(project));

    }

    /**
     * Not Used
     */

    public static Result updateProject(int id){

        Project project = Project.find.byId(id);

        if (project == null){
            return notFound("User not found");
        }

        JsonNode json = request().body().asJson();
        Project projectToBe = Json.fromJson(json, Project.class);

        project = projectToBe;
        project.update();

        return ok();
    }
    /**
     * Not Used
     */
    public static Result deleteProject(int id){

        Project project = Project.find.byId(id);

        if (project == null){
            return notFound("User not found");
        }

        project.delete();
        return ok();
    }
    /**
     * Not Used
     */
    public static Result searchProject(String name){
        List<Project> projects = Project.find.where()
                .like("name", "%"+name+"%")
                .findList();

        if (projects == null){
            return notFound("User not found!");
        }

        return ok(toJson(projects));

    }




}
