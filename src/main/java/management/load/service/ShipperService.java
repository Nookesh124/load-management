package management.load.service;

import lombok.AllArgsConstructor;
import management.load.entities.Load;
import management.load.entities.Shipper;
import management.load.repositories.LoadRepository;
import management.load.repositories.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipperService {
    @Autowired
    private ShipperRepository shipperRepository;
    @Autowired
    private LoadRepository loadRepository;

    public List<Shipper> getAllShippers(){
        return shipperRepository.findAll();
    }

    public List<Load> getShiperLoads(int id){
        return loadRepository.findByShipperId(id);
    }

    public Shipper getShipperByLocations(int locationA,int locationB){
        return shipperRepository.getShipper(locationA,locationB);
    }

    public String deleteShipper(int id){
        shipperRepository.deleteById(id);
        return "Delete success";
    }

    public String updateShipper(int id,String name){
        Shipper shipper = shipperRepository.findById(id).get();
        shipper.setName(name);
        shipperRepository.save(shipper);
        return "Update success";
    }
}
