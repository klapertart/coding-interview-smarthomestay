package coding.interview.smarthomestay;

import coding.interview.smarthomestay.entity.GuestEntity;
import coding.interview.smarthomestay.service.GuestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@SpringBootTest
public class GuestEntityServiceTest {

    @Autowired
    private GuestService service;

    @Test
    void testSave() {
        GuestEntity guestEntity = new GuestEntity();
        guestEntity.setNoIdentity("78984336537");
        guestEntity.setName("Abdillah Hamka");
        guestEntity.setEmail("hamka@gamil.com");
        guestEntity.setPhone("938476738");
        guestEntity.setAddress("Majalengka");

        service.save(guestEntity);
    }
}
