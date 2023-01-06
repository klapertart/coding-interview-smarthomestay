package coding.interview.smarthomestay.service;

import coding.interview.smarthomestay.entity.RoomTypeEntity;
import coding.interview.smarthomestay.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@Service
public class RoomTypeService {

    @Autowired
    private RoomTypeRepository repository;

    public void save(RoomTypeEntity roomTypeEntity){
        repository.save(roomTypeEntity);
    }

    public List<RoomTypeEntity> getAllRoomType(){
        List<RoomTypeEntity> roomTypeEntityList = new ArrayList<>();
        repository.findAll().forEach(( roomType) -> {
            RoomTypeEntity tempRT = new RoomTypeEntity();
            tempRT.setId(roomType.getId());
            tempRT.setName(roomType.getName());
            tempRT.setPrice(roomType.getPrice());
            roomTypeEntityList.add(tempRT);
        });

        return roomTypeEntityList;
    }

    public RoomTypeEntity findById(String id){
        Optional<RoomTypeEntity> room = repository.findById(id);

        if(room.isPresent()){
            RoomTypeEntity roomTypeEntity = new RoomTypeEntity();
            room.ifPresent(roomType -> {
                roomTypeEntity.setId(roomType.getId());
                roomTypeEntity.setName(roomType.getName());
                roomTypeEntity.setPrice(roomType.getPrice());
                roomTypeEntity.setReservationEntityList(null);
            });
            return roomTypeEntity;
        }else{
            return null;
        }

    }
}
