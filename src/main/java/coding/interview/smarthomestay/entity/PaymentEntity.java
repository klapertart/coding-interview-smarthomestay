package coding.interview.smarthomestay.entity;

import coding.interview.smarthomestay.data.PaymentFlag;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@Entity
@Table(name = "payment")
@Data
public class PaymentEntity {

    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @OneToOne
    @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    private ReservationEntity reservationEntity;

    @Column(name = "payment_amount", nullable = false)
    private Double paymentAmount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "payment_date")
    private Date paymentDate;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "payment_flag", nullable = false)
    private PaymentFlag paymentFlag;
}
