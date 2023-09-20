package management.load.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "load")
public class Load {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;

    private int weight;
    private int amount;
    @Column(name = "pickupid")
    private int pickupId;
    @Column(name = "deliveryid")
    private int deliveryId;

    @Column(name = "shipperid")
    private int shipperId;
    @Column(name = "carrierid")
    private Integer carrierId;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "shipperid",referencedColumnName = "id",updatable = false,insertable = false)
    @JsonIgnore
    private Shipper shipper;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "carrierid" , referencedColumnName = "id",insertable = false,updatable = false)
    private Carrier carrier;


}
