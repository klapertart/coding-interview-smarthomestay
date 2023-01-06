package coding.interview.smarthomestay.service;

import coding.interview.smarthomestay.entity.FacilitiesEntity;
import coding.interview.smarthomestay.repository.FacilitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@Service
public class FacilitiesService {

    @Autowired
    private FacilitiesRepository repository;

    public void save(FacilitiesEntity facilitiesEntity){
        repository.save(facilitiesEntity);
    }

    public List<FacilitiesEntity> getAllFacilities(){
        List<FacilitiesEntity> facilitiesEntityList = new ArrayList<>();
         repository.findAll().forEach(facilitiesEntity -> {
             FacilitiesEntity facilities = new FacilitiesEntity();
             facilities.setId(facilitiesEntity.getId());
             facilities.setName(facilitiesEntity.getName());
             facilitiesEntityList.add(facilities);
         });

         return facilitiesEntityList;
    }
}
