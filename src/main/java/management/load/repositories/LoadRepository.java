package management.load.repositories;

import management.load.entities.Load;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoadRepository extends JpaRepository<Load,Integer> {

    List<Load> findByShipperId(int shipperid);

    List<Load> findByCarrierId(int carrierid);

    @Query("select l from Load l where carrierId=:id")
    public List<Load> getShipperForCarrier(@Param("id") Integer id);

    @Query("from Load where pickupId=:id1 and deliveryId=:id2")
    public Load getLoads(@Param("id1")int id1,@Param("id2") int id2);
}
