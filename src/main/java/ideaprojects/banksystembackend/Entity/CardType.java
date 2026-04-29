package ideaprojects.banksystembackend.Entity;

import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.logging.SimpleFormatter;

public enum CardType {
    KASPI,
    HALYK,
    FREEDOM,
    UNKNOWN;

    public SimpleGrantedAuthority toAuthority(){
        return new SimpleGrantedAuthority("ROLE_"+ this.name());
    }
}
