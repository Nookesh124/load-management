package management.load.controller;

import lombok.AllArgsConstructor;
import management.load.entities.Userinfo;
import management.load.service.UserInfoUserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserInfoController {

    private UserInfoUserDetailsService userDetailsService;

    @PostMapping("/add")
    public String addUser(@RequestBody Userinfo userinfo) {
        return userDetailsService.addUserinfo(userinfo);

    }
}
