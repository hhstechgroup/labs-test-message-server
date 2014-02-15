import com.engagepoint.university.messaging.dao.specific.impl.AttachmentDAOImpl;
import com.engagepoint.university.messaging.dao.specific.impl.EmailDAOImpl;
import com.engagepoint.university.messaging.dao.specific.impl.SmsDAOImpl;
import com.engagepoint.university.messaging.dao.specific.impl.UserDAOImpl;
import com.engagepoint.university.messaging.dto.AttachmentDTO;
import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.dto.SmsDTO;
import com.engagepoint.university.messaging.dto.UserDTO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EmailDAOTest {
    private static final Logger LOG = LoggerFactory.getLogger(EmailDAOTest.class);

    @Inject
    AttachmentDAOImpl attachmentDAOImpl;
    @Inject
    EmailDAOImpl emailDAOImpl;
    @Inject
    SmsDAOImpl smsDAOImpl;
    @Inject
    UserDAOImpl userDAOImpl;

    @Before
    public void setUp() {

    }

    @Test
    public void shouldGetAllEmails() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("engagepoint");
        userDTO.setEmail("engagepoint@engagepoint.com");
        userDTO.setPhoneNumber("555-777-000-888");
        userDTO.setPassword("passphrase");

        UserDTO userDTO1 = new UserDTO();
        userDTO1.setName("engagepoint1");
        userDTO1.setEmail("engagepoint1@engagepoint.com");
        userDTO1.setPhoneNumber("111-777-222-888");
        userDTO1.setPassword("passphrase1");

        SmsDTO smsDTO = new SmsDTO();
        smsDTO.setSender("engagepoint-sender");
        smsDTO.setBody("engagepoint-body");
        smsDTO.setSendDate(new Date());
        smsDTO.setDeliveryDate(new Date());

        SmsDTO smsDTO1 = new SmsDTO();
        smsDTO1.setSender("engagepoint-sender1");
        smsDTO1.setBody("engagepoint-body1");
        smsDTO1.setSendDate(new Date());
        smsDTO1.setDeliveryDate(new Date());

        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setSender("engagepoint-sender-email");
        emailDTO.setSubject("engagepoint-sender-subject");
        emailDTO.setBody("engagepoint-sender-body");
        emailDTO.setSendDate(new Date());
        emailDTO.setDeliveryDate(new Date());

        EmailDTO emailDTO1 = new EmailDTO();
        emailDTO1.setSender("engagepoint-sender-email1");
        emailDTO1.setSubject("engagepoint-sender-subject1");
        emailDTO1.setBody("engagepoint-sender-body1");
        emailDTO1.setSendDate(new Date());
        emailDTO1.setDeliveryDate(new Date());

        AttachmentDTO attachmentDTO = new AttachmentDTO();
        attachmentDTO.setMimeType("engagepoint-sender-MimeType");
        attachmentDTO.setName("engagepoint-attachment-name");
        attachmentDTO.setContent("engagepoint-attachment-content");

        AttachmentDTO attachmentDTO1 = new AttachmentDTO();
        attachmentDTO1.setMimeType("engagepoint-sender-MimeType1");
        attachmentDTO1.setName("engagepoint-attachment-name1");
        attachmentDTO1.setContent("engagepoint-attachment-content1");

        userDAOImpl.save(userDTO);
        userDAOImpl.save(userDTO1);
        Assert.assertEquals(2, userDAOImpl.getAll().size());

        smsDAOImpl.save(smsDTO);
        smsDAOImpl.save(smsDTO1);
        Assert.assertEquals(2, smsDAOImpl.getAll().size());

        emailDAOImpl.save(emailDTO);
        emailDAOImpl.save(emailDTO1);
        Assert.assertEquals(2, emailDAOImpl.getAll().size());

        attachmentDAOImpl.save(attachmentDTO);
        attachmentDAOImpl.save(attachmentDTO1);
        Assert.assertEquals(2, attachmentDAOImpl.getAll().size());
    }
}
