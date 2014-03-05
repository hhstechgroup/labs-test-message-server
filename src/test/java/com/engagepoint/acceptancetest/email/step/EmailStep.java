package com.engagepoint.acceptancetest.email.step;

import com.engagepoint.acceptancetest.base.steps.JbehaveBaseSteps;
import net.thucydides.core.pages.Pages;
import org.jbehave.core.annotations.When;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

public class EmailStep extends JbehaveBaseSteps {

    Transport bus;
    Message msg;
    InternetAddress[] address;

    public EmailStep(Pages pages) {
        super(pages);
    }


    @When("'$from' send '$to' email to '$host':'$port' with '$subject' and '$text'")
    public void sendEmail(String host, String port, String to, String from, String subject, String text) {
        try {
            createConnection(host, port, to, from, subject);
            sendEmailText(text);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    @When("'$from' send '$to' email to '$host':'$port' with '$subject' and html '$text'")
    public void sendEmailwithHTMLcontent(String host, String port, String to, String from, String subject, String text) {
        try {
            createConnection(host, port, to, from, subject);
            sendEmailHTML(text);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @When("'$from' send '$to' email to '$host':'$port' with '$subject' and '$text' and attachment '$path'")
    public void sendEmailwithAttachment(String host, String port, String to, String from, String subject, String text, String path) {
        try {
            createConnection(host, port, to, from, subject);
            sendEmailAttachment(text, path);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void createConnection(String host, String port, String to, String from, String subject) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
//        props.put("mail.debug", "true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props);

        bus = session.getTransport("smtp");
        bus.connect(host, "username", "password");

        msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        address = new InternetAddress[]{new InternetAddress(to)};
        msg.setRecipients(Message.RecipientType.TO, address);
        msg.setRecipients(Message.RecipientType.CC,
                InternetAddress.parse(to, true));
        msg.setRecipients(Message.RecipientType.BCC,
                InternetAddress.parse(to, false));
        msg.setSubject(subject);
        msg.setSentDate(new Date());
    }

    public void sendEmailText(String text) throws MessagingException {
        msg.setText(text);
        msg.saveChanges();
        bus.sendMessage(msg, address);
    }

    public void sendEmailAttachment(String text, String path) throws MessagingException {
        MimeBodyPart p1 = new MimeBodyPart();
        p1.setText(text);

        MimeBodyPart p2 = new MimeBodyPart();
        FileDataSource fds = new FileDataSource(path);
        p2.setDataHandler(new DataHandler(fds));
        p2.setFileName(fds.getName());

        Multipart mp = new MimeMultipart();
        mp.addBodyPart(p1);
        mp.addBodyPart(p2);

        msg.setContent(mp);
        msg.saveChanges();
        bus.sendMessage(msg, address);
    }

    public void sendEmailHTML(String text) throws MessagingException {
        msg.setDataHandler(new DataHandler(new HTMLDataSource(text)));
        msg.saveChanges();
        bus.sendMessage(msg, address);
    }

    static class HTMLDataSource implements DataSource {
        private String html;

        public HTMLDataSource(String htmlString) {
            html = htmlString;
        }

        public InputStream getInputStream() throws IOException {
            if (html == null) throw new IOException("Null HTML");
            return new ByteArrayInputStream(html.getBytes());
        }

        public OutputStream getOutputStream() throws IOException {
            throw new IOException("This DataHandler cannot write HTML");
        }

        public String getContentType() {
            return "text/html";
        }

        public String getName() {
            return "JAF text/html dataSource to send e-mail only";
        }
    }
}
