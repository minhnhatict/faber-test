package faber.codetest.nhat.config;

import faber.codetest.nhat.entity.User;
import faber.codetest.nhat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = (String) authentication.getCredentials();

        // check if user has valid email & password in db
        User authResultUser = userService.authenticate(email, password);

        // return user info after logged in
        if (authResultUser != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }

}
