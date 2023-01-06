package coding.interview.smarthomestay.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@Entity
@Table(name = "room_type")
@Data
public class RoomTypeEntity {

    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(nullable = false)
    private double price;

    @OneToMany(mappedBy = "roomTypeEntity")
    private List<ReservationEntity> reservationEntityList;
}
