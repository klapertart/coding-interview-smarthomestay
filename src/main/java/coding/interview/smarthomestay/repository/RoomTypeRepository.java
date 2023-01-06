package coding.interview.smarthomestay.repository;

import coding.interview.smarthomestay.entity.RoomTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@Repository
public interface RoomTypeRepository extends CrudRepository<RoomTypeEntity,String> {
}
