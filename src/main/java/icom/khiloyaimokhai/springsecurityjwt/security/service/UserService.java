package icom.khiloyaimokhai.springsecurityjwt.security.service;

import icom.khiloyaimokhai.springsecurityjwt.security.SecurityUtils;
import icom.khiloyaimokhai.springsecurityjwt.security.model.Authority;
import icom.khiloyaimokhai.springsecurityjwt.security.model.User;
import icom.khiloyaimokhai.springsecurityjwt.security.repository.AuthorityRepository;
import icom.khiloyaimokhai.springsecurityjwt.security.repository.UserRepository;
import icom.khiloyaimokhai.springsecurityjwt.security.rest.dto.UserDTO;
import icom.khiloyaimokhai.springsecurityjwt.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


/**
 * Created by Ikhiloya Imokhai on 2019-10-14.
 */
@Service
@Transactional
public class UserService {
    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }


    public User registerUser(UserDTO userDTO, String password) throws Exception {

        try {
//            Authority authority = new Authority(Constants.USER);
//            authorityRepository.save(authority);

//            userRepository.findOneByLogin(userDTO.getLogin().toLowerCase()).ifPresent(existingUser -> {
//                boolean removed = removeNonActivatedUser(existingUser);
//                if (!removed) {
//                    throw new LoginAlreadyUsedException();
//                }
//            });
//            userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).ifPresent(existingUser -> {
//                boolean removed = removeNonActivatedUser(existingUser);
//                if (!removed) {
//                    throw new EmailAlreadyUsedException();
//                }
//            });
            User newUser = new User();
            String encryptedPassword = passwordEncoder.encode(password);
            newUser.setUsername(userDTO.getLogin().toLowerCase());
            // new user gets initially a generated password
            newUser.setPassword(encryptedPassword);
            newUser.setFirstname(userDTO.getFirstName());
            newUser.setLastname(userDTO.getLastName());
            newUser.setEmail(userDTO.getEmail().toLowerCase());
//            newUser.setAvatarUrl(userDTO.getImageUrl());
//            newUser.setLangKey(userDTO.getLangKey());
            // new user is not active
            newUser.setActivated(true);
            // new user gets registration key
//            newUser.setActivationKey(RandomUtil.generateActivationKey());
            Set<Authority> authorities = new HashSet<>();
//
//            if (authorityRepository.findByName(Constants.USER).isPresent()) {
//                System.out.println("::::::::Exist::::" + true);
//            }

            authorityRepository.findById(Constants.USER).ifPresent(authorities::add);

            newUser.setAuthorities(authorities);
            User savedUser = userRepository.save(newUser);
//        this.clearUserCaches(newUser);
            log.debug("Created Information for User: {}", newUser);

//            mailService.sendActivationEmail(newUser);

//            response.setResponseCode("200");
////            response.setResponseMessage("Registration successful!");
////            response.setData(newUser);

            return savedUser;
        } catch (Exception e) {
            e.printStackTrace();
//            response.setResponseCode("999");
//            response.setResponseMessage("user registration failed: " + e.getLocalizedMessage());
        }

        return null;

    }

    public Authority saveAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }
}
