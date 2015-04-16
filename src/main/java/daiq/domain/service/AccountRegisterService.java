package daiq.domain.service;

import daiq.domain.Message;
import daiq.domain.model.party.AccountId;
import daiq.domain.model.party.Person;
import daiq.domain.model.shared.*;
import daiq.domain.repository.AccountRepository;
import daiq.domain.model.party.Account;
import daiq.domain.model.party.AccountAuth;
import static daiq.core.lang.Langs.*;

public class AccountRegisterService {

    private final AccountRepository repository;

    public AccountRegisterService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account register(RegisterRequest request) {
        
        final AccountId accountId = new AccountId(request.accountId);

        repository.filterBy(accountId).stream().findFirst()
                .ifPresent(a -> throwWith(Message.ConflictAccountId));

        Account account = new Account(
                accountId,
                new Person(
                    new FullName(request.familyName, request.givenName),
                    new ContactInfo(
                        new EmailAddress(request.emailAddress),
                        new PostalAddress(), 
                        new Tel(), 
                        new Tel())),
                AccountAuth.of(request.password));

        return repository.add(account);
    }
    

    public static class RegisterRequest {
        public String accountId;
        public String password;
        public String familyName;
        public String givenName;
        public String emailAddress;
    }
}
