package com.engagepoint.university.messaging.smpp;

import com.cloudhopper.commons.charset.CharsetUtil;
import com.cloudhopper.smpp.PduAsyncResponse;
import com.cloudhopper.smpp.impl.DefaultSmppSessionHandler;
import com.cloudhopper.smpp.pdu.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MySmppSessionHandler extends DefaultSmppSessionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(MySmppSessionHandler.class);

    @Override
    public PduResponse firePduRequestReceived(PduRequest pduRequest) {
        if (pduRequest.isRequest()
                && pduRequest.getClass() == DeliverSm.class) {
            return pduRequest.createResponse();
        }

        return super.firePduRequestReceived(pduRequest);
    }

    // обработка поступления ответа на запрос в SessionHandler
    @Override
    public void fireExpectedPduResponseReceived(PduAsyncResponse pduAsyncResponse) {
        if (pduAsyncResponse.getResponse().getClass() == SubmitSmResp.class) {
            SubmitSm req = (SubmitSm) pduAsyncResponse.getRequest();

            LOG.info("Got response for APPID={}", req.getReferenceObject());
            LOG.info("!!! SubmitSm req.getDestAddress(); - " + req.getDestAddress());
            LOG.info("!!! SubmitSm req.getSourceAddress(); - " + req.getSourceAddress());
            LOG.info("!!! SubmitSm req.getShortMessage() in byte - " + req.getShortMessage());

            String str = CharsetUtil.decode(req.getShortMessage(), CharsetUtil.CHARSET_MODIFIED_UTF8);
            LOG.info("!!! SubmitSm body - " + str);

            SubmitSmResp ssmr = (SubmitSmResp) pduAsyncResponse.getResponse();

            LOG.info("Got response with MSG ID={} for seqnum={}", ssmr.getMessageId(), ssmr.getSequenceNumber());
        }
    }
}



