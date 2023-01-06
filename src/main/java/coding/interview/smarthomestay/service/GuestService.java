package coding.interview.smarthomestay.service;

import coding.interview.smarthomestay.entity.GuestEntity;
import coding.interview.smarthomestay.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@Service
public class GuestService {

    @Autowired
    private GuestRepository repository;

    public void save(GuestEntity guestEntity){
        repository.save(guestEntity);
    }
}
