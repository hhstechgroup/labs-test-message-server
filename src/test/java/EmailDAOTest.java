import com.engagepoint.university.messaging.dao.impl.EmailDAOImpl;
import com.engagepoint.university.messaging.dto.EmailMessageDTO;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class EmailDAOTest {
    @Test
    public void getAllEmail(){
        EmailDAOImpl dao = new EmailDAOImpl();
        dao.saveEmail(new EmailMessageDTO("lol","rew","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.saveEmail(new EmailMessageDTO("lol","rew","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.saveEmail(new EmailMessageDTO("lol","rew","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.saveEmail(new EmailMessageDTO("lol1","rew1","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.saveEmail(new EmailMessageDTO("lol1","rew1","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.saveEmail(new EmailMessageDTO("lol1","rew1","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.saveEmail(new EmailMessageDTO("lol2","rew2","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.saveEmail(new EmailMessageDTO("lol2","rew2","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.saveEmail(new EmailMessageDTO("lol2","rew2","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
//        System.out.println(dao.getAllEmails());




        Assert.assertNotNull(dao.getAllEmails());
        Assert.assertNotNull(dao.getEmail(1));
        Assert.assertNotNull(dao.getEmailByReceiver("rew"));
        Assert.assertNotNull(dao.getEmailBySender("lol"));

        dao.deleteEmail(2);
        EmailMessageDTO email1 = dao.getEmail(2);
        Assert.assertNull(email1);
    }
}
