package coding.interview.smarthomestay;

import coding.interview.smarthomestay.entity.*;
import coding.interview.smarthomestay.service.CheckinService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@SpringBootTest
public class CheckinEntityServiceTest {

    @Autowired
    private CheckinService service;

    @Test
    void test() {
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

        GuestEntity guestEntity = new GuestEntity();
        guestEntity.setNoIdentity("78984336537");

        CheckinEntity checkinEntity = new CheckinEntity();
        checkinEntity.setReservationEntity(reservationEntity);
        checkinEntity.setGuestEntity(guestEntity);
        checkinEntity.setCheckinTimestamp(new Date());

        service.save(checkinEntity);
    }
}

