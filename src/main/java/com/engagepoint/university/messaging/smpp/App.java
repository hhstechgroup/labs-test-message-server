package com.engagepoint.university.messaging.smpp;

/*
 * #%L
 * labs-test-message-server
 * %%
 * Copyright (C) 2012 - 2014 Cloudhopper by Twitter
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */



import com.cloudhopper.commons.charset.CharsetUtil;
import com.cloudhopper.commons.util.windowing.WindowFuture;
import com.cloudhopper.smpp.SmppBindType;
import com.cloudhopper.smpp.SmppSession;
import com.cloudhopper.smpp.SmppSessionConfiguration;
import com.cloudhopper.smpp.impl.DefaultSmppClient;
import com.cloudhopper.smpp.pdu.PduRequest;
import com.cloudhopper.smpp.pdu.PduResponse;
import com.cloudhopper.smpp.pdu.SubmitSm;
import com.cloudhopper.smpp.pdu.SubmitSmResp;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import com.cloudhopper.smpp.type.Address;
import com.cloudhopper.smpp.type.LoggingOptions;
import com.cloudhopper.smpp.type.RecoverablePduException;
import com.cloudhopper.smpp.type.SmppChannelException;
import com.cloudhopper.smpp.type.SmppInvalidArgumentException;
import com.cloudhopper.smpp.type.SmppTimeoutException;
import com.cloudhopper.smpp.type.UnrecoverablePduException;
import com.cloudhopper.smpp.type.SmppBindException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.logging.Level;

public class App {
    public static Logger log = LoggerFactory.getLogger(App.class);

    private static void log(WindowFuture<Integer, PduRequest, PduResponse> future) {
        SubmitSm req = (SubmitSm)future.getRequest();
        SubmitSmResp resp = (SubmitSmResp)future.getResponse();
        System.out.println("Got response with MSG ID={} for APPID={} "+ resp.getMessageId()+ req.getReferenceObject());
        log.debug("Got response with MSG ID={} for APPID={}", resp.getMessageId(), req.getReferenceObject());
    }

    public static void main(String[] args) throws SmppInvalidArgumentException {
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
        sessionConfig.setRequestExpiryTimeout(30000);
        sessionConfig.setWindowMonitorInterval(15000);
        sessionConfig.setCountersEnabled(true);

        //==============отключаем ЛОГЕР===============================
        LoggingOptions loggingOptions = new LoggingOptions();
        //закоментировали-----------
        loggingOptions.setLogPdu(false);
        loggingOptions.setLogBytes(false);
        //-----------------------
        sessionConfig.setLoggingOptions(loggingOptions);
        //===============================================



//***//
       try {
            //SmppSession session = client.bind(sessionConfig);
            // обработка поступающих сообщений
            SmppSession session = client.bind(sessionConfig, new MySmppSessionHandler());



            SubmitSm sm2 = createSubmitSm("Test", "79111234567", "Hello cloudHopper!", "UCS-2");
            sm2.setReferenceObject("Hello2" + sm2+"//***//");

            WindowFuture<Integer, PduRequest, PduResponse> future2 = session.sendRequestPdu(sm2, TimeUnit.SECONDS.toMillis(60), false);
            while (!future2.isDone()
  /* --- */                /*|| !future.isDone()*/) {
                log.debug("Not done");
            }

  /* --- */        /*log.info("",future);*/
            log.info("",future2);

            while (!future2.isDone()) {
                log.debug("Not done Succes is {}", future2.isSuccess());
                System.out.println("send message 3 Not done Succes is {}" + future2.isSuccess());
            }
            log.debug("Got response 3 {}", future2.getResponse());
            System.out.println("send message 3 Got response 2 {}"+ future2.getResponse());
            log.debug("Done Succes status is {}", future2.isSuccess());
            System.out.println("send message 3 Done Succes status is {}"+ future2.isSuccess());
            log.debug("Response time is {}", future2.getAcceptToDoneTime());
            System.out.println("send message 3 Response time is {}"+ future2.getAcceptToDoneTime());
            log.debug("Wait 10 seconds");
            System.out.println("Wait 10 seconds");
            TimeUnit.SECONDS.sleep(10);
            log.debug("Wait 10 seconds");
            TimeUnit.SECONDS.sleep(10);

            log.debug("Destroy session");
            System.out.println("DESTROY SESSION");

            session.close();
            session.destroy();

            log.debug("Destroy client");
            System.out.println("Destroy client");

            client.destroy();

            log.debug("Bye!");
            System.out.println("Bye!");
        } catch (SmppTimeoutException ex) {
            log.error("{}", ex);
            //Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SmppChannelException ex) {
            log.error("{}", ex);
            // Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SmppBindException ex) {
            log.error("{}", ex);
            //Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverablePduException ex) {
            log.error("{}", ex);
            //Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            log.error("{}", ex);
            //Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RecoverablePduException ex) {
            log.error("{}", ex);
            //Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static SubmitSm createSubmitSm(String src, String dst, String text, String charset) throws SmppInvalidArgumentException {
        SubmitSm sm = new SubmitSm();

        // For alpha numeric will use
        // TON=5
        // NPI=0
        sm.setSourceAddress(new Address((byte)5, (byte)0, src));

        // For national numbers will use
        // TON=1
        // NPI=1
        sm.setDestAddress(new Address((byte)1, (byte)1, dst));

        // Set datacoding to UCS-2
        sm.setDataCoding((byte)8);

        // Encode text
        sm.setShortMessage(CharsetUtil.encode(text, charset));

// ДОБАВИЛИ!!!     отчет о доставке
        //We would like to get delivery receipt
        sm.setRegisteredDelivery((byte)1);

        return sm;
    }


}

/**/
//=========================================================================
/*
Client client = new Client(sessionConfig);

client.setSessionHandler(new MySmppSessionHandler(client));

ExecutorService pool = Executors.newFixedThreadPool(2);

pool.submit(client);

client.start();

log.debug("Wait to bound");

while (
        client.getSession() == null
        || !client.getSession().isBound()
        ) {

        if (client.getSession() != null) {
        log.debug("Session is {}", client.getSession().isBound());
} else {
        log.debug("Null session");
}

        try {
        TimeUnit.SECONDS.sleep(1);
} catch (InterruptedException ex) {
        java.util.logging.Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
}
        }

        log.debug("Try to send");

SubmitSm sm = new SubmitSm();

sm.setSourceAddress(new Address((byte)5, (byte)0, "Test"));
sm.setDestAddress(new Address((byte)1, (byte)1, "79111234567"));

sm.setShortMessage(CharsetUtil.encode("Привет!", "UCS-2"));

sm.setRegisteredDelivery((byte)1);

sm.setDataCoding((byte)8);



try {
        client.getSession().submit(sm, TimeUnit.SECONDS.toMillis(60));
} catch (RecoverablePduException ex) {
        java.util.logging.Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
} catch (UnrecoverablePduException ex) {
        java.util.logging.Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
} catch (SmppTimeoutException ex) {
        java.util.logging.Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
} catch (SmppChannelException ex) {
        java.util.logging.Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
} catch (InterruptedException ex) {
        java.util.logging.Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
}
        try {
        TimeUnit.SECONDS.sleep(30);
} catch (InterruptedException ex) {
        java.util.logging.Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
}

        client.stop();

pool.shutdown();
}
        }*/
