package coding.interview.smarthomestay.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @NotBlank
    @JsonProperty("userId")
    private String userId;

    @NotBlank
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    @JsonProperty("checkinDate")
    private String checkinDate;

    @NotBlank
    @Digits(integer = 2,fraction = 0)
    @JsonProperty("duration")
    private String duration;

    @NotBlank
    @JsonProperty("roomType")
    private String roomType;

    @JsonProperty("additionalFacilities1")
    private String additionalFacilities1;

    @JsonProperty("additionalFacilities2")
    private String additionalFacilities2;

    @JsonProperty("additionalFacilities3")
    private String additionalFacilities3;

    @JsonProperty("additionalFacilities4")
    private String additionalFacilities4;

    @JsonProperty("otherAdditionalFacilities")
    private String otherAdditionalFacilities;

}
