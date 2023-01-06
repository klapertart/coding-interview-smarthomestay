package coding.interview.smarthomestay.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@Entity
@Table(name = "checkout")
@Data
public class CheckoutEntity {

    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @OneToOne
    @JoinColumn(name = "reservation_id",referencedColumnName = "id")
    private ReservationEntity reservationEntity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "checkout_timestamp", nullable = false)
    private Date checkoutTimestamp;
}
