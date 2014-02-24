package com.engagepoint.university.messaging.smtp;

import org.subethamail.smtp.MessageHandlerFactory;
import org.subethamail.smtp.server.SMTPServer;

public class SMTPServerMain implements Runnable{
    private static final int PORT = 25000;
    private static final String HOSTNAME = "localhost";
    SMTPServer server;
    MessageHandlerFactory messageHandlerFactory;

    public SMTPServerMain() {
        setMessageHandlerFactory();
        setSMTPServer();
    }

    private void setMessageHandlerFactory() {
        this.messageHandlerFactory = new SMTPMessageHandlerFactory();
    }

    private MessageHandlerFactory getMessageHandlerFactory () {
        return this.messageHandlerFactory;
    }

    private void setSMTPServer () {
        this.server = new SMTPServer(getMessageHandlerFactory());
    }

    private SMTPServer getServer () {
        return this.server;
    }

    public void startSMTPServer () {
        Thread t = new Thread(this,"SMTPServeSubetha");
        t.start();
    }

    public void shutDownSMTPServer () {
        getServer().stop();
    }

    public int getPort () {
        return PORT;
    }

    public String getHostname () {
        return HOSTNAME;
    }
    @Override
    public void run() {
        getServer().setPort(getPort());
        getServer().setHostName(getHostname());
        getServer().start();
    }
}
