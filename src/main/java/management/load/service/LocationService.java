package management.load.service;

import lombok.AllArgsConstructor;
import management.load.entities.Location;
import management.load.repositories.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationService {
    private LocationRepository locationRepository;

    public List<Location> getAllLocations(){
        return locationRepository.findAll();
    }

    public String deleteLocation(int id){
        locationRepository.deleteById(id);
        return "Delete Success";
    }

    public String updateLocation(int id,String city){
        Location location = locationRepository.findById(id).get();
        location.setCity(city);
        locationRepository.save(location);
        return "Update Success";
    }
}
