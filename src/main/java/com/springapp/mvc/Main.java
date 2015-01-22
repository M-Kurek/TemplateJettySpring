package com.springapp.mvc;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;


public class Main {

    public static void main(String[] args) throws Exception {

        Server server = new Server(8080);

        WebAppContext context = new WebAppContext();
        context.setDescriptor("webapp/WEB-INF/web.xml");
        context.setContextPath("/");
        context.setResourceBase("src/main/webapp");
        context.setParentLoaderPriority(true);

        server.setHandler(context);

        server.start();
        server.join();
    }

}
