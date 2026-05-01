package ideaprojects.banksystembackend.Entity;

import org.springframework.boot.logging.java.SimpleFormatter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum TransactionType {
    DEPOSIT,
    WITHDRAWAL,
    TRANSFER,
    PAYMENT;

    public SimpleGrantedAuthority toAuthority(){
        return new SimpleGrantedAuthority("ROLE_"+ this.name());
    }
}
