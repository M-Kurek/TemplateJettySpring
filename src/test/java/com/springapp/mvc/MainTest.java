package com.springapp.mvc;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.component.Dumpable;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.StringWriter;
import java.io.Writer;

import static org.junit.Assert.*;

public class MainTest {

    private static final Logger LOG = Logger.getLogger(MainTest.class);

    @Test
    @Ignore
    public void testSth() throws Exception {
        fail("NIY");
    }

    @Before
    public void upServer() throws Exception {

        Server server = new Server(8080);

        WebAppContext context = new WebAppContext();
        context.setDescriptor("webapp/WEB-INF/web.xml");
        context.setContextPath("/");
        context.setResourceBase("src/main/webapp");
        context.setParentLoaderPriority(true);

        Writer writer = new StringWriter();
        context.dump(writer, " ");
        LOG.info("SRV is:\n" + writer.toString());


        server.setHandler(context);

        server.start();
        server.join();
    }

}