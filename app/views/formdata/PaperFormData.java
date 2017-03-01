package views.formdata;

import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by surbhi on 12/8/2016.
 */
public class PaperFormData {
    public String title = "";
    public String authors = "";
    public int pages = 0;
    public String channel = "";

    public List<ValidationError> validate() {

        List<ValidationError> errors = new ArrayList<>();
        return (errors.size() > 0) ? errors : null;
    }
}
