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
        http.authorizeRequests().antMatchers("/", "/main", "/authorization").permitAll();
        http.authorizeRequests().antMatchers("/profile/**").hasAnyAuthority("BIDDER", "SELLER");
        http.authorizeRequests().antMatchers("/addLot/**").hasAnyAuthority("SELLER");
        http.authorizeRequests().antMatchers("/addFeedback/**").hasAnyAuthority("BIDDER");
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/accessDenied").accessDeniedHandler(new AccessDeniedExceptions());
        http.authorizeRequests().and().formLogin()//
                // Submit URL of login page.
                .loginProcessingUrl("/authorization").permitAll()
                .loginPage("/authorization")
                .failureUrl("/accessDenied")
                .failureHandler(new AuthExceptions())
                .defaultSuccessUrl("/main")
                .usernameParameter("login")
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
