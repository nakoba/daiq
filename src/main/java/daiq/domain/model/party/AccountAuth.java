package daiq.domain.model.party;

import daiq.core.lang.Utils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data @NoArgsConstructor @AllArgsConstructor(access = AccessLevel.PACKAGE)
public class AccountAuth implements Serializable {
    
    private String password;
    private String initialPassword;
    private Date passwordChangedDate;

    
    public static AccountAuth of() {
        final String plane = RandomStringUtils.random(12, "abcdefhnrtwxyzABCDEFGHLMNTWXYZ2345678#$%&-@+*?");
        return new AccountAuth(Utils.digest(plane), plane, new Date());
    }

    public static AccountAuth of(String planePassword) {
        AccountAuth auth = AccountAuth.of();
        auth.changePassword(planePassword);
        return auth;
    }

    boolean canPermit(String planePassword) {
        return getPassword().equals(Utils.digest(planePassword));
    }

    void initialize() {
        changePassword(getInitialPassword());
    }
    
    void changePassword(String planePassword) {
        if (Objects.requireNonNull(planePassword).length() < 4 ) {
            throw new IllegalArgumentException("AccountAuth.passwordLength");
        }
        setPassword(Utils.digest(planePassword));
        setPasswordChangedDate(new Date());
    }

}
