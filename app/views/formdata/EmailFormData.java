package views.formdata;

import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyan on 12/1/16.
 */
public class EmailFormData {

    public String target = "";
    public String subject = "";
    public String content = "";

    public EmailFormData() {
    }

    /**
     * Validates Form<EmailFormData>.
     * Called automatically in the controller by bindFromRequest().
     *
     * @return Null if valid, or a List[ValidationError] if problems found.
     */
    public List<ValidationError> validate() {

        List<ValidationError> errors = new ArrayList<>();
        return (errors.size() > 0) ? errors : null;
    }
}