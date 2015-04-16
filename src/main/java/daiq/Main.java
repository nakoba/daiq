package daiq;

import daiq.core.lang.Langs;
import daiq.core.server.AppServer;
import daiq.rest.RestServlet;

import java.awt.*;
import java.net.URI;

import static daiq.core.lang.Langs.str;

public class Main {
    
    public static void main(String[] args) throws Exception {
        AppServer appServer = new AppServer("/" + Config.APP_NAME, RestServlet.class);
        appServer.start();
        appServer.await(()-> Langs.uncheck(() ->
                Desktop.getDesktop().browse(new URI(str("http://localhost:8080/{}/index.html", Config.APP_NAME)))));
    }
}
