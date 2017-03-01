package views.formdata;

import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by surbhibhatnagar on 12/2/16.
 */
public class ServiceUserFormData {
    public String userID ="";
    public String reqKeywords = "";
    public String ongoing = "";
    public String finished =" ";



    public ServiceUserFormData() {
    }

    public List<ValidationError> validate() {

        List<ValidationError> errors = new ArrayList<>();
        return (errors.size() > 0) ? errors : null;
    }
}
