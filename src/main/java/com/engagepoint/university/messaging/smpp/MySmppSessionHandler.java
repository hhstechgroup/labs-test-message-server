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


import com.cloudhopper.smpp.PduAsyncResponse;
import com.cloudhopper.smpp.SmppConstants;
import com.cloudhopper.smpp.impl.DefaultSmppSessionHandler;
import com.cloudhopper.smpp.pdu.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cloudhopper.commons.charset.CharsetUtil;


/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 09.02.14
 * Time: 15:29
 * To change this template use File | Settings | File Templates.
 */
public class MySmppSessionHandler extends DefaultSmppSessionHandler {
    public static Logger log = LoggerFactory.getLogger(MySmppSessionHandler.class);
 //добавили-------------
    /*protected Client client;
    public MySmppSessionHandler(Client client) {
        this.client = client;
    }*/
//---------------------

    @Override
    public PduResponse firePduRequestReceived(PduRequest pduRequest) {
        if (
                pduRequest.isRequest()
                        && pduRequest.getClass() == DeliverSm.class
                ) {
            System.out.println("Got DELIVER_SM");
            log.debug("Got DELIVER_SM");

            DeliverSm dlr = (DeliverSm)pduRequest;
            System.out.println("Msg id={} "+ dlr.getOptionalParameter(SmppConstants.TAG_RECEIPTED_MSG_ID));
            log.debug("Msg id={}", dlr.getOptionalParameter(SmppConstants.TAG_RECEIPTED_MSG_ID));

            System.out.println("Status={}"+ dlr.getOptionalParameter(SmppConstants.TAG_MSG_STATE));
            log.debug("Status={}", dlr.getOptionalParameter(SmppConstants.TAG_MSG_STATE));

            return pduRequest.createResponse();
        }

        return super.firePduRequestReceived(pduRequest);
    }

    // обработка поступления ответа на запрос в SessionHandler
    @Override
    public void fireExpectedPduResponseReceived(PduAsyncResponse pduAsyncResponse) {
        if (pduAsyncResponse.getResponse().getClass() == SubmitSmResp.class) {
            SubmitSm req = (SubmitSm)pduAsyncResponse.getRequest();
            log.debug("Got response for APPID={}", req.getReferenceObject());
            System.out.println("Got response for APPID={} "+ req.getReferenceObject()+"++");

            SmsSaverFromSmpp smsSaverFromSmpp = new SmsSaverFromSmpp();
            smsSaverFromSmpp.saveSms(req);

            System.out.println("!!! fireExpectedPduResponseReceived req.getDestAddress();"+ req.getDestAddress());
            System.out.println("!!! fireExpectedPduResponseReceived req.getSourceAddress();"+ req.getSourceAddress());
            System.out.println("!!! fireExpectedPduResponseReceived req.getShortMessage() in byte" + req.getShortMessage());//byte
            System.out.println("!!! fireExpectedPduResponseReceived req.getName()"+req.getName());
            System.out.println("!!! fireExpectedPduResponseReceived req.getName()"+req.getReferenceObject());

            String str = CharsetUtil.decode(req.getShortMessage(), CharsetUtil.CHARSET_MODIFIED_UTF8);
            System.out.println("!!! fireExpectedPduResponseReceived str" + str);

           //ТУТ НАДО SaveOrUpdate

            System.out.println("!!!fireExpectedPduResponseReceived  req.getDestAddress();"+req.getSourceAddress());

            SubmitSmResp ssmr = (SubmitSmResp)pduAsyncResponse.getResponse();

            System.out.println("!!! fireExpectedPduResponseReceived ssmr.getResultMessage()" + ssmr.getResultMessage());
            System.out.println("!!! fireExpectedPduResponseReceived ssmr.getReferenceObject()"+ ssmr.getReferenceObject());
            System.out.println("!!! fireExpectedPduResponseReceived ssmr.getName()"+ ssmr.getName());
            System.out.println("!!! fireExpectedPduResponseReceived ssmr.getOptionalParameterCount()"+ ssmr.getOptionalParameterCount());
            System.out.println("!!! fireExpectedPduResponseReceived ssmr.getMessageId()"+ ssmr.getMessageId());

            log.debug("Got response with MSG ID={} for seqnum={}", ssmr.getMessageId(), ssmr.getSequenceNumber());
            System.out.println("Got response with MSG ID={} for seqnum={} "+ ssmr.getMessageId()+"//" + ssmr.getSequenceNumber());
        }
    }
    /*@Override
    public void fireExpectedPduResponseReceived(PduAsyncResponse pduAsyncResponse) {
        if (pduAsyncResponse.getResponse().getClass() == SubmitSmResp.class) {
            SubmitSmResp ssmr = (SubmitSmResp)pduAsyncResponse.getResponse();

            log.debug("Got response with MSG ID={} for seqnum={}", ssmr.getMessageId(), ssmr.getSequenceNumber());
        }
    }*/

    //добавили-------------
   /* @Override
    public void fireChannelUnexpectedlyClosed() {
        client.bind(); // Rebind
    }*/
    //--------------------------

}



