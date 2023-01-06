package coding.interview.smarthomestay.repository;

import coding.interview.smarthomestay.entity.PaymentEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@Repository
public interface PaymentRepository extends CrudRepository<PaymentEntity,String> {

    @Query("SELECT pe FROM PaymentEntity pe  " +
            "JOIN pe.reservationEntity re " +
            "WHERE re.id=:id")
    PaymentEntity findByReservationId(@Param(value = "id") String id);

    @Query("SELECT pe FROM PaymentEntity pe  " +
            "JOIN pe.reservationEntity re " +
            "WHERE re.id=:id AND payment_flag=0")
    PaymentEntity getInquiry(@Param(value = "id") String id);

    @Modifying
    @Query("UPDATE PaymentEntity pe " +
            "SET pe.paymentDate=NOW(), pe.paymentFlag=1 " +
            "WHERE pe.id=:id ")
    void flagPayment(@Param(value = "id") String id);

}
