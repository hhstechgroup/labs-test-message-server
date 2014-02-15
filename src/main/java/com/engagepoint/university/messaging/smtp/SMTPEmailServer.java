package com.engagepoint.university.messaging.smtp;

import org.subethamail.smtp.MessageHandlerFactory;
import org.subethamail.smtp.server.SMTPServer;

public class SMTPEmailServer {

    private final Integer DEFAULT_PORT = 24000;
    private SMTPServer mailserver;
    SMTPMessageHandlerFactory messageFactory;
//    private boolean serverIsUp = false;
    public Integer port = null;
    public void init () {
//        if (port==null) setPort(DEFAULT_PORT);
        setMessageFactory();
        setMailserver(getMessageHandFactory());
        getServer().start();
    }
    public void setMailserver (MessageHandlerFactory factory) {
//        if (serverIsUp == false){
            mailserver = new SMTPServer(factory);
//            serverIsUp = true;
//        }else {
//            throw new ServerIsUpException("Server is already working!");
//        }
    }
    public SMTPServer getServer () {
        return this.mailserver;
    }
    public void setMessageFactory () {
        this.messageFactory = new SMTPMessageHandlerFactory();
    }
    public MessageHandlerFactory getMessageHandFactory () {
        return this.messageFactory;
    }
    public void setPort (int port) {
        this.port = port;
        mailserver.setPort(port);
    }
    public Integer getPort () {
        return this.port;
    }
}
