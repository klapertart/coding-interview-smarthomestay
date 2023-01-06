package coding.interview.smarthomestay.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */

@Entity
@Table(name = "guest")
@Data
public class GuestEntity {

    @Id
    @Column(name = "no_identity", unique = true, nullable = false, length = 20)
    private String noIdentity;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 15)
    private String phone;

    @Column(length = 30, unique = true)
    private String email;

    @Column(length = 100)
    private String address;

    @OneToMany(mappedBy = "guestEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CheckinEntity> checkinEntityList;
}
