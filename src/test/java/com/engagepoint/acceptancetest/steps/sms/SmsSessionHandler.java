package com.engagepoint.acceptancetest.steps.sms;

import com.cloudhopper.commons.charset.CharsetUtil;
import com.cloudhopper.smpp.PduAsyncResponse;
import com.cloudhopper.smpp.SmppConstants;
import com.cloudhopper.smpp.impl.DefaultSmppSessionHandler;
import com.cloudhopper.smpp.pdu.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SmsSessionHandler extends DefaultSmppSessionHandler {
    public static Logger log = LoggerFactory.getLogger(SmsSessionHandler.class);

    @Override
    public PduResponse firePduRequestReceived(PduRequest pduRequest) {
        if (pduRequest.isRequest()
                && pduRequest.getClass() == DeliverSm.class) {
            System.out.println("Got DELIVER_SM");
            log.info("Got DELIVER_SM");

            DeliverSm dlr = (DeliverSm) pduRequest;

            System.out.println("Msg id={} " + dlr.getOptionalParameter(SmppConstants.TAG_RECEIPTED_MSG_ID));
            log.info("Msg id={}", dlr.getOptionalParameter(SmppConstants.TAG_RECEIPTED_MSG_ID));

            System.out.println("Status={}" + dlr.getOptionalParameter(SmppConstants.TAG_MSG_STATE));
            log.info("Status={}", dlr.getOptionalParameter(SmppConstants.TAG_MSG_STATE));

            return pduRequest.createResponse();
        }

        return super.firePduRequestReceived(pduRequest);
    }

    // обработка поступления ответа на запрос в SessionHandler
    @Override
    public void fireExpectedPduResponseReceived(PduAsyncResponse pduAsyncResponse) {
        if (pduAsyncResponse.getResponse().getClass() == SubmitSmResp.class) {
            SubmitSm req = (SubmitSm) pduAsyncResponse.getRequest();

            log.info("Got response for APPID={}", req.getReferenceObject());
            log.info("!!! SubmitSm req.getDestAddress(); - " + req.getDestAddress());
            log.info("!!! SubmitSm req.getSourceAddress(); - " + req.getSourceAddress());
            log.info("!!! SubmitSm req.getShortMessage() in byte - " + req.getShortMessage());

            String str = CharsetUtil.decode(req.getShortMessage(), CharsetUtil.CHARSET_MODIFIED_UTF8);
            log.info("!!! SubmitSm body - " + str);

            SubmitSmResp ssmr = (SubmitSmResp) pduAsyncResponse.getResponse();

            System.out.println("!!! fireExpectedPduResponseReceived ssmr.getResultMessage()" + ssmr.getResultMessage());
            System.out.println("!!! fireExpectedPduResponseReceived ssmr.getName()" + ssmr.getName());

            log.info("Got response with MSG ID={} for seqnum={}", ssmr.getMessageId(), ssmr.getSequenceNumber());
        }
    }
}



