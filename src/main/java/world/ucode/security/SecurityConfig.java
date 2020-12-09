package world.ucode.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .cors().disable()
                .csrf().disable();

        // The pages does not require login
        http.authorizeRequests().antMatchers("/", "/main", "/authorization").permitAll();

        // /userInfo page requires login as USER or ADMIN.
        // If no login, it will redirect to /login page.
        http.authorizeRequests().antMatchers("/profile/**").hasAnyAuthority("BIDDER", "SELLER");
        http.authorizeRequests().antMatchers("/addLot/**").hasAnyAuthority("SELLER");
        http.authorizeRequests().antMatchers("/addFeedback/**").hasAnyAuthority("BIDDER");
        // When the user has logged in as XX.
        // But access a page that requires role YY,
        // AccessDeniedException will throw.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/accessDenied").accessDeniedHandler(new AccessDeniedExceptions());
        // Config for Login Form
        http.authorizeRequests().and().formLogin()//
                // Submit URL of login page.
                .loginProcessingUrl("/authorization").permitAll() // Submit URL
                .loginPage("/authorization")//
                .failureUrl("/accessDenied")
                .failureHandler(new AuthExceptions())
                .defaultSuccessUrl("/main")//
                .usernameParameter("login") //the username parameter in the queryString, default is 'username'
                .passwordParameter("password");
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                // Config for Logout Page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/main");
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    };
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
            auth.authenticationProvider(new AuthProvider());
    }
}
