package coding.interview.smarthomestay;

import coding.interview.smarthomestay.data.Gender;
import coding.interview.smarthomestay.data.User;
import coding.interview.smarthomestay.entity.UserEntity;
import coding.interview.smarthomestay.service.UserService;
import coding.interview.smarthomestay.validation.UserValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Calendar;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Autowired
    private Validator validator;


    @Test
    void testSave() {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR,1990);
        instance.set(Calendar.MONTH,Calendar.SEPTEMBER);
        instance.set(Calendar.DAY_OF_MONTH,19);
        Date date = instance.getTime();

        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("rahasia");
        userEntity.setFullName("Hana Abdillah");
        userEntity.setBirthDate(date);
        userEntity.setEmail("hana@gmail.com");
        userEntity.setGender(Gender.MALE);
        userEntity.setPhone("0282837464");

        service.save(userEntity);
    }

    @Test
    void testFindByEmail() {
        UserEntity userEntity = service.findByEmail("erwin@gmail.com");

        Assertions.assertEquals("f3d5c4ab-8e99-43dd-ae4e-27bd1198454e",userEntity.getId());
        Assertions.assertEquals("Erwin",userEntity.getFullName());

        UserEntity userEntity2 = service.findByEmail("asdfasd");
        Assertions.assertNull(userEntity2);

    }

    @Test
    void TestValidator() {
        User user = new User("", "", "", "", "", new Date());

        Set<ConstraintViolation<User>> validate = validator.validate(user);


        Assertions.assertFalse(validate.isEmpty());
        Assertions.assertEquals(5, validate.size());

    }

    @Test
    void testGender() {

        User user = new User("hamka", "123", "123", "hamka@gamil.com", "blank",new Date());
        Assertions.assertFalse(UserValidation.isGenderValid(user));

        User user2 = new User("hamka", "123", "123", "hamka@gamil.com", "male",new Date());
        Assertions.assertTrue(UserValidation.isGenderValid(user2));

        User user3 = new User("hamka", "123", "123", "hamka@gamil.com", "female",new Date());
        Assertions.assertTrue(UserValidation.isGenderValid(user3));

    }
}
