package daiq.rest;

import daiq.application.AccountAppService;
import daiq.domain.model.party.Account;
import daiq.domain.service.AccountRegisterService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("account")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountResource {
    
    @Inject AccountAppService accountAppService;

    @POST
    public Account post(AccountRegisterService.RegisterRequest param) {
        return accountAppService.registerAccount(param);
    }

    @GET
    public List<Account> list() {
        return accountAppService.accountlist();
    }

}
