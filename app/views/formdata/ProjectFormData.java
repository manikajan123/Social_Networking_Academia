package views.formdata;

import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * Backing class for the Add Project form.
 */
public class ProjectFormData {

    public String name = "";
    public String desc = "";
    public int cost = 0;
    public String expertise = "";
    public Date duedate = null;
    public String status = "";

    public ProjectFormData() {
    }

    /**
     * Validates Form<ProjectFormData>.
     * Called automatically in the controller by bindFromRequest().
     *
     * @return Null if valid, or a List[ValidationError] if problems found.
     */
    public List<ValidationError> validate() {

        List<ValidationError> errors = new ArrayList<>();
        return (errors.size() > 0) ? errors : null;
    }
}
