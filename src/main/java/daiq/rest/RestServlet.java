package daiq.rest;

import daiq.Config;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.annotation.WebServlet;
import javax.ws.rs.ApplicationPath;

@WebServlet( name = "RsServlet", urlPatterns = {"/rest/*"} )
public class RestServlet extends ServletContainer {

    public RestServlet() {
        super(new RsConfig());
    }

    @ApplicationPath("rest")
    public static class RsConfig extends ResourceConfig {

        public RsConfig() {
            packages(getClass().getPackage().getName());
            register(AppExceptionMapper.class);
            register(Config.BINDER);
        }
    }
}
