package management.load.controller;

import lombok.AllArgsConstructor;
import management.load.entities.Location;
import management.load.repositories.LocationRepository;
import management.load.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/location")
public class LocationController {
    private LocationService locationService;

    private LocationRepository locationRepository;

    @GetMapping
    public List<Location> getLocations(){
        return locationService.getAllLocations();
    }

    @PostMapping("/add")
    public Location addLocation(@RequestBody Location location){
        return locationRepository.save(location);
    }

    @DeleteMapping("/delete")
    public String deleteLocation(@RequestParam("id") int id){
        return locationService.deleteLocation(id);
    }

    @PutMapping("/update")
    public String updateLocation(@RequestParam("id") int id,@RequestParam("city") String city){
        return locationService.updateLocation(id, city);
    }
}
