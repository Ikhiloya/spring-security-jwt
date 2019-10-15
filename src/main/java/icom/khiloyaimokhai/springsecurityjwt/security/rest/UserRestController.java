package icom.khiloyaimokhai.springsecurityjwt.security.rest;

import icom.khiloyaimokhai.springsecurityjwt.security.model.User;
import icom.khiloyaimokhai.springsecurityjwt.security.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ikhiloya Imokhai on 2019-10-14.
 */
@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<User> getActualUser() {
        return ResponseEntity.ok(userService.getUserWithAuthorities().get());
    }
}