package coding.interview.smarthomestay.repository;

import coding.interview.smarthomestay.entity.ReservationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationEntity,String> {
}
