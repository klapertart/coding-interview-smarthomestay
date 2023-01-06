package coding.interview.smarthomestay.validation;

import coding.interview.smarthomestay.data.Gender;
import coding.interview.smarthomestay.data.User;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */
public class UserValidation {
    public static boolean isGenderValid(User user){
        String gender = user.getGender();
        return (gender.toUpperCase().equals(Gender.MALE.name()) || gender.toUpperCase().equals(Gender.FEMALE.name()));
    }
}
