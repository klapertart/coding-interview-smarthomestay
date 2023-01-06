package coding.interview.smarthomestay.data;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @NotBlank
    @JsonProperty("fullName")
    private String fullName;

    @NotBlank
    @JsonProperty("password")
    private String password;

    @NotBlank
    @Digits(integer = 15,fraction = 0)
    @JsonProperty("phone")
    private String phone;

    @NotBlank
    @Email
    @JsonProperty("email")
    private String email;

    @NotBlank
    @JsonProperty("gender")
    private String gender;

    @JsonProperty("birthDate")
    private Date  birthDate;
}
