package coding.interview.smarthomestay.repository;

import coding.interview.smarthomestay.entity.CheckoutEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@Repository
public interface CheckoutRepository extends CrudRepository<CheckoutEntity,String> {
}
