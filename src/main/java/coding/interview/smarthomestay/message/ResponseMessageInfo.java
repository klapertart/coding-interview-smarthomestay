package coding.interview.smarthomestay.message;

import java.util.Map;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */
public class ResponseMessageInfo extends ResponseMessageGeneric{
    private Map<String,String> info;

    public ResponseMessageInfo(int status, String message, Map<String,String> info){
        super(status,message);

        this.info=info;
    }

    public Map<String, String> getInfo() {
        return info;
    }

    public void setInfo(Map<String, String> info) {
        this.info = info;
    }
}
