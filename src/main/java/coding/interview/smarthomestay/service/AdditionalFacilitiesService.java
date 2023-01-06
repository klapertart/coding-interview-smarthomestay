package coding.interview.smarthomestay.service;

import coding.interview.smarthomestay.entity.AdditionalFacilitiesEntity;
import coding.interview.smarthomestay.repository.AdditionalFacilitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@Service
public class AdditionalFacilitiesService {

    @Autowired
    private AdditionalFacilitiesRepository repository;

    public void save(AdditionalFacilitiesEntity additionalFacilitiesEntity){
        repository.save(additionalFacilitiesEntity);
    }
}
