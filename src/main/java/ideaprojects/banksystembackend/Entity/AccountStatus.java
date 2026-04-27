package ideaprojects.banksystembackend.Entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum AccountStatus {
    ACCOUNT_STATUS_ACTIVE,
    ACCOUNT_STATUS_SUSPENDED,
    ACCOUNT_STATUS_CLOSED;

    public SimpleGrantedAuthority SimpleGrantedAuthority(){
        return new SimpleGrantedAuthority("ROLE_"+ this.name());
    }
}
