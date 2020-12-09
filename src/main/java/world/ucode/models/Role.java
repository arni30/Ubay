package world.ucode.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    BIDDER,
    SELLER;

    @Override
    public String getAuthority()
    {
        return name();
    }
}