package world.ucode.security;//package world.ucode.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import world.ucode.models.User;
import world.ucode.services.UserService;

import java.util.Collection;

@Component
public class AuthProvider implements AuthenticationProvider
{
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    };
    final private UserService userService = new UserService();

    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        PasswordEncoder passwordEncoder = passwordEncoder();
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        System.out.println(username);
        System.out.println(password);
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
