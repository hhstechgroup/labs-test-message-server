package com.engagepoint.university.messaging.controller;

import com.engagepoint.university.messaging.smpp.SMPPServerMain;
import com.engagepoint.university.messaging.smtp.SMTPServerMain;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ServerInfoController {

    @Inject
    SMPPServerMain serverSMPP;
    @Inject
    SMTPServerMain serverSMTP;

    public String getSMTPhostName () {
        return serverSMTP.getHostname();
    }

    public int getSMTPport () {
        return serverSMTP.getPort();
    }

    public String getSMPPhost () {
        return serverSMPP.getHost();
    }

    public int getSMPPport () {
        return serverSMPP.getPort();
    }

}
