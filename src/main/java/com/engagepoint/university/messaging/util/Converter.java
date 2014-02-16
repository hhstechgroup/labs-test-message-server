package com.engagepoint.university.messaging.util;

import com.engagepoint.university.messaging.dto.AttachmentDTO;
import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.dto.SmsDTO;
import com.engagepoint.university.messaging.dto.UserDTO;
import com.engagepoint.university.messaging.dto.base.BaseDTO;
import com.engagepoint.university.messaging.entities.Attachment;
import com.engagepoint.university.messaging.entities.Email;
import com.engagepoint.university.messaging.entities.Sms;
import com.engagepoint.university.messaging.entities.User;
import com.engagepoint.university.messaging.entities.base.BaseEntity;

public class Converter implements ToDtoConverter<BaseEntity, BaseDTO>, ToEntityConverter<BaseDTO, BaseEntity> {
    @Override
    public BaseDTO convert(BaseEntity from) {
        return null;
    }
    @Override
    public BaseEntity convert(BaseDTO from) {
        return null;
    }
    public UserDTO convert(User from) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(from.getId());
        userDTO.setEmail(from.getEmail());
        userDTO.setPassword(from.getPassword());
        userDTO.setPhoneNumber(from.getPhoneNumber());
        return userDTO;
    }
    public User convert(UserDTO from) {
        User user = new User();
        user.setId(from.getId());
        user.setEmail(from.getEmail());
        user.setPassword(from.getPassword());
        user.setPhoneNumber(from.getPhoneNumber());
        user.setName(from.getName());
        return user;
    }
    public SmsDTO convert(Sms sms) {
        SmsDTO smsDTO = new SmsDTO();
        smsDTO.setId(sms.getId());
        smsDTO.setSender(sms.getSender());
        smsDTO.setBody(sms.getBody());
        smsDTO.setSendDate(sms.getSendDate());
        smsDTO.setDeliveryDate(sms.getDeliveryDate());
        return smsDTO;
    }
    public Sms convert(SmsDTO from) {
        Sms sms = new Sms();
        sms.setId(from.getId());
        sms.setSender(from.getSender());
        sms.setBody(from.getBody());
        sms.setSendDate(from.getSendDate());
        sms.setDeliveryDate(from.getDeliveryDate());
        return sms;
    }
    public EmailDTO convert(Email email) {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setId(email.getId());
        emailDTO.setSender(email.getSender());
        emailDTO.setSubject(email.getSubject());
        emailDTO.setBody(email.getBody());
        emailDTO.setSendDate(email.getSendDate());
        emailDTO.setDeliveryDate(email.getDeliveryDate());
        return emailDTO;
    }
    public Email convert(EmailDTO from) {
        Email email = new Email();
        email.setId(from.getId());
        email.setSender(from.getSender());
        email.setSubject(from.getSubject());
        email.setBody(from.getBody());
        email.setSendDate(from.getSendDate());
        email.setDeliveryDate(from.getDeliveryDate());
        return email;
    }
    public AttachmentDTO convert(Attachment attachment) {
        AttachmentDTO attachmentDTO = new AttachmentDTO();
        attachmentDTO.setId(attachment.getId());
        attachmentDTO.setMimeType(attachment.getMimeType());
        attachmentDTO.setName(attachment.getName());
        attachmentDTO.setContent(attachment.getContent());
        return attachmentDTO;
    }
    public Attachment convert(AttachmentDTO from) {
        Attachment attachment = new Attachment();
        attachment.setId(from.getId());
        attachment.setMimeType(from.getMimeType());
        attachment.setName(from.getName());
        attachment.setContent(from.getContent());
        return attachment;
    }
}
