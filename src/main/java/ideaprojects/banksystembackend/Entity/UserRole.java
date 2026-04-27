package ideaprojects.banksystembackend.Entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum UserRole {
    USER_BLACKLISTED,
    USER_STANDARD,
    USER_VIP,
    ADMIN_ROLE;

    public SimpleGrantedAuthority toAuthority(){
        return new SimpleGrantedAuthority("ROLE_"+ this.name());
    }
}
