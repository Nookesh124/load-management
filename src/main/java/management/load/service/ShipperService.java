package management.load.service;

import lombok.AllArgsConstructor;
import management.load.entities.Load;
import management.load.entities.Shipper;
import management.load.repositories.LoadRepository;
import management.load.repositories.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ShipperService {
    @Autowired
    private ShipperRepository shipperRepository;
    @Autowired
    private LoadRepository loadRepository;

    public List<Shipper> getAllShippers(){
        return shipperRepository.findAll();
    }

    public List<Load> getShipperLoads(int id){
        List<Load> load = loadRepository.findByShipperId(id);
        if(!load.isEmpty()){
            return load;
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Shipper getShipperByLocations(int locationA,int locationB){
        Shipper shipper = shipperRepository.getShipper(locationA,locationB);
        if(shipper != null){
            return shipper;
        }else{
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

    public String deleteShipper(int id){
        Optional<Shipper> shipper = shipperRepository.findById(id);
        if(shipper != null) {
            shipperRepository.deleteById(id);
            return "Delete success";
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public String updateShipper(int id,String name){
        Shipper shipper = shipperRepository.findById(id).get();
        if(shipper != null) {
            shipper.setName(name);
            shipperRepository.save(shipper);
            return "Update success";
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
