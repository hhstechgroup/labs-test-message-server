import com.engagepoint.university.messaging.dao.condao.EmailDAO;
import com.engagepoint.university.messaging.dao.condao.impl.EmailDAOImpl;
import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.entities.Email;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EmailDAOTest {
    private static final Logger LOG = LoggerFactory.getLogger(EmailDAOTest.class);

    EmailDAO emailDAO;

    @Before
    public void setUp() {
        emailDAO = new EmailDAOImpl();
    }

    @Test
    public void shouldGetAllEmails() {
        //Given
        Email email1 = new Email(new EmailDTO("author 1","Hello 1!","Body 1",new Date(),new Date()));
        LOG.info("new emailObject", email1);

        emailDAO.save(email1);
        LOG.info("save emailObject to DB", email1);

        emailDAO.save(new Email(new EmailDTO("author 2","Hello 2!","Body 2",new Date(),new Date())));
        emailDAO.save(new Email(new EmailDTO("author 3","Hello 3!","Body 3",new Date(),new Date())));

        // When
        List<Email> emails = emailDAO.getAll();

        // Then
        assertEquals(3, emails.size());
    }
}
