package br.com.santasecret.security.repository;

import br.com.santasecret.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRespository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
