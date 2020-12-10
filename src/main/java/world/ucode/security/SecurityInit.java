package world.ucode.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

class SecurityInit extends AbstractSecurityWebApplicationInitializer{
    SecurityInit(){
        super(SecurityConfig.class);
    }
}