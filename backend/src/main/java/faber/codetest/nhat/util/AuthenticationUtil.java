package faber.codetest.nhat.util;

import faber.codetest.nhat.model.CustomUserPrincipal;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public class AuthenticationUtil {

    public static boolean isAuthenticated(){
        Object userPrincipal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userPrincipal instanceof CustomUserPrincipal;
    }

    public static CustomUserPrincipal getPrincipal(){
        if (isAuthenticated()){
            return (CustomUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        return null;
    }

    /**
     * check authenticated user has specific role
     * @param role
     * @return
     */
    public static boolean checkUserHasRole(String role) {
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals(role)) {
                return true;
            }
        }
        return false;
    }

    /**
     * get Email of authenticated User
     * @return
     */
    public static String getUserName () {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
