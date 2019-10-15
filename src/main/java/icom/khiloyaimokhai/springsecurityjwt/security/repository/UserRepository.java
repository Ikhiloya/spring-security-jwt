package icom.khiloyaimokhai.springsecurityjwt.security.repository;

import icom.khiloyaimokhai.springsecurityjwt.security.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Ikhiloya Imokhai on 2019-10-14.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByUsername(String username);

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByEmailIgnoreCase(String email);


    Optional<User> findOneByUsername(String login);


    Optional<User> findOneByEmailIgnoreCase(String email);


}
