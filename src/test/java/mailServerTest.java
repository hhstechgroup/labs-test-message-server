import com.engagepoint.university.messaging.util.EntityManagerUtil;
import com.icegreen.greenmail.user.GreenMailUser;
import com.icegreen.greenmail.user.UserException;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class mailServerTest {
    private static final String EMAIL_USER_ADDRESS = "hascode@localhost.com";
    private static final String USER_NAME = "hascode";
    private static final String USER_PASSWORD = "abcdef123";
    private static final String EMAIL_TO = "someone@localhost.com";
    private static final String EMAIL_SUBJECT = "Test E-Mail";
    private static final String EMAIL_TEXT = "Hello Miklosh!";
    private static final String LOCALHOST = "127.0.0.1";

    private GreenMail mailServer;
    private EntityManager em;
    private EntityManagerUtil emu;

    @Before
    public void setUp() {
        mailServer = new GreenMail(ServerSetup.POP3);
        emu = new EntityManagerUtil();
        em = emu.getEntityManager();
        mailServer.start();
    }

    @After
    public void tearDown() {
        mailServer.stop();
    }

    @Test
    public void getMessage() throws IOException, MessagingException,
            UserException, InterruptedException {
        GreenMailUser user = mailServer.setUser(EMAIL_USER_ADDRESS, USER_NAME, USER_PASSWORD);
        MimeMessage message = new MimeMessage((Session) null);
        message.setFrom(new InternetAddress(EMAIL_TO));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(EMAIL_USER_ADDRESS));
        message.setSubject(EMAIL_SUBJECT);
        message.setText(EMAIL_TEXT);
        user.deliver(message);

        /*Email are stored in mailServer sever queue in the array
        * so we can access e-mails by array index
        * */
//        checking that we had received e-mail
        assertNotNull(mailServer.getReceivedMessages()[0].getContent());
//        checking received e-mail content : text
        assertThat((String) mailServer.getReceivedMessages()[0].getContent(),
                equalTo("Hello Miklosh!"));
//        checking received e-mail content : sender
        assertThat(mailServer.getReceivedMessages()[0].getFrom()[0].toString(),
                equalTo("someone@localhost.com"));
//        checking received e-mail content : subject
        assertThat(mailServer.getReceivedMessages()[0].getSubject(),
                equalTo("Test E-Mail"));
    }
}
