package management.load.repositories;

import management.load.entities.Shipper;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShipperRepository extends JpaRepository<Shipper,Integer> {

    @Query("From Shipper where location_a = :locationA and location_b = :locationB")
    public Shipper getShipper(@Param("locationA") int locationA,@Param("locationB") int locationB);
}
