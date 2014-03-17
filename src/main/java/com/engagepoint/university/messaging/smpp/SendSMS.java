package com.engagepoint.university.messaging.smpp;

import com.cloudhopper.commons.charset.CharsetUtil;
import com.cloudhopper.commons.util.windowing.WindowFuture;
import com.cloudhopper.smpp.SmppBindType;
import com.cloudhopper.smpp.SmppSession;
import com.cloudhopper.smpp.SmppSessionConfiguration;
import com.cloudhopper.smpp.impl.DefaultSmppClient;
import com.cloudhopper.smpp.pdu.PduRequest;
import com.cloudhopper.smpp.pdu.PduResponse;
import com.cloudhopper.smpp.pdu.SubmitSm;
import com.cloudhopper.smpp.type.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class SendSMS {
    public static Logger log = LoggerFactory.getLogger(SMSClient.class);

    //public static void main(String[] args) throws SmppInvalidArgumentException {
    public void sendSMS(String sender, String receiver, String body) {
        DefaultSmppClient client = new DefaultSmppClient();

        SmppSessionConfiguration sessionConfig = new SmppSessionConfiguration();

        sessionConfig.setWindowSize(1);
        sessionConfig.setName("Tester.Session.0");
        sessionConfig.setType(SmppBindType.TRANSCEIVER);
        sessionConfig.setHost("127.0.0.1");
        sessionConfig.setPort(2776);
        sessionConfig.setConnectTimeout(10000);
        sessionConfig.setSystemId("smppclient1");
        sessionConfig.setPassword("password");
        sessionConfig.getLoggingOptions().setLogBytes(true);
        // to enable monitoring (request expiration)
        sessionConfig.setRequestExpiryTimeout(10000);
        sessionConfig.setWindowMonitorInterval(10000);
        sessionConfig.setCountersEnabled(true);

        try {

            // обработка поступающих сообщений
            SmppSession session = client.bind(sessionConfig, new MySmppSessionHandler());

            SubmitSm sm2 = createSubmitSm(sender, receiver, body, "UCS-2");
            sm2.setReferenceObject("Hello2" + sm2 + "//***//");

            WindowFuture<Integer, PduRequest, PduResponse> future2 = session.sendRequestPdu(sm2, TimeUnit.SECONDS.toMillis(10), false);
            session.close();
            session.destroy();
            client.destroy();
        } catch (SmppTimeoutException ex) {
            log.error("{}", ex);
            //Logger.getLogger(SMSClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SmppChannelException ex) {
            log.error("{}", ex);
            // Logger.getLogger(SMSClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SmppBindException ex) {
            log.error("{}", ex);
            //Logger.getLogger(SMSClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverablePduException ex) {
            log.error("{}", ex);
            //Logger.getLogger(SMSClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            log.error("{}", ex);
            //Logger.getLogger(SMSClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RecoverablePduException ex) {
            log.error("{}", ex);
            //Logger.getLogger(SMSClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static SubmitSm createSubmitSm(String src, String dst, String text, String charset) throws SmppInvalidArgumentException {
        SubmitSm sm = new SubmitSm();

        // For alpha numeric will use
        // TON=5
        // NPI=0
        sm.setSourceAddress(new Address((byte) 5, (byte) 0, src));

        // For national numbers will use
        // TON=1
        // NPI=1
        sm.setDestAddress(new Address((byte) 1, (byte) 1, dst));

        // Set datacoding to UCS-2
        sm.setDataCoding((byte) 8);

        // Encode text
        sm.setShortMessage(CharsetUtil.encode(text, charset));

// ДОБАВИЛИ!!!     отчет о доставке
        //We would like to get delivery receipt
        sm.setRegisteredDelivery((byte) 1);

        return sm;
    }

}
