package com.engagepoint.university.messaging.util;

import com.engagepoint.university.messaging.dto.*;
import com.engagepoint.university.messaging.dto.base.BaseDTO;
import com.engagepoint.university.messaging.entity.*;
import com.engagepoint.university.messaging.entity.base.BaseEntity;

import java.util.ArrayList;
import java.util.Collection;

public final class Converter implements ToDtoConverter<BaseEntity, BaseDTO>, ToEntityConverter<BaseDTO, BaseEntity> {

    private Converter() {
    }

    public static UserDTO convert(User from) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(from.getName());
        userDTO.setId(from.getId());
        userDTO.setEmail(from.getEmail());
        userDTO.setPassword(from.getPassword());
        userDTO.setPhoneNumber(from.getPhoneNumber());
        return userDTO;
    }

    public static User convert(UserDTO from) {
        User user = new User();
        user.setId(from.getId());
        user.setEmail(from.getEmail());
        user.setPassword(from.getPassword());
        user.setPhoneNumber(from.getPhoneNumber());
        user.setName(from.getName());
        return user;
    }

    public static SmsDTO convert(Sms sms) {
        SmsDTO smsDTO = new SmsDTO();
        smsDTO.setId(sms.getId());
        smsDTO.setSender(sms.getSender());
        smsDTO.setBody(sms.getBody());
        smsDTO.setSendDate(sms.getSendDate());
        smsDTO.setDeliveryDate(sms.getDeliveryDate());
        smsDTO.setRecipient(sms.getRecipient());
        return smsDTO;
    }

    public static Sms convert(SmsDTO from) {
        Sms sms = new Sms();
        sms.setId(from.getId());
        sms.setSender(from.getSender());
        sms.setBody(from.getBody());
        sms.setSendDate(from.getSendDate());
        sms.setDeliveryDate(from.getDeliveryDate());
        sms.setRecipient(from.getRecipient());
        return sms;
    }

    public static EmailDTO convert(Email email) {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setId(email.getId());
        emailDTO.setSender(email.getSender());
        emailDTO.setSubject(email.getSubject());
        emailDTO.setBody(email.getBody());
        emailDTO.setSendDate(email.getSendDate());
        emailDTO.setDeliveryDate(email.getDeliveryDate());
        emailDTO.setRecipient(email.getRecipient());
        if (email.getAttachmentCollection() != null) {
            emailDTO.setAttachmentCollection(convertInAttachmentDTO(email.getAttachmentCollection()));
        }
        return emailDTO;
    }

    public static Email convert(EmailDTO from) {
        Email email = new Email();
        email.setId(from.getId());
        email.setSender(from.getSender());
        email.setSubject(from.getSubject());
        email.setBody(from.getBody());
        email.setSendDate(from.getSendDate());
        email.setDeliveryDate(from.getDeliveryDate());
        email.setRecipient(from.getRecipient());
        if (from.getAttachmentCollection() != null) {
            email.setAttachmentCollection(convertInAttachment(from.getAttachmentCollection()));
        }
        return email;
    }

    public static AttachmentDTO convert(Attachment attachment) {
        AttachmentDTO attachmentDTO = new AttachmentDTO();
        attachmentDTO.setId(attachment.getId());
        attachmentDTO.setName(attachment.getName());
        attachmentDTO.setContent(attachment.getContent());
        return attachmentDTO;
    }

    public static Attachment convert(AttachmentDTO from) {
        Attachment attachment = new Attachment();
        attachment.setId(from.getId());
        attachment.setName(from.getName());
        attachment.setContent(from.getContent());
        return attachment;
    }

    public static Collection<Attachment> convertInAttachment(Collection<AttachmentDTO> attachmentDTOs) {
        Collection<Attachment> attachments = new ArrayList<>(attachmentDTOs.size());
        for (AttachmentDTO attachmentDTO : attachmentDTOs) {
            attachments.add(convert(attachmentDTO));
        }
        return attachments;
    }

    public static Collection<AttachmentDTO> convertInAttachmentDTO(Collection<Attachment> attachments) {
        Collection<AttachmentDTO> attachmentDTOs = new ArrayList<>(attachments.size());
        for (Attachment attachment : attachments) {
            attachmentDTOs.add(convert(attachment));
        }
        return attachmentDTOs;
    }

    public static JmsDTO convert(Jms from) {
        JmsDTO jmsDTO = new JmsDTO();
        jmsDTO.setId(from.getId());
        jmsDTO.setMsg(from.getMsg());
        jmsDTO.setMsgidProd(from.getMsgidProd());
        return jmsDTO;
    }

    public static Jms convert(JmsDTO from) {
        Jms jms = new Jms();
        jms.setId(from.getId());
        jms.setMsg(from.getMsg());
        jms.setMsgidProd(from.getMsgidProd());
        return jms;
    }

    public static ReqResp convert(ReqRespDTO reqRespDTO) {
        ReqResp reqResp = new ReqResp();
        reqResp.setUrl(reqRespDTO.getUrl());
        reqResp.setRequest(reqRespDTO.getRequest());
        reqResp.setResponse(reqRespDTO.getResponse());
        return reqResp;
    }

    public static ReqRespDTO convert(ReqResp reqResp) {
        ReqRespDTO reqRespDTO = new ReqRespDTO();
        reqRespDTO.setId(reqResp.getId());
        reqRespDTO.setUrl(reqResp.getUrl());
        reqRespDTO.setRequest(reqResp.getRequest());
        reqRespDTO.setResponse(reqResp.getResponse());
        return reqRespDTO;
    }

    @Override
    public BaseDTO convert(BaseEntity from) {
        return null;
    }

    @Override
    public BaseEntity convert(BaseDTO from) {
        return null;
    }
}
