package com.engagepoint.university.messaging.smpp;

import com.cloudhopper.smpp.type.*;

public class SMSClient {

    private SMSClient(){

    }

    public static void main(String[] args) throws SmppInvalidArgumentException {

        SendSMS sendSMS = new SendSMS();
        sendSMS.sendSMS("Sender 1", "98989898", "Hi, How are you?");
        sendSMS.sendSMS("Sender 2", "97979797", "Fine");
        sendSMS.sendSMS("Sender 3", "93333333", "hey");
        sendSMS.sendSMS("Sender 4", "96444444", "Hi");
        sendSMS.sendSMS("Sender 5", "96555555", "c:\\Users\\Kami-sama\\.m2\\");
        sendSMS.sendSMS("Sender 6", "96666666", " I love you!");
        sendSMS.sendSMS("Sender 7", "96777777", "with 23 february");
        sendSMS.sendSMS("Sender 8", "96888888", "Hi-hHi-Hi");
        sendSMS.sendSMS("Sender 9", "96999999", "Hi-Htyi-Hi");
        sendSMS.sendSMS("Sender 10", "96101010", "What?");
        sendSMS.sendSMS("Sender 11", "96111111", "Hi-Hi-Hi");

    }

}

