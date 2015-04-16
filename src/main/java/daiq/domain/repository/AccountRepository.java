package daiq.domain.repository;

import daiq.domain.model.party.Account;
import daiq.domain.model.party.AccountId;

import java.util.List;

public interface AccountRepository {

    Account add(Account account);

    List<Account> filterBy(AccountId accountId);
    
    List<Account> list();
}
