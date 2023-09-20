package management.load.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import management.load.config.UserInfoUserDetails;
import management.load.entities.Userinfo;
import management.load.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public String addUserinfo(Userinfo userinfo){
        userinfo.setPassword(passwordEncoder.encode(userinfo.getPassword()));
        userInfoRepository.save(userinfo);
        return "Added";
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Userinfo> userInfo = userInfoRepository.findByName(username);
        return userInfo.map(UserInfoUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found "+username));
    }
}
