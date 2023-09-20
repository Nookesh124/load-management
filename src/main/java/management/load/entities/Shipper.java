package management.load.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shipper")
public class Shipper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "contactinfo")
    private String contactInfo;

    private int location_a;
    private int location_b;

    @OneToMany(mappedBy = "shipper")
    @JsonIgnore
    private List<Load> loads;
}
