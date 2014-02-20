package junit;

import com.engagepoint.university.messaging.dao.specific.SmsDAO;
import com.engagepoint.university.messaging.dao.specific.impl.SmsDAOImpl;
import com.engagepoint.university.messaging.dto.SmsDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Date;

public class SmsDAOTest {
    private static final Logger LOG = LoggerFactory.getLogger(EmailDAOTest.class);

    @Inject
    SmsDAO smsDAOImpl;

    @Before
    public void setUp() {
        smsDAOImpl = new SmsDAOImpl();
    }

    @Test
    public void shouldGetAllSmses() {

        SmsDTO smsDTO = new SmsDTO();
        smsDTO.setSender("engage-point-sender");
        smsDTO.setBody("engage-point-body");
        smsDTO.setSendDate(new Date());
        smsDTO.setDeliveryDate(new Date());

        SmsDTO smsDTO1 = new SmsDTO();
        smsDTO1.setSender("engage-point-sender-1");
        smsDTO1.setBody("engage-point-body-1");
        smsDTO1.setSendDate(new Date());
        smsDTO1.setDeliveryDate(new Date());


        smsDAOImpl.save(smsDTO);
        smsDAOImpl.save(smsDTO);
        Assert.assertEquals(2, smsDAOImpl.getAll().size());

    }
}
