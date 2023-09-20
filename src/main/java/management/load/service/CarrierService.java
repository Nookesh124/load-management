package management.load.service;

import lombok.AllArgsConstructor;
import management.load.entities.Carrier;
import management.load.entities.Load;
import management.load.repositories.CarrierRepository;
import management.load.repositories.LoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarrierService {
    private CarrierRepository carrierRepository;

    private LoadRepository loadRepository;

    public List<Carrier> getAllCarriers() {
        return carrierRepository.findAll();
    }

    public List<Load> getCarrierLoads(Integer id) {
        return loadRepository.findByCarrierId(id);
    }

    public String deleteCarrier(int carrierId){
        carrierRepository.deleteById(carrierId);
        return "Deleted";
    }

    public String updateCarrier(int carrierId,String contactInfo){
        Carrier carrier = carrierRepository.findById(carrierId).get();
        carrier.setContactInfo(contactInfo);
        carrierRepository.save(carrier);
        return "Updated";
    }
}
