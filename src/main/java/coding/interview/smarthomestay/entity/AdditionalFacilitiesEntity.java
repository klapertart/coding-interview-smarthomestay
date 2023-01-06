package coding.interview.smarthomestay.entity;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@Entity
@Table(name = "additional_facilities")
@Data
public class AdditionalFacilitiesEntity {

    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "facilities_id")
    private FacilitiesEntity facilitiesEntity;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "reservation_id", nullable = false)
    private ReservationEntity reservationEntity;

    @Column(name = "other_facilities", length = 100)
    private String otherFacilities;

}
