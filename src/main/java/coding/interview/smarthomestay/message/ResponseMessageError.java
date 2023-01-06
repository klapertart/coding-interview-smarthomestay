package coding.interview.smarthomestay.message;

import java.util.Map;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

public class ResponseMessageError extends ResponseMessageGeneric {
    private Map<String,String> errors;

    public ResponseMessageError(int status, String message, Map<String,String> errors){
        super(status,message);

        this.errors=errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
