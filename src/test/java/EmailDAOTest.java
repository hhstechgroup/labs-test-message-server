import com.engagepoint.university.messaging.dao.specific.impl.AttachmentDAOImpl;
import com.engagepoint.university.messaging.dao.specific.impl.EmailDAOImpl;
import com.engagepoint.university.messaging.dao.specific.impl.SmsDAOImpl;
import com.engagepoint.university.messaging.dao.specific.impl.UserDAOImpl;
import com.engagepoint.university.messaging.dto.EmailDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Date;

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

        emailDAOImpl.save(emailDTO);
        emailDAOImpl.save(emailDTO1);
        Assert.assertEquals(2, emailDAOImpl.getAll().size());
    }
}
