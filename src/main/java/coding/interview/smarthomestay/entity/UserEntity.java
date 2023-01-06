package coding.interview.smarthomestay.entity;

import coding.interview.smarthomestay.data.Gender;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@Entity
@Table(name = "user")
@Data
public class UserEntity {

    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false, length = 30)
    private String password;

    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;

    @Column(unique = true, length = 15)
    private String phone;

    @Column(unique = true, length = 30)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @OneToMany(mappedBy = "userEntity")
    private List<ReservationEntity> reservationEntityList;
}
