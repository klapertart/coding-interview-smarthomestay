package coding.interview.smarthomestay.service;

import coding.interview.smarthomestay.entity.PaymentEntity;
import coding.interview.smarthomestay.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@Service
@Transactional
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public void save(PaymentEntity paymentEntity){
        repository.save(paymentEntity);
    }

    public PaymentEntity findByReservationId(String reservationId){
        PaymentEntity paymentEntity = repository.findByReservationId(reservationId);
        if(paymentEntity != null){
            PaymentEntity payment = new PaymentEntity();
            payment.setId(paymentEntity.getId());
            payment.setPaymentDate(paymentEntity.getPaymentDate());
            payment.setPaymentAmount(paymentEntity.getPaymentAmount());
            payment.setPaymentFlag(paymentEntity.getPaymentFlag());
            payment.setReservationEntity(null);

            return payment;
        }
        return  null;
    }

    public PaymentEntity getInquiry(String reservationId){
         PaymentEntity paymentEntity = repository.getInquiry(reservationId);
         if(paymentEntity != null){
             PaymentEntity payment = new PaymentEntity();
             payment.setId(paymentEntity.getId());
             payment.setPaymentAmount(paymentEntity.getPaymentAmount());
             payment.setPaymentFlag(paymentEntity.getPaymentFlag());
             payment.setPaymentDate(paymentEntity.getPaymentDate());
             payment.setReservationEntity(null);

             return payment;
         }

         return null;
    }

    public void paymentPaid(String paymentId){
        repository.flagPayment(paymentId);
    }
}
