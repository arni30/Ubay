package world.ucode.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import world.ucode.models.User;
import world.ucode.services.UserService;

import java.util.Collection;

@Component
public class AuthProvider implements AuthenticationProvider {
    private UserService userService = new UserService();

    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        User user = (User) userService.findUserByLogin(username);

        if(user != null && (user.getLogin().equals(username)))
        {
            if (!user.getVerification().equals("verificated")) {
                throw new BadCredentialsException("Not verificated");
            }
            if(!BCrypt.checkpw(password, user.getPassword()))
            {
                throw new BadCredentialsException("Wrong password");
            }

            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            return new UsernamePasswordAuthenticationToken(user, password, authorities);
        }
        else
            throw new BadCredentialsException("Username not found");
    }

    public boolean supports(Class<?> arg)
    {
        return true;
    }
}
