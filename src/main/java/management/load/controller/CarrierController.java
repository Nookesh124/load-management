package management.load.controller;

import lombok.AllArgsConstructor;
import management.load.entities.Carrier;
import management.load.entities.Load;
import management.load.entities.Shipper;
import management.load.repositories.CarrierRepository;
import management.load.service.CarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carrier")
public class CarrierController {

    @Autowired
    private CarrierService carrierService;

    @Autowired
    private CarrierRepository carrierRepository;

    public List<Carrier> getCarriers(){
        return carrierService.getAllCarriers();
    }

    @GetMapping("/{id}")
    public List<Load> getListOfLoads(@PathVariable("id") Integer id){
        return carrierService.getCarrierLoads(id);
    }

    @PostMapping("/add")
    public Carrier addNewCarrier(@RequestBody Carrier carrier){
        return carrierRepository.save(carrier);
    }

    @DeleteMapping("/delete")
    public String deleteCarrier(@RequestParam("carrierId") int id){
        return carrierService.deleteCarrier(id);
    }

    @PutMapping("/update")
    public String updateCarrier(@RequestParam("id") int id,@RequestParam("contactInfo") String info){
        return carrierService.updateCarrier(id,info);
    }
}
