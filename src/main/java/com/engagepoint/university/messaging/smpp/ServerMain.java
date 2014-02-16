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
import com.cloudhopper.smpp.*;
import com.cloudhopper.smpp.impl.DefaultSmppServer;
import com.cloudhopper.smpp.impl.DefaultSmppSessionHandler;
import com.cloudhopper.smpp.pdu.*;
import com.cloudhopper.smpp.type.SmppProcessingException;
import com.engagepoint.university.messaging.dao.specific.SmsDAO;
import com.engagepoint.university.messaging.dao.specific.impl.SmsDAOImpl;
import com.engagepoint.university.messaging.dto.SmsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

@Singleton
public class ServerMain {
    private static final Logger logger = LoggerFactory.getLogger(ServerMain.class);
    @Inject
    private SmsDTO smsDTO;
    @Inject
    private SmsDAO smsDAO;

    ServerMain (){
        try{
            startSmppServer();
            logger.info("START - startSmppServer");
        } catch (Exception ex){
            logger.info("Exception SMPP server..." + ex);
        }
    }

    public void startSmppServer() throws Exception {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        ScheduledThreadPoolExecutor monitorExecutor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1, new ThreadFactory() {
            private AtomicInteger sequence = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("SmppServerSessionWindowMonitorPool-" + sequence.getAndIncrement());
                return t;
            }
        });

        // create a server configuration
        SmppServerConfiguration configuration = new SmppServerConfiguration();
        configuration.setPort(2776);
        configuration.setMaxConnectionSize(10);
        configuration.setNonBlockingSocketsEnabled(true);
        configuration.setDefaultRequestExpiryTimeout(30000);
        configuration.setDefaultWindowMonitorInterval(15000);
        configuration.setDefaultWindowSize(5);
        configuration.setDefaultWindowWaitTimeout(configuration.getDefaultRequestExpiryTimeout());
        configuration.setDefaultSessionCountersEnabled(true);
        configuration.setJmxEnabled(true);

        // create a server, start it up
        DefaultSmppServer smppServer = new DefaultSmppServer(configuration, new DefaultSmppServerHandler(), executor, monitorExecutor);

        logger.info("Starting SMPP server...");
        smppServer.start();
        logger.info("SMPP server started");
    }

    public /*static*/ class DefaultSmppServerHandler implements SmppServerHandler {

        @Override
        public void sessionBindRequested(Long sessionId, SmppSessionConfiguration sessionConfiguration, final BaseBind bindRequest) throws SmppProcessingException {
            sessionConfiguration.setName("Application.SMPP." + sessionConfiguration.getSystemId());
            System.out.println("kuku1" + 1);
        }

        @Override
        public void sessionCreated(Long sessionId, SmppServerSession session, BaseBindResp preparedBindResponse) throws SmppProcessingException {
            logger.info("Session created: {}", session);
            System.out.println("kuku2" + 2);
            // need to do something it now (flag we're ready)
            session.serverReady(new TestSmppSessionHandler(session));
        }

        @Override
        public void sessionDestroyed(Long sessionId, SmppServerSession session) {
            logger.info("Session destroyed: {}", session);
            System.out.println("kuku3");
            if (session.hasCounters()) {
                logger.info(" final session rx-submitSM: {}", session.getCounters().getRxSubmitSM());
            }
            session.destroy();
        }

    }

    public /*static*/ class TestSmppSessionHandler extends DefaultSmppSessionHandler {

        private WeakReference<SmppSession> sessionRef;

        public TestSmppSessionHandler(SmppSession session) {
            this.sessionRef = new WeakReference<SmppSession>(session);
        }

        @Override
        public PduResponse firePduRequestReceived(PduRequest pduRequest) {
            SmppSession session = sessionRef.get();

            logger.info("PduRequest .getReferenceObject()"+ pduRequest.getName());

            SubmitSm req = (SubmitSm) pduRequest;

            String str = CharsetUtil.decode(req.getShortMessage(), CharsetUtil.CHARSET_MODIFIED_UTF8);

            logger.info("SMS sender - " + req.getSourceAddress().getAddress());
            logger.info("SMS reciver - " + req.getDestAddress().getAddress());
            logger.info("SMS body in byte - " + req.getShortMessage());
            System.out.println("SMS reciver in server SMPP - " + req.getDestAddress().getAddress());
            System.out.println("SMS sender in server SMPP - " + req.getSourceAddress().getAddress());
            System.out.println("SMS body in server SMPP - " + str);

            logger.info("SMS body - " + str);

           // SmsDTO smsDTO = new SmsDTO();
            smsDTO.setBody(str);
            smsDTO.setSender(req.getSourceAddress().getAddress());
            smsDTO.setDeliveryDate(new Date());
            smsDTO.setSendDate(new Date());

           // SmsDAOImpl smsDAO = new SmsDAOImpl();
            smsDAO.save(smsDTO);


            System.out.println("kuku4");
            // mimic how long processing could take on a slower smsc
            try {
                //Thread.sleep(50);
            } catch (Exception e) {
            }

            return pduRequest.createResponse();
        }
    }

}
