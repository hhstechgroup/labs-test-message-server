package com.engagepoint.university.messaging.smtp;

import com.engagepoint.university.messaging.dto.AttachmentDTO;
import sun.misc.BASE64Encoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class SMTPMailParser {

    AttachmentDTO attdto;
    Collection<AttachmentDTO> atachCollection;
    private BASE64Encoder encoder = new BASE64Encoder();
    private final String WARNING1 = "Sorry, the attachment was too big.";
    String encodedAttachmentWarning = encoder.encode(WARNING1.getBytes());
    private final String WARNING2 = "Sorry, the message body was too big.";

    public String getMailSendDate(String stream) {
        String[] wer1 = null;
        String[] wer = stream.split("Date:");
        if (wer.length > 1) {
            wer1 = wer[1].split("From:");
            return wer1[0].trim();
        }
        return null;
    }

    public String getMailSubject(String stream) {
        String[] wer2 = stream.split("Subject:");
        String[] wer3 = wer2[1].split("Content-Type:");
        return wer3[0].trim();
    }

    public String getMailContent(String stream) {
        String s = "";
        if (stream.contains("filename")) {
            String[] wer4 = stream.split("7bit");
            String[] wer5 = wer4[1].split("-");
            s = wer5[0].trim();
        } else {
            String[] wer4 = stream.split("7bit");
            s = wer4[1].trim();
        }
        return s;
    }

    public String getAttachmentName(String stream) {
        String[] att = stream.split("filename=\"");
        String[] att1 = att[1].split("\"");
        // use trim to get read of useless string remains
        // use substring to prevent "too long attachment name" error
        return att1[0].trim().substring(0, 20);
    }

    public String getAttachmentBase64(String stream) {
        String[] att = stream.split("filename=\"");
        String[] att1 = att[1].split("\"");
        return att1[1].trim();
    }

    public String getBoundary(String stream) {
        String[] bound = stream.split("boundary=\"");
        String[] bound1 = bound[1].split("\"");
        return bound1[0].trim();
    }

    public Collection<AttachmentDTO> getAttachment(String stream) {
        atachCollection = new ArrayList<>();
        String[] arr = stream.split("Content-Disposition:");
        ArrayList<String> attaches = new ArrayList<>(Arrays.asList(arr));
        attaches.remove(0);
//            String boundString = getBoundary(stream);
//            byte [] boundArr = boundString.getBytes();
        for (String attachments : attaches) {
            attdto = new AttachmentDTO();
            attdto.setName(getAttachmentName(attachments));
            byte[] checkAttBody = getAttachmentBase64(attachments).getBytes();
            if (!(checkAttBody.length > 15000000)) {
                String attBase = getAttachmentBase64(attachments);
//                    byte[] att = attBase.getBytes();
                attdto.setContent(attBase);
            } else {
                attdto.setContent(encodedAttachmentWarning);
            }
            atachCollection.add(attdto);
        }
        return atachCollection;
    }

    public Collection<String> checkAttachmentSize(String stream) {
        String attBase = "";
        Collection<String> atachCollection = new ArrayList<>();
        String[] arr = stream.split("Content-Disposition:");
        ArrayList<String> attaches = new ArrayList<>(Arrays.asList(arr));
        attaches.remove(0);
        for (String attachments : attaches) {
            attBase = getAttachmentBase64(attachments);
            atachCollection.add(attBase);
        }
        return atachCollection;
    }

    public long totalAttachmentSize(String s) {
        long i = 0;
        for (String a : checkAttachmentSize(s)) {
            byte[] r = a.getBytes();
            i += r.length;
        }
        return i;
    }
}
