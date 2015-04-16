package daiq.domain.model.party;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data @AllArgsConstructor @NoArgsConstructor
public class Account implements Serializable {
    private AccountId accountId;
    private Person person;
    private AccountAuth auth;
}
