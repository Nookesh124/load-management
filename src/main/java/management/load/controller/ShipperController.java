package management.load.controller;

import lombok.AllArgsConstructor;
import management.load.entities.Load;
import management.load.entities.Shipper;
import management.load.repositories.ShipperRepository;
import management.load.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/shipper")
public class ShipperController {

    private ShipperService shipperService;
    private ShipperRepository shipperRepository;

    @GetMapping
    public List<Shipper> getShippers(){
        return shipperService.getAllShippers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public List<Load> getLoadByShipper(@PathVariable("id") int id){
        return shipperService.getShipperLoads(id);
    }

    @GetMapping("/location")
    @PreAuthorize("hasAuthority('admin')")
    public Shipper getShipperByLocations(@RequestParam("locationA") int locationA,@RequestParam("locationB") int locationB){
        return shipperService.getShipperByLocations(locationA,locationB);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('admin')")
    public Shipper addNewShipper(@RequestBody Shipper shipper){
        return shipperRepository.save(shipper);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('admin')")
    public String deleteShipper(@RequestParam("id") int id){
        return shipperService.deleteShipper(id);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('admin')")
    public String updateShipper(@RequestParam("id") int id,@RequestParam("name") String name){
        return shipperService.updateShipper(id, name);
    }
}
