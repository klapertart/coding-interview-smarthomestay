package coding.interview.smarthomestay.service;

import coding.interview.smarthomestay.entity.UserEntity;
import coding.interview.smarthomestay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */


@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void save(UserEntity userEntity){
        repository.save(userEntity);
    }

    public UserEntity findByEmail(String email){
        UserEntity userEntity = repository.findByEmail(email);
        if (userEntity != null){
            userEntity.setPassword(null);
            userEntity.setReservationEntityList(null);
        }
        return userEntity;
    }
}
