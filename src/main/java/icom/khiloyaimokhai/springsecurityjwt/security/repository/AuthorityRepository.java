package icom.khiloyaimokhai.springsecurityjwt.security.repository;

import icom.khiloyaimokhai.springsecurityjwt.security.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Ikhiloya Imokhai on 2019-10-14.
 */

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {

//    Optional<Authority> findByName(String name);
}
