package br.com.santasecret.security.service;

import br.com.santasecret.security.core.impl.ResourceOwner;
import br.com.santasecret.security.entity.User;
import br.com.santasecret.security.repository.UserRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRespository userRespository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRespository.findByEmail(email);
        if (user != null) {
            return new ResourceOwner(user);
        }
        throw new UsernameNotFoundException("User not found");

    }

    public User findByEmail(String email) {
        return userRespository.findByEmail(email);
    }
}
