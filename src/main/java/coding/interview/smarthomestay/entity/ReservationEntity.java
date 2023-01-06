package coding.interview.smarthomestay.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@Entity
@Table(name = "reservation")
@Data
public class ReservationEntity {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "room_type", nullable = false)
    private RoomTypeEntity roomTypeEntity;

    @Temporal(TemporalType.DATE)
    @Column(name = "checkin_date", nullable = false)
    private Date checkinDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "checkout_date", nullable = false)
    private Date checkoutDate;

    @Column(nullable = false)
    private int duration;

    @Column(name = "total_payment", nullable = false)
    private Double totalPayment;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reservation_timestamp", nullable = false)
    private Date reservationTimeStamp;

    @OneToOne(mappedBy = "reservationEntity")
    private CheckoutEntity checkoutEntity;

    @OneToOne(mappedBy = "reservationEntity")
    private CheckinEntity checkinEntity;

    @OneToOne(mappedBy = "reservationEntity")
    private PaymentEntity paymentEntity;

    @OneToMany(mappedBy = "reservationEntity",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<AdditionalFacilitiesEntity> additionalFacilityEntities;
}
