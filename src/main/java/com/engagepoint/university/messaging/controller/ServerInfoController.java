package com.engagepoint.university.messaging.controller;

import com.engagepoint.university.messaging.smpp.SMPPServerMain;
import com.engagepoint.university.messaging.smtp.SMTPServerMain;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Named
@ViewScoped
public class ServerInfoController {

    @Inject
    SMPPServerMain serverSMPP;
    @Inject
    SMTPServerMain serverSMTP;

    public String getSMTPhostName() throws UnknownHostException {
        return serverSMTP.getHost();
    }

    public int getSMTPport() {
        return serverSMTP.getPort();
    }

    public String getSMPPhost() throws UnknownHostException {
        return serverSMPP.getHost();
    }

    public int getSMPPport() {
        return serverSMPP.getPort();
    }

    public String getSoapServletHOST() throws UnknownHostException {
        return "http://" + InetAddress.getLocalHost().getHostAddress() + ":8080/labs-test-message-server/soap/";
    }

}
