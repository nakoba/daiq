package daiq.rest;

import daiq.application.AdminAppService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminResource {
    
    @Inject AdminAppService adminAppService;

    @GET @Path("healthCheck")
    public String healthCheck() {
        return adminAppService.healthCheck();
    }
}
