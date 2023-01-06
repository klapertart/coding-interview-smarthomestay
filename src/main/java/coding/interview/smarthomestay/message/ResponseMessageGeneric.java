package coding.interview.smarthomestay.message;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@AllArgsConstructor
@Data
public class ResponseMessageGeneric {
    private int status;
    private String message;
}
