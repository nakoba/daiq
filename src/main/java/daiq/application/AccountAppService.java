package daiq.application;

import daiq.core.lang.ApplicationService;
import daiq.domain.model.party.Account;
import daiq.domain.repository.AccountRepository;
import daiq.domain.service.AccountRegisterService;

import javax.inject.Inject;
import java.util.List;

public class AccountAppService implements ApplicationService {
    
    @Inject private AccountRepository accountRepository;
    
    public Account registerAccount(AccountRegisterService.RegisterRequest request) {
        Account account =  new AccountRegisterService(accountRepository).register(request);
        return accountRepository.add(account);
    }

    public List<Account> accountlist() {
        return accountRepository.list();
    }
}
