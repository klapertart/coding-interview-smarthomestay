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
public class Checkin {

    @NotBlank
    @JsonProperty("reservationId")
    private String reservationId;

    @NotBlank
    @JsonProperty("noIdentity")
    private String noIdentity;

    @NotBlank
    @JsonProperty("fullName")
    private String fullName;

    @NotBlank
    @Digits(integer = 15,fraction = 0)
    @JsonProperty("phone")
    private String phone;

    @NotBlank
    @JsonProperty("address")
    private String address;

    @JsonProperty("email")
    private String email;

}
