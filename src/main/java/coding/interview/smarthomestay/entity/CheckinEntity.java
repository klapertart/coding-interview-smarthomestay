package coding.interview.smarthomestay.entity;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@Entity
@Table(name = "checkin")
@Data
public class CheckinEntity {

    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @OneToOne
    @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    private ReservationEntity reservationEntity;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "no_identity", nullable = false)
    private GuestEntity guestEntity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "checkin_timestamp", nullable = false)
    private Date checkinTimestamp;
}
