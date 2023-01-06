package coding.interview.smarthomestay;

import coding.interview.smarthomestay.data.Reservation;
import coding.interview.smarthomestay.entity.ReservationEntity;
import coding.interview.smarthomestay.entity.RoomTypeEntity;
import coding.interview.smarthomestay.entity.UserEntity;
import coding.interview.smarthomestay.service.ReservationService;
import coding.interview.smarthomestay.util.Common;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@SpringBootTest
public class ReservationEntityServiceTest {

    @Autowired
    private ReservationService service;

    @Test
    void testSave() {
        RoomTypeEntity roomTypeEntity = new RoomTypeEntity();
        roomTypeEntity.setId("01f3ebcb-8667-49af-8c8d-3d38f9b38093");

        UserEntity userEntity = new UserEntity();
        userEntity.setId("2a675949-7490-493c-a470-e023bf69ae64");

        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setId(Common.getReservationCode());
        reservationEntity.setUserEntity(userEntity);
        reservationEntity.setRoomTypeEntity(roomTypeEntity);
        reservationEntity.setCheckinDate(new Date());
        reservationEntity.setDuration(1);
        reservationEntity.setCheckoutDate(new Date());
        reservationEntity.setTotalPayment(750_000.0);
        reservationEntity.setReservationTimeStamp(new Date());

        service.save(reservationEntity);
    }

    @Test
    void testIsPresent() {
        Assertions.assertTrue(service.isPresent("RSV112405012023"));
        Assertions.assertFalse(service.isPresent("xxxxxxx"));
    }

    @Test
    void testFindById() {
        ReservationEntity reservationEntity = null;
        reservationEntity = service.findById("RSV112405012023");

        Assertions.assertNotNull(reservationEntity);

        Assertions.assertEquals(1, reservationEntity.getDuration());
        Assertions.assertEquals("2677f189-46ee-4495-ab01-f0b4801ea608", reservationEntity.getUserEntity().getId());
    }

    @Test
    void testValidation() {
        Reservation reservation = new Reservation();
        reservation.setUserId("asdf");
        reservation.setDuration("1");
        reservation.setCheckinDate("2345-12-12");
        reservation.setRoomType("asdf");

        Assertions.assertNull(reservation.getAdditionalFacilities1());

    }
}
