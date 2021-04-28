package br.com.santasecret.security.config;

import br.com.santasecret.security.entity.User;
import br.com.santasecret.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.checkedMap;

@Component
@Primary
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final UserService userService;

    private final PasswordEncoder encoder;

    private final ClientDetailsService clientDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Map<String, String> reqParam = checkedMap((Map<String, String>) authentication.getDetails(), String.class, String.class);
        String userEmail = !StringUtils.isEmpty(reqParam.get("user")) ? reqParam.get("user") : authentication.getName();
        String credential = authentication.getCredentials().toString();

        User user = userService.findByEmail(userEmail);
        if (user == null || !encoder.matches(credential, user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password!");
        }

        final List<GrantedAuthority> grants = new ArrayList<>(1);
        user.getRoles().forEach(role -> {
            grants.add(new SimpleGrantedAuthority(role.getName()));
        });
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, grants);
        usernamePasswordAuthenticationToken.setDetails(user);
        return new UsernamePasswordAuthenticationToken(user, null, grants);
    }
}
