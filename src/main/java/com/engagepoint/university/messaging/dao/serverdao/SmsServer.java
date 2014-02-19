package com.engagepoint.university.messaging.dao.serverdao;

import com.cloudhopper.commons.charset.CharsetUtil;
import com.cloudhopper.smpp.*;
import com.cloudhopper.smpp.impl.DefaultSmppServer;
import com.cloudhopper.smpp.impl.DefaultSmppSessionHandler;
import com.cloudhopper.smpp.pdu.*;
import com.cloudhopper.smpp.type.SmppChannelException;
import com.cloudhopper.smpp.type.SmppProcessingException;
import com.engagepoint.university.messaging.dao.specific.SmsDAO;
import com.engagepoint.university.messaging.dto.SmsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created with IntelliJ IDEA.
 * User: Toronaga
 * Date: 12.02.14
 * Time: 1:30
 * To change this template use File | Settings | File Templates.
 */
@Startup
@Singleton
public class SmsServer {
    private static final Logger logger = LoggerFactory.getLogger(SmsServer.class);
    //static public void main(String[] args) throws Exception {
    public SmsServer(){

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        /*ScheduledThreadPoolExecutor monitorExecutor = (ScheduledThreadPoolExecutor)Executors.newScheduledThreadPool(1, new ThreadFactory() {
            private AtomicInteger sequence = new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("SmppServerSessionWindowMonitorPool-" + sequence.getAndIncrement());
                return t;
            }
        });            */

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
        DefaultSmppServer smppServer = new DefaultSmppServer(configuration, new DefaultSmppServerHandler(), executor);

        logger.info("Starting SMPP server...");
        try {
            smppServer.start();
        } catch (SmppChannelException e) {
            e.printStackTrace();
        }
        smppServer.getSessionCreated();
        logger.info("SMPP server started");
        /*System.out.println("Press any key to stop server");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }      */

        /*logger.info("Stopping SMPP server...");
        smppServer.stop();
        logger.info("SMPP server stopped");*/

        logger.info("Server counters: {}", smppServer.getCounters());
    }

    public static class DefaultSmppServerHandler implements SmppServerHandler {

        @Override
        public void sessionBindRequested(Long sessionId, SmppSessionConfiguration sessionConfiguration, final BaseBind bindRequest) throws SmppProcessingException {

        sessionConfiguration.setName("Application.SMPP." + sessionConfiguration.getSystemId());
    }

        @Override
        public void sessionCreated(Long sessionId, SmppServerSession session, BaseBindResp preparedBindResponse) throws SmppProcessingException {
            logger.info("Session created: {}", session);
            System.out.print("kuku1");
            session.serverReady(new TestSmppSessionHandler(session));
        }

        @Override
        public void sessionDestroyed(Long sessionId, SmppServerSession session) {
            logger.info("Session destroyed: {}", session);
            // print out final stats
            System.out.print("kuku2");
            if (session.hasCounters()) {
                logger.info(" final session rx-submitSM: {}", session.getCounters().getRxSubmitSM());
            }
            session.destroy();
        }

    }

    public static class TestSmppSessionHandler extends DefaultSmppSessionHandler {

        private WeakReference<SmppSession> sessionRef;

        @Inject
        private SmsDAO smsDAO;

        public TestSmppSessionHandler() {}

        public TestSmppSessionHandler(SmppSession session) {
            this.sessionRef = new WeakReference<SmppSession>(session);

            System.out.print("kuku3");
        }

        @Override
        public PduResponse firePduRequestReceived(PduRequest pduRequest) {
            SmppSession session = sessionRef.get();

            if ( (pduRequest.getClass() == DeliverSm.class)) {
                DeliverSm dsm = (DeliverSm)pduRequest;

                String sender = dsm.getSourceAddress().getAddress();
                String recipient = dsm.getDestAddress().getAddress();
                String message = CharsetUtil.decode(dsm.getShortMessage(), CharsetUtil.CHARSET_GSM);
                int id = dsm.getCommandId();

                System.out.print("<<<<< " + sender + " ||||| " + recipient + " ||||| " + message + " ||||| " + id + " >>>>>");

                SmsDTO smsDTO = new SmsDTO();
                smsDTO.setSender(sender);
                smsDTO.setBody(message);
                smsDTO.setSendDate(new Date());
                smsDTO.setDeliveryDate(new Date());

                smsDAO.save(smsDTO);
            }
            return pduRequest.createResponse();
        }

    }

}
