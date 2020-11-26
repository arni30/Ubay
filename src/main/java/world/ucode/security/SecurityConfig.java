//package world.ucode.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Configuration
//@EnableWebSecurity
//@Component
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Bean
//    SecurityConfig securityConfig() {
//        return new SecurityConfig();
//    }
//    @Bean
//    PasswordEncoder passwordEncoder()
//    {
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        return passwordEncoder;
//    }
//    @Bean
//    AuthProvider authProvider(){
//        System.out.println("hadsadasdasdasdasdasdasdas");
//        return new AuthProvider();
//    }
//
//    @Autowired
//    private AuthProvider authProvider;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//    {
//        auth.authenticationProvider(authProvider);
//    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception
//    {
//        http
//                .authorizeRequests()
//                .antMatchers("/resources/**", "/", "/authorization", "/main").permitAll()
//                .antMatchers("/profile").hasAnyRole("USER")
//                .anyRequest().authenticated()
//                .and().formLogin().loginPage("authorization")
//                .defaultSuccessUrl("/profile").failureUrl("/authorization").permitAll()
//                .and().logout().logoutSuccessUrl("/").permitAll();
//    }
//}
