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
    private final static Logger LOG = LoggerFactory.getLogger(SendSMS.class);

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
        sessionConfig.setRequestExpiryTimeout(10000);
        sessionConfig.setWindowMonitorInterval(10000);
        sessionConfig.setCountersEnabled(true);

        try {

            SmppSession session = client.bind(sessionConfig, new MySmppSessionHandler());

            SubmitSm sm2 = createSubmitSm(sender, receiver, body, "UCS-2");
            sm2.setReferenceObject("Hello2" + sm2+"//***//");

            WindowFuture<Integer, PduRequest, PduResponse> future2 = session.sendRequestPdu(sm2, TimeUnit.SECONDS.toMillis(10), false);
            while (!future2.isDone()) {
                LOG.debug("Not done");
                LOG.debug("Not done Succes is {}", future2.isSuccess());
            }
            LOG.info("", future2);

            LOG.info("Got response  {}", future2.getResponse());

            LOG.info("Done Succes status is {}", future2.isSuccess());

            LOG.info("Response time is {}", future2.getAcceptToDoneTime());

            LOG.info("Wait 10 seconds");

            TimeUnit.SECONDS.sleep(10);
            LOG.info("Wait 10 seconds");


            LOG.debug("Destroy session");
            System.out.println("DESTROY SESSION");

            session.close();
            session.destroy();

            LOG.info("Destroy client");
            System.out.println("Destroy client");

            client.destroy();

            LOG.info("Bye!");
            System.out.println("Bye!");
        } catch (SmppTimeoutException | SmppChannelException | UnrecoverablePduException | InterruptedException
                | RecoverablePduException e) {
            LOG.error("{}", e);
        }
    }

    public static SubmitSm createSubmitSm(String src, String dst, String text, String charset) throws SmppInvalidArgumentException {
        SubmitSm sm = new SubmitSm();

        sm.setSourceAddress(new Address((byte)5, (byte)0, src));
        sm.setDestAddress(new Address((byte)1, (byte)1, dst));
        sm.setDataCoding((byte)8);
        sm.setShortMessage(CharsetUtil.encode(text, charset));
        sm.setRegisteredDelivery((byte)1);

        return sm;
    }

}
