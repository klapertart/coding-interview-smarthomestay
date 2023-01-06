package coding.interview.smarthomestay.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

/**
 * @author kurakuraninja
 * @since 06/01/23
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @NotBlank
    @JsonProperty("reservationId")
    private String reservationId;

    @NotBlank
    @Digits(integer = 12,fraction = 2)
    @JsonProperty("amount")
    private String amount;
}
