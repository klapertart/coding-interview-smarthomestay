package coding.interview.smarthomestay.service;

import coding.interview.smarthomestay.entity.CheckinEntity;
import coding.interview.smarthomestay.entity.GuestEntity;
import coding.interview.smarthomestay.repository.CheckinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@Service
public class CheckinService {

    @Autowired
    private CheckinRepository repository;

    @Autowired
    private GuestService guestService;

    public void save(CheckinEntity checkinEntity){
        repository.save(checkinEntity);
    }

    public void saveGuestAndCheckin(GuestEntity guestEntity, CheckinEntity checkinEntity){
        guestService.save(guestEntity);
        save(checkinEntity);
    }

}
