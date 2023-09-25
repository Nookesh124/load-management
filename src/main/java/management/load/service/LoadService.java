package management.load.service;

import management.load.entities.Load;
import management.load.entities.Shipper;
import management.load.repositories.LoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class LoadService {

    @Autowired
    private LoadRepository loadRepository;

    public List<Load> getAllLoads(){
        return loadRepository.findAll();
    }

    public Load getLoadsInLocations(int id1,int id2){
        Load load = loadRepository.getLoads(id1,id2);
        if(load != null){
            return load;
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<Load> getShipperForCarrier(Integer id){
        List<Load> load = loadRepository.getShipperForCarrier(id);
        if(load != null){
            return load;
        }else{
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

    public String deleteLoad(int id){
        Optional<Load> load = loadRepository.findById(id);
        if(load != null) {
            loadRepository.deleteById(id);
            return "Delete success";
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public String updateLoad(int id,int amount){
        Load load = loadRepository.findById(id).get();
        if(load != null) {
            load.setAmount(amount);
            loadRepository.save(load);
            return "Update success";
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public String addLoad(Load load){
        Optional<Load> load1 = loadRepository.findById(load.getId());
        if(load1 == null) {
            loadRepository.save(load);
            return "Added Sucessfully";
        }else{
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED);
        }
    }

}
