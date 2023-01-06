package coding.interview.smarthomestay.service;

import coding.interview.smarthomestay.entity.CheckoutEntity;
import coding.interview.smarthomestay.repository.CheckoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@Service
public class CheckoutService {

    @Autowired
    private CheckoutRepository repository;

    public void save(CheckoutEntity checkoutEntity){
        repository.save(checkoutEntity);
    }
}
