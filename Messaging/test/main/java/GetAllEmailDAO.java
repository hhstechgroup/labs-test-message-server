import com.engagepoint.university.messaging.dao.impl.EmailDAOImpl;
import com.engagepoint.university.messaging.dto.EmailMessageDTO;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class GetAllEmailDAO {
    @Test
    public void testGetAllEmail() {
        EmailDAOImpl emailDAO = new EmailDAOImpl();
        emailDAO.saveEmail(new EmailMessageDTO("lol", "lol2", "dfg", "sdfghjkjhgfdsdxgfcjg", "url", new Date(12345687L)));
        System.out.println(emailDAO.getAllEmails());
        Assert.assertNotNull(emailDAO.getAllEmails());
    }
}
