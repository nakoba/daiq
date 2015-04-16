package daiq.persistence;

import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import daiq.core.server.Database;
import daiq.domain.model.party.Account;
import daiq.domain.model.party.AccountId;
import daiq.domain.repository.AccountRepository;
import javax.inject.Inject;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {
    
    @Inject Database db;
    
    @Override
    public Account add(Account account) {
        return db.save(account);
    }

    @Override
    public List<Account> filterBy(AccountId accountId) {
        return db.query(new OSQLSynchQuery<Account>("select from Account where accountId.value = :surname"), accountId.getValue());
    }
    
    @Override
    public List<Account> list() {
        return db.query(new OSQLSynchQuery<Account>("select from Account"));
    }

}
