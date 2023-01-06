package coding.interview.smarthomestay;

import coding.interview.smarthomestay.entity.CheckoutEntity;
import coding.interview.smarthomestay.entity.ReservationEntity;
import coding.interview.smarthomestay.entity.RoomTypeEntity;
import coding.interview.smarthomestay.entity.UserEntity;
import coding.interview.smarthomestay.service.CheckoutService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@SpringBootTest
public class CheckoutEntityServiceTest {

    @Autowired
    private CheckoutService service;

    @Test
    void testSave() {
        RoomTypeEntity roomTypeEntity = new RoomTypeEntity();
        roomTypeEntity.setId("01f3ebcb-8667-49af-8c8d-3d38f9b38093");

        UserEntity userEntity = new UserEntity();
        userEntity.setId("2a675949-7490-493c-a470-e023bf69ae64");

        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setId("RSV003406012023");
        reservationEntity.setUserEntity(userEntity);
        reservationEntity.setRoomTypeEntity(roomTypeEntity);
        reservationEntity.setCheckinDate(new Date());
        reservationEntity.setDuration(1);
        reservationEntity.setCheckoutDate(new Date());
        reservationEntity.setTotalPayment(750_000.0);
        reservationEntity.setReservationTimeStamp(new Date());

        CheckoutEntity checkoutEntity = new CheckoutEntity();
        checkoutEntity.setReservationEntity(reservationEntity);
        checkoutEntity.setCheckoutTimestamp(new Date());

        service.save(checkoutEntity);

    }
}
