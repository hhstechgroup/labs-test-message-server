package com.engagepoint.university.messaging.controller;

import com.engagepoint.university.messaging.smpp.SMPPServerMain;
import com.engagepoint.university.messaging.smtp.SMTPServerMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.Serializable;

@Startup
@Singleton
public class InitController implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(InitController.class);

    @Inject
    private SMPPServerMain serverSMPP;

    @Inject
    private SMTPServerMain serverSMTP;

    @PostConstruct
    void init() {
        try {
            serverSMTP.startSMTPServer();
            serverSMPP.startSmppServer();
        } catch (Exception e) {
            LOG.warn(e.getMessage(), e);
        }
    }

    @PreDestroy
    void shutdown() {
        try {
            serverSMPP.stopSmppServer();
            serverSMTP.shutDownSMTPServer();
        } catch (Exception e) {
            LOG.warn(e.getMessage(), e);
        }

    }
}


