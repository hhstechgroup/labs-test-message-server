import com.engagepoint.university.messaging.dao.condao.EmailDAO;
import com.engagepoint.university.messaging.dao.condao.implementation.EmailDAOImpl;
import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.entities.Email;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EmailDAOTest {

    EmailDAO emailDao;

    @Before
    public void setUp() {
        emailDao = new EmailDAOImpl();
    }

    @Test
    public void shouldGetAllEmails() {
        EmailDTO newEmailDTO = new EmailDTO();
        newEmailDTO.setSender("author 1");
        newEmailDTO.setSubject("Subject: Hello from author 1.");
        newEmailDTO.setBody("Email body from author 1.");
        newEmailDTO.setSendDate(new Date(12345678L));
        newEmailDTO.setDeliveryDate(new Date(123456789L));

        EmailDTO newEmailDTO1 = new EmailDTO();
        newEmailDTO1.setSender("author 2");
        newEmailDTO1.setSubject("Subject: Hello from author 2.");
        newEmailDTO1.setBody("Email body from author 2.");
        newEmailDTO1.setSendDate(new Date(12345678L));
        newEmailDTO1.setDeliveryDate(new Date(123456789L));

        EmailDTO newEmailDTO2 = new EmailDTO();
        newEmailDTO2.setSender("author 2");
        newEmailDTO2.setSubject("Subject: Hello from author 2.");
        newEmailDTO2.setBody("Email body from author 2.");
        newEmailDTO2.setSendDate(new Date(12345678L));
        newEmailDTO2.setDeliveryDate(new Date(123456789L));

        Email email = new Email(newEmailDTO);
        Email email1 = new Email(newEmailDTO1);
        Email email2 = new Email(newEmailDTO2);

        emailDao.save(email);
        emailDao.save(email1);
        emailDao.save(email2);

        // When
        List<Email> emails = emailDao.getAll();

        // Then
        assertEquals(3, emails.size());
    }
}
