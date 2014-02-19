package junit;

import com.engagepoint.university.messaging.dao.specific.EmailDAO;
import com.engagepoint.university.messaging.dao.specific.impl.EmailDAOImpl;
import com.engagepoint.university.messaging.dto.EmailDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Date;

public class LargeMessagesBodyTest {
    private static final Logger LOG = LoggerFactory.getLogger(LargeMessagesBodyTest.class);

    @Inject
    EmailDAO emailDAOImpl;

    @Before
    public void setUp() {
        emailDAOImpl = new EmailDAOImpl();
    }

    @Test
    public void shouldGetAllEmailsWithLargeBody() {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setSender("engagepoint-sender-email");
        emailDTO.setSubject("engagepoint-sender-subject");
        emailDTO.setBody("engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-" +
                "engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-" +
                "engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-" +
                "engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-" +
                "engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-" +
                "engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-" +
                "engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-" +
                "engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-" +
                "engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-" +
                "engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-" +
                "engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-" +
                "engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-" +
                "engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-" +
                "engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body-engagepoint-sender-body");
        emailDTO.setSendDate(new Date());
        emailDTO.setDeliveryDate(new Date());

        EmailDTO emailDTO1 = new EmailDTO();
        emailDTO1.setSender("engagepoint-sender-email1");
        emailDTO1.setSubject("engagepoint-sender-subject1");
        emailDTO1.setBody("engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-" +
                "engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-" +
                "engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-" +
                "engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-" +
                "engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-" +
                "engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-" +
                "engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-" +
                "engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-" +
                "engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-" +
                "engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-" +
                "engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-" +
                "engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-" +
                "engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-" +
                "engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-" +
                "engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-" +
                "engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1-engagepoint-sender-body1");
        emailDTO1.setSendDate(new Date());
        emailDTO1.setDeliveryDate(new Date());

        emailDAOImpl.save(emailDTO);
        emailDAOImpl.save(emailDTO1);
        Assert.assertEquals(2, emailDAOImpl.getAll().size());
    }

}
