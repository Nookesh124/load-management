package management.load.service;

import lombok.AllArgsConstructor;
import management.load.entities.Location;
import management.load.repositories.LocationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LocationService {
    private LocationRepository locationRepository;

    public List<Location> getAllLocations(){
        return locationRepository.findAll();
    }

    public String deleteLocation(int id){
        Optional<Location> location = locationRepository.findById(id);
        if(location != null) {
            locationRepository.deleteById(id);
            return "Delete Success";
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public String updateLocation(int id,String city){
        Location location = locationRepository.findById(id).get();
        if(location != null) {
            location.setCity(city);
            locationRepository.save(location);
            return "Update Success";
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
