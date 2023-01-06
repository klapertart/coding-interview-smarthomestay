package coding.interview.smarthomestay;

import coding.interview.smarthomestay.data.PaymentFlag;
import coding.interview.smarthomestay.entity.PaymentEntity;
import coding.interview.smarthomestay.entity.ReservationEntity;
import coding.interview.smarthomestay.entity.RoomTypeEntity;
import coding.interview.smarthomestay.entity.UserEntity;
import coding.interview.smarthomestay.service.PaymentService;
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
public class PaymenServiceTest {

    @Autowired
    private PaymentService service;

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

        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setReservationEntity(reservationEntity);
        paymentEntity.setPaymentAmount(750_000.0);
        paymentEntity.setPaymentDate(new Date());
        paymentEntity.setPaymentFlag(PaymentFlag.PAID);

        service.save(paymentEntity);
    }

    @Test
    void testUpdate() {
        service.paymentPaid("af42edea-c51d-4951-94c7-903f9a0a1dac");
    }

    @Test
    void testCheckPayment() {
        PaymentEntity paymentEntity = service.getInquiry("RSV032406012023");

        Assertions.assertNull(paymentEntity);
    }
}
