package com.engagepoint.university.messaging.beans;

import com.engagepoint.university.messaging.dao.condao.AttachmentDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

@Named
public class AttachmentBean {
    private static final Logger LOG = LoggerFactory.getLogger(AttachmentBean.class);

    @Inject
    private AttachmentDAO attachmentDAO;

    public List<ObjectOutputStream> getAttachments(int id){
        String lol = "lol";
        BASE64Encoder encoder = new BASE64Encoder();
        String code = encoder.encode(lol.getBytes());
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] result;
        ObjectOutputStream os = null;
        try {
             result = decoder.decodeBuffer(code);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            os =  new ObjectOutputStream(baos);
            os.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }



        return Arrays.asList(os);
    }
}
