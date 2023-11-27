package management.load.controller;

import management.load.dto.AuthRequest;
import management.load.dto.JwtResponse;
import management.load.dto.RefreshTokenRequest;
import management.load.entities.Load;
import management.load.entities.RefreshToken;
import management.load.service.JwtService;
import management.load.service.LoadService;
import management.load.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/load")
public class LoadController {
    @Autowired
    private LoadService loadService;

    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping
    public List<Load> getAllLoads() {
        return loadService.getAllLoads();
    }

    @GetMapping("/{id1}/{id2}")
    @PreAuthorize("hasAuthority('admin')")
    public Load getLocationLoads(@PathVariable("id1") int id1, @PathVariable("id2") int id2) {
        return loadService.getLoadsInLocations(id1, id2);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public List<Load> getShipperForCarrier(@PathVariable("id") Integer id) throws Exception {
        return loadService.getShipperForCarrier(id);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('admin')")
    public String deleteLoad(@RequestParam("id") int id){
        return loadService.deleteLoad(id);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('admin')")
    public String updateLoad(@RequestParam("id") int id,@RequestParam("amount") int amount){
        return loadService.updateLoad(id,amount);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('admin')")
    public String addLoad(@RequestBody Load load){
        return loadService.addLoad(load);
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        if(authentication.isAuthenticated()) {
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequest.getUsername());
            return JwtResponse.builder()
                    .accessToken(jwtService.generateToken(authRequest.getUsername()))
                    .token(refreshToken.getToken()).build();
        }else{
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

    @PostMapping("/refreshToken")
    public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return refreshTokenService.findByToken(refreshTokenRequest.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserinfo)
                .map(userinfo -> {
                    String accessToken = jwtService.generateToken(userinfo.getUsername());
                    return JwtResponse.builder()
                            .accessToken(accessToken)
                            .token(refreshTokenRequest.getToken()).build();
                }).orElseThrow(()->new RuntimeException("Refresh token is not in database"));
    }
}
