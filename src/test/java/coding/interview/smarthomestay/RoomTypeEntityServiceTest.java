package coding.interview.smarthomestay;

import coding.interview.smarthomestay.entity.RoomTypeEntity;
import coding.interview.smarthomestay.service.RoomTypeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@SpringBootTest
public class RoomTypeEntityServiceTest {

    @Autowired
    private RoomTypeService service;

    @Test
    void testSave() {
        RoomTypeEntity roomTypeEntity1 = new RoomTypeEntity();
        roomTypeEntity1.setName("Single");
        roomTypeEntity1.setPrice(200_000.0);
        service.save(roomTypeEntity1);

        RoomTypeEntity roomTypeEntity2 = new RoomTypeEntity();
        roomTypeEntity2.setName("Twin");
        roomTypeEntity2.setPrice(300_000.0);
        service.save(roomTypeEntity2);

        RoomTypeEntity roomTypeEntity3 = new RoomTypeEntity();
        roomTypeEntity3.setName("Deluxe");
        roomTypeEntity3.setPrice(400_000.0);
        service.save(roomTypeEntity3);

        RoomTypeEntity roomTypeEntity4 = new RoomTypeEntity();
        roomTypeEntity4.setName("Family");
        roomTypeEntity4.setPrice(600_000.0);
        service.save(roomTypeEntity4);
    }

    @Test
    void testFindAll() {
        List<RoomTypeEntity> roomTypeEntityList = service.getAllRoomType();


        roomTypeEntityList.forEach(roomType -> {
            System.out.println(roomType.getId());
            System.out.println(roomType.getName());
            System.out.println(roomType.getPrice());
            System.out.println(roomType.getReservationEntityList());
        });

        Assertions.assertEquals(4, roomTypeEntityList.size());
    }

    @Test
    void testFindById() {
        RoomTypeEntity roomTypeEntity = service.findById("1b0b0c84-5f1d-4c2f-b541-df7de88da243");

        Assertions.assertNotNull(roomTypeEntity);
        Assertions.assertEquals("Family",roomTypeEntity.getName());


        RoomTypeEntity roomTypeEntity2 = service.findById("1b0b0c84-5f1d-4c2f-b541-df7de8sdf8da243");

        Assertions.assertNull(roomTypeEntity2);
    }
}
