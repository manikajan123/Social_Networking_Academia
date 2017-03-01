package views.formdata;

import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;



/**
 * Backing class for the Report Bug form.
 */
public class ReportBugForm {


    public String description = "";
    public String projectId ="";



    public ReportBugForm() {
    }

    public List<ValidationError> validate() {

        List<ValidationError> errors = new ArrayList<>();
        return (errors.size() > 0) ? errors : null;
    }


}
