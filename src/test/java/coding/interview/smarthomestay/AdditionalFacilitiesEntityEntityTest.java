package coding.interview.smarthomestay;

import coding.interview.smarthomestay.entity.AdditionalFacilitiesEntity;
import coding.interview.smarthomestay.entity.FacilitiesEntity;
import coding.interview.smarthomestay.entity.ReservationEntity;
import coding.interview.smarthomestay.service.AdditionalFacilitiesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@SpringBootTest
public class AdditionalFacilitiesEntityEntityTest {

    @Autowired
    private AdditionalFacilitiesService service;

    @Test
    void testSave() {
        FacilitiesEntity facilitiesEntity = new FacilitiesEntity();
        facilitiesEntity.setId("308e2dbf-815f-43e5-9ec2-07ae97e6cc06");

        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setId("RSV003406012023");

        AdditionalFacilitiesEntity additionalFacilitiesEntity = new AdditionalFacilitiesEntity();
        additionalFacilitiesEntity.setFacilitiesEntity(facilitiesEntity);
        additionalFacilitiesEntity.setReservationEntity(reservationEntity);
        additionalFacilitiesEntity.setOtherFacilities(null);

        service.save(additionalFacilitiesEntity);
    }
}
