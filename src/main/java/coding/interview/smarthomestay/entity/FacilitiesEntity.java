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
@Table(name = "facilities")
@Data
public class FacilitiesEntity {

    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    private String id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @OneToMany(mappedBy = "facilitiesEntity",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<AdditionalFacilitiesEntity> additionalFacilityEntities;
}
