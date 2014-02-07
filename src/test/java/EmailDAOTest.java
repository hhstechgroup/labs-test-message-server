import com.engagepoint.university.messaging.dao.impl.EmailDAOImpl;
import com.engagepoint.university.messaging.entity.EmailMessageEntity;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class EmailDAOTest {

    private EmailDAOImpl dao;

    @Before
    public void setUp(){
        dao = new EmailDAOImpl();
    }

    @Test
    public void shouldGetAllSenderEmails(){
        // Given
        EmailMessageEntity fistEmail = new EmailMessageEntity("lol","rew","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L));
        EmailMessageEntity secondEmail = new EmailMessageEntity("lol","rew","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L));
        EmailMessageEntity thirdEmail = new EmailMessageEntity("foo","rew","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L));
        dao.save(fistEmail);
        dao.save(secondEmail);
        dao.save(thirdEmail);

        // When
        List<EmailMessageEntity> emails = dao.getEmailsBySender("lol");

        // Then
        assertThat(emails, hasItems(fistEmail, secondEmail));
        assertThat(emails, not(hasItems(thirdEmail)));
    }

    @Test
    public void shouldGetAllEmails(){
        // Given
        dao.save(new EmailMessageEntity("lol","rew","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.save(new EmailMessageEntity("rol","rew","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));
        dao.save(new EmailMessageEntity("fol","rew","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L)));

        // When
        List<EmailMessageEntity> emails = dao.getAll();

        // Then
        assertEquals(3, emails.size());
    }
     @Test
    public void shouldDeleteEmail(){
         // Given
         EmailMessageEntity email = new EmailMessageEntity("lol","rew","qwerty","sdfghjkllkjh5+64","url",new Date(12345678L));
         dao.save(email);

         // When
         dao.delete(email);

         //Then
         assertNull(dao.getById(0));
    }
}
