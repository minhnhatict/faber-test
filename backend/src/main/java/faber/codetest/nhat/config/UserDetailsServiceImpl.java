package faber.codetest.nhat.config;

import faber.codetest.nhat.entity.User;
import faber.codetest.nhat.model.CustomUserPrincipal;
import faber.codetest.nhat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    /**
     * Load authenticated user by email
     * @param s specific username -> email
     * @return User Principal
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findByEmail(s);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRoleName()));

        return new CustomUserPrincipal(user.getEmail(), user.getPassword(), grantedAuthorities);    }
}
