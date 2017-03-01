package views.formdata;

import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;



/**
 * Backing class for the Service Provider Info form.
 */
public class ServiceProviderinfo {

    public String userID ="";
    public String servicename = "";
    public String education = "";
    public String job ="";
    public String project ="";
    public String publication =" ";
    public String researchAreas ="";
    public String profServices="";


    public ServiceProviderinfo() {
    }

    public List<ValidationError> validate() {

        List<ValidationError> errors = new ArrayList<>();
        return (errors.size() > 0) ? errors : null;
    }
}
