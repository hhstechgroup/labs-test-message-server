package com.engagepoint.university.messaging.services;

import com.engagepoint.university.messaging.smpp.ServerMain;
import com.engagepoint.university.messaging.smtp.SMTPMessageHandlerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.subethamail.smtp.server.SMTPServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.Serializable;

@Startup
@Singleton
public class InitService implements Serializable, Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(InitService.class);

    @Inject
    private ServerMain serverMain;

    @Inject
    private SMTPMessageHandlerFactory emailFactory;

    SMTPServer server;
    Thread thread;
    @PostConstruct
    void init() {
        thread = new Thread(this, "SubeThaSMTP");
        thread.start();
        try {
            serverMain.startSmppServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("APP STARTUP");
    }

    @PreDestroy
    public void cleanUp () {
        server.stop();
        serverMain.stopSmppServer();
    }

    @Override
    public void run() {
        server = new SMTPServer(emailFactory);
        server.setPort(25000);
        server.start();
    }


}