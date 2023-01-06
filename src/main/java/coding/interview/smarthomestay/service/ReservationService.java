package coding.interview.smarthomestay.service;

import coding.interview.smarthomestay.entity.AdditionalFacilitiesEntity;
import coding.interview.smarthomestay.entity.PaymentEntity;
import coding.interview.smarthomestay.entity.ReservationEntity;
import coding.interview.smarthomestay.repository.AdditionalFacilitiesRepository;
import coding.interview.smarthomestay.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@Service
@Slf4j
public class ReservationService {

    @Autowired
    private ReservationRepository repository;

    @Autowired
    private AdditionalFacilitiesService additionalFacilitiesService;

    @Autowired
    private PaymentService paymentService;

    public void save(ReservationEntity reservationEntity){
        repository.save(reservationEntity);
    }

    public boolean isPresent(String reservationId){
        return repository.findById(reservationId).isPresent();
    }

    public ReservationEntity findById(String reservationId){
        return repository.findById(reservationId).get();
    }

    public void createReservation(ReservationEntity reservationEntity, PaymentEntity paymentEntity, List<AdditionalFacilitiesEntity> addFacilitiesEntityList){
        // save reservation
        save(reservationEntity);

        // save additional facilities
        addFacilitiesEntityList.forEach(additionalFacilitiesEntity -> {
            additionalFacilitiesService.save(additionalFacilitiesEntity);
        });

        // save payment
        paymentService.save(paymentEntity);

    }
}
