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

    private String soapServletHOST = "{host}/soap/*";

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

    public String getSoapServletHOST() {
        return soapServletHOST;
    }

    public void setSoapServletHOST(String soapServletHOST) {
        this.soapServletHOST = soapServletHOST;
    }
}
