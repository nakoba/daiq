package daiq.core.server;

import daiq.core.lang.Langs;
import org.apache.catalina.Context;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static daiq.core.lang.Langs.uncheck;
import static daiq.core.lang.Langs.uncheck;

public class AppServer {

    private final Tomcat tomcat;

    public AppServer(int port, Path base, String contextPath, Map<WebServlet, Servlet> webServlets) {
        tomcat = new Tomcat();
        tomcat.setPort(port);
        Context context = tomcat.addContext(contextPath, base.toFile().getAbsolutePath());
        Tomcat.addServlet(context, "default", new DefaultServlet()).addMapping("/");
        webServlets.forEach((k, v) ->
            Tomcat.addServlet(context, k.name(), v).addMapping(k.urlPatterns()[0]));
    }
    
    
    public AppServer(String contextPath, Class<? extends Servlet>...servlets) {
        this(8080, Paths.get("src/main/webapp/"), contextPath, servletMapOf(servlets));
    }

    
    public void await(Runnable...runnables) {
        Arrays.asList(runnables).forEach(r -> r.run());
        tomcat.getServer().await();
    }
    
    public void start() { Langs.uncheck(tomcat::start); }
    
    
    public void stop() { Langs.uncheck(tomcat::stop); }

    
    public static Map<WebServlet, Servlet> servletMapOf(Class<? extends Servlet>...servlets) {
        return Arrays.stream(servlets).collect(Collectors.toMap(
            e -> e.getAnnotation(WebServlet.class),
            e -> (Servlet) Langs.uncheck(e::newInstance)));
    }

}
