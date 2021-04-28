package br.com.santasecret.security.service;

import br.com.santasecret.security.entity.User;
import br.com.santasecret.security.repository.UserRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRespository userRespository;
/*
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRespository.findByEmail(email);
        if (user != null) {
            return new ResourceOwner(user);
        }
        throw new UsernameNotFoundException("User not found");

    }*/

    public User findByEmail(String email) {
        return userRespository.findByEmail(email);
    }
}
