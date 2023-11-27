package management.load.controller;

import lombok.AllArgsConstructor;
import management.load.entities.Carrier;
import management.load.entities.Load;
import management.load.entities.Shipper;
import management.load.repositories.CarrierRepository;
import management.load.service.CarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carrier")
public class CarrierController {

    @Autowired
    private CarrierService carrierService;

    @Autowired
    private CarrierRepository carrierRepository;

    @GetMapping
    public List<Carrier> getCarriers(){
        return carrierService.getAllCarriers();
    }

    @GetMapping("/{id}")
   // @PreAuthorize("hasAuthority('user')")
    public List<Load> getListOfLoads(@PathVariable("id") Integer id){
        Optional<Carrier> load = carrierRepository.findById(id);
        if(load != null) {
            return carrierService.getCarrierLoads(id);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('user')")
    public Carrier addNewCarrier(@RequestBody Carrier carrier){
        Carrier carrier1 = carrierRepository.findById(carrier.getId()).get();
        if(carrier1 == null) {
            return carrierRepository.save(carrier);
        }else{
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED);
        }
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('user')")
    public String deleteCarrier(@RequestParam("carrierId") int id){
        Optional<Carrier> carrier = carrierRepository.findById(id);
        if(carrier != null) {
            return carrierService.deleteCarrier(id);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('user')")
    public String updateCarrier(@RequestParam("id") int id,@RequestParam("contactInfo") String info){
        Optional<Carrier> carrier = carrierRepository.findById(id);
        if(carrier != null) {
            return carrierService.updateCarrier(id, info);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
