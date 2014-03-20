package com.engagepoint.university.messaging.smpp;

import com.cloudhopper.smpp.type.*;

public class SMSClient {
    public static void main(String[] args) throws SmppInvalidArgumentException {

        SendSMS sendSMS = new SendSMS();
        sendSMS.sendSMS("Sender 1", "98989898", "Hi, How are you?");
        sendSMS.sendSMS("Sender 2", "97979797", "Fine");
        sendSMS.sendSMS("Sender 3", "96969696", "hey");
        sendSMS.sendSMS("Sender 4", "96969696", "Hi");
        sendSMS.sendSMS("Sender 5", "96969696", "c:\\Users\\Kami-sama\\.m2\\");
        sendSMS.sendSMS("Sender 6", "96969696", " I love you!");
        sendSMS.sendSMS("Sender 7", "96969696", "with 23 february");
        sendSMS.sendSMS("Sender 8", "96969696", "Hi-hHi-Hi");
        sendSMS.sendSMS("Sender 9", "96969696", "Hi-Htyi-Hi");
        sendSMS.sendSMS("Sender 10", "96969696", "What?");
        sendSMS.sendSMS("Sender 11", "96969696", "Hi-Hi-Hi");

    }

}

