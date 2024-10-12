package com.plavsic.network.authorization.config;

import com.plavsic.network.authorization.feign.UserClient;
import com.plavsic.network.authorization.feign.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.plavsic.security.userDetails.CustomUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserResponse user = userClient.getUser(username);

        Set<GrantedAuthority> grantedAuthorities = user.roles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toSet());

        return new CustomUserDetails(user.username(),user.password(),grantedAuthorities);
    }
}
