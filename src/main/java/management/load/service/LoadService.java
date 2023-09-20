package management.load.service;

import management.load.entities.Load;
import management.load.entities.Shipper;
import management.load.repositories.LoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LoadService {

    @Autowired
    private LoadRepository loadRepository;

    public List<Load> getAllLoads(){
        return loadRepository.findAll();
    }

    public Load getLoadsInLocations(int id1,int id2){
        return loadRepository.getLoads(id1,id2);
    }

    public List<Integer> getShipperForCarrier(Integer id){
        return loadRepository.getShipperForCarrier(id);
    }

    public String deleteLoad(int id){
        loadRepository.deleteById(id);
        return "Delete success";
    }

    public String updateLoad(int id,int amount){
        Load load = loadRepository.findById(id).get();
        load.setAmount(amount);
        loadRepository.save(load);
        return "Update success";
    }

    public String addLoad(Load load){
        loadRepository.save(load);
        return "Added Sucessfully";
    }

}
