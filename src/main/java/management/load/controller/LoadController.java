package management.load.controller;

import management.load.dto.AuthRequest;
import management.load.entities.Load;
import management.load.service.JwtService;
import management.load.service.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/load")
public class LoadController {
    @Autowired
    private LoadService loadService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public List<Load> getAllLoads() {
        return loadService.getAllLoads();
    }

    @GetMapping("/{id1}/{id2}")
    public Load getLocationLoads(@PathVariable("id1") int id1, @PathVariable("id2") int id2) {
        return loadService.getLoadsInLocations(id1, id2);
    }

    @GetMapping("/{id}")
    public List<Integer> getShipperForCarrier(@PathVariable("id") Integer id) throws Exception {
        return loadService.getShipperForCarrier(id);
    }

    @DeleteMapping("/delete")
    public String deleteLoad(@RequestParam("id") int id){
        return loadService.deleteLoad(id);
    }

    @PutMapping("/update")
    public String updateLoad(@RequestParam("id") int id,@RequestParam("amount") int amount){
        return loadService.updateLoad(id,amount);
    }

    @PostMapping("/add")
    public String addLoad(@RequestBody Load load){
        return loadService.addLoad(load);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        }else{
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}
