package views.formdata;

import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;



/**
 * Backing class for the New User Register class.
 */
public class RegisterFormData {

        /** The submitted userId **/
        public String userId = "";
        /** The submitted role **/
        public String role = "";
        /** Security Question **/
        public String question = "";
        /** Security Question answer **/
        public String sanswer = "";
        /** The submitted email. */
        public String email = "";
        /** The submitted password. */
        public String password = "";



    /** Required for form instantiation. */
        public RegisterFormData() {
        }

        /**
         * Validates Form<RegisterFormData>.
         * Called automatically in the controller by bindFromRequest().
         *
         * @return Null if valid, or a List[ValidationError] if problems found.
         */
        public List<ValidationError> validate() {

            List<ValidationError> errors = new ArrayList<>();


            return (errors.size() > 0) ? errors : null;
        }

}
