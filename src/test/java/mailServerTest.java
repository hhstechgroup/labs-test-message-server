import com.engagepoint.university.messaging.dao.condao.EmailDAO;
import com.engagepoint.university.messaging.dao.condao.impl.EmailDAOImpl;
import com.engagepoint.university.messaging.dao.serverdao.impl.EmailServerImpl;
import com.engagepoint.university.messaging.entities.Email;
import com.icegreen.greenmail.user.GreenMailUser;
import com.icegreen.greenmail.user.UserException;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.validation.ValidationException;
import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class mailServerTest {
    private static final String USER_EMAIL_ADDRESS = "hascode@localhost.com";
    private static final String USER_NAME = "hascode";
    private static final String USER_PASSWORD = "abcdef123";
    private static final String EMAIL_TO = "someone@localhost.com";
    private static final String EMAIL_SUBJECT = "Test E-Mail";
    private static final String EMAIL_TEXT = "Hello Miklosh!";
    private static final String LOCALHOST = "127.0.0.1";
    private static final String WRONG_USER_EMAIL = "hascode@.localhost.com";
    private static final String WRONG_USER_NAME = "Mykola@";

    private GreenMail mailServer;
    private EmailServerImpl emailServerDAO;
    private EmailDAO dbDAO;

    @Before
    public void startUp() {
        mailServer = new GreenMail(ServerSetupTest.SMTP);
        emailServerDAO = new EmailServerImpl(mailServer);
        dbDAO = new EmailDAOImpl();
        mailServer.start();
    }

    @After
    public void tearDown() {
        mailServer.stop();
    }

    @Test
    public void sendAndGetOneMessageFromServer() throws IOException, MessagingException,
            UserException, InterruptedException {
        GreenMailUser user = emailServerDAO.createUser(USER_EMAIL_ADDRESS,USER_NAME,USER_PASSWORD);
        assertNotNull(user);
        assertNotNull(user.getEmail());
        assertEquals(USER_EMAIL_ADDRESS, user.getEmail());
        assertNotNull(user.getLogin());
        assertEquals(USER_NAME, user.getLogin());
        assertNotNull(user.getPassword());
        assertEquals(USER_PASSWORD, user.getPassword());
        Session session = emailServerDAO.initSession(LOCALHOST);
        assertNotNull(session);
        Message message = emailServerDAO.createMessage(USER_EMAIL_ADDRESS,EMAIL_TO,EMAIL_SUBJECT,EMAIL_TEXT,session);
        assertNotNull(message);
        emailServerDAO.sendEmail(LOCALHOST, USER_EMAIL_ADDRESS, USER_PASSWORD, session, message);
        Message[] messages = emailServerDAO.getTestEmailArray();
        assertNotNull(messages);
        assertEquals(1,messages.length);
        assertNotNull(messages[0].getRecipients(Message.RecipientType.TO));
        assertEquals(EMAIL_TO,messages[0].getRecipients(Message.RecipientType.TO)[0].toString());
        assertNotNull(messages[0].getSubject());
        assertEquals(EMAIL_SUBJECT,messages[0].getSubject());
        assertNotNull(messages[0].getContent().toString());
    }

    @Test(expected = ValidationException.class)
     public void wrongUserEmailTest() throws IOException, MessagingException,
            UserException, InterruptedException {
        GreenMailUser user = emailServerDAO.createUser(WRONG_USER_EMAIL, USER_NAME, USER_PASSWORD);
        assertNotNull(user);
    }

    @Test(expected = ValidationException.class)
    public void wrongUserLoginTest() throws IOException, MessagingException,
            UserException, InterruptedException {
        GreenMailUser user = emailServerDAO.createUser(USER_EMAIL_ADDRESS, WRONG_USER_NAME, USER_PASSWORD);
        assertNotNull(user);
    }

    @Test
    public void setMessageToDB () throws MessagingException, IOException {
        GreenMailUser user = emailServerDAO.createUser(USER_EMAIL_ADDRESS,USER_NAME,USER_PASSWORD);
        assertNotNull(user);
        assertNotNull(user.getEmail());
        assertEquals(USER_EMAIL_ADDRESS, user.getEmail());
        assertNotNull(user.getLogin());
        assertEquals(USER_NAME, user.getLogin());
        assertNotNull(user.getPassword());
        assertEquals(USER_PASSWORD, user.getPassword());
        Session session = emailServerDAO.initSession(LOCALHOST);
        assertNotNull(session);
        Message message = emailServerDAO.createMessage(USER_EMAIL_ADDRESS,EMAIL_TO,EMAIL_SUBJECT,EMAIL_TEXT,session);
        assertNotNull(message);
        emailServerDAO.sendEmail(LOCALHOST, USER_EMAIL_ADDRESS, USER_PASSWORD, session, message);
        Message[] messages = emailServerDAO.getTestEmailArray();
        assertNotNull(messages);
        assertEquals(1, messages.length);
        assertNotNull(messages[0].getRecipients(Message.RecipientType.TO));
        assertEquals(EMAIL_TO,messages[0].getRecipients(Message.RecipientType.TO)[0].toString());
        assertNotNull(messages[0].getSubject());
        assertEquals(EMAIL_SUBJECT, messages[0].getSubject());
        assertNotNull(messages[0].getContent().toString());
        Email email = new Email();
        email.setSender(messages[0].getFrom()[0].toString());
        email.setSubject(messages[0].getSubject());
        email.setBody(messages[0].getContent().toString());
        email.setSendDate(messages[0].getSentDate());
        email.setDeliveryDate(messages[0].getSentDate());
        dbDAO.save(email);
    }
}
