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
@Table(name = "carrier")
public class
Carrier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "contactinfo")
    private String contactInfo;
    @OneToMany(mappedBy = "carrier")
    @JsonIgnore
    private List<Load> loads;
}
