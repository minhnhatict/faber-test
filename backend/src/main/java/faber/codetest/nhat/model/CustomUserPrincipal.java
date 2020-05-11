package faber.codetest.nhat.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * This class is used for store attributes of authenticated user
 */
public class CustomUserPrincipal extends org.springframework.security.core.userdetails.User {

    public CustomUserPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
