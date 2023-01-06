package coding.interview.smarthomestay;

import coding.interview.smarthomestay.entity.FacilitiesEntity;
import coding.interview.smarthomestay.service.FacilitiesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@SpringBootTest
public class FacilitiesEntityServiceTest {

    @Autowired
    private FacilitiesService service;

    @Test
    void testSave() {
        FacilitiesEntity facilitiesEntity1 = new FacilitiesEntity();
        facilitiesEntity1.setName("Breakfast");
        service.save(facilitiesEntity1);

        FacilitiesEntity facilitiesEntity2 = new FacilitiesEntity();
        facilitiesEntity2.setName("Extra Bed");
        service.save(facilitiesEntity2);

        FacilitiesEntity facilitiesEntity3 = new FacilitiesEntity();
        facilitiesEntity3.setName("High Floor");
        service.save(facilitiesEntity3);

        FacilitiesEntity facilitiesEntity4 = new FacilitiesEntity();
        facilitiesEntity4.setName("Non-smoking Room");
        service.save(facilitiesEntity4);
    }

    @Test
    void testGetAllFacilities() {
        List<FacilitiesEntity> facilitiesEntityList = new ArrayList<>();
        facilitiesEntityList = service.getAllFacilities();
        Assertions.assertEquals(4, facilitiesEntityList.size());
    }
}
