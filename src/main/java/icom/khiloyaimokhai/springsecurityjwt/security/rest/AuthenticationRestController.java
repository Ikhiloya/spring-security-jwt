package icom.khiloyaimokhai.springsecurityjwt.security.rest;


import com.fasterxml.jackson.annotation.JsonProperty;
import icom.khiloyaimokhai.springsecurityjwt.security.jwt.JWTFilter;
import icom.khiloyaimokhai.springsecurityjwt.security.jwt.TokenProvider;
import icom.khiloyaimokhai.springsecurityjwt.security.model.Authority;
import icom.khiloyaimokhai.springsecurityjwt.security.model.User;
import icom.khiloyaimokhai.springsecurityjwt.security.rest.dto.LoginDto;
import icom.khiloyaimokhai.springsecurityjwt.security.rest.dto.ManagedUserVM;
import icom.khiloyaimokhai.springsecurityjwt.security.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

/**
 * Created by Ikhiloya Imokhai on 2019-10-14.
 * <p>
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
public class AuthenticationRestController {


    private final TokenProvider tokenProvider;
    private final UserService userService;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthenticationRestController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserService userService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        boolean rememberMe = (loginDto.isRememberMe() == null) ? false : loginDto.isRememberMe();
        String jwt = tokenProvider.createToken(authentication, rememberMe);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<User> registerAccount(@Valid @RequestBody ManagedUserVM managedUserVM) throws Exception {
//        if (!checkPasswordLength(managedUserVM.getPassword())) {
//            throw new InvalidPasswordException();
//        }
        return new ResponseEntity<>(userService.registerUser(managedUserVM, managedUserVM.getPassword()), (HttpStatus.CREATED));
    }

    @PostMapping("/authority")
    public ResponseEntity<Authority> registerAccount(@Valid @RequestBody Authority authority) throws Exception {
//        if (!checkPasswordLength(managedUserVM.getPassword())) {
//            throw new InvalidPasswordException();
//        }
        return new ResponseEntity<>(userService.saveAuthority(authority), (HttpStatus.CREATED));
    }

    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
