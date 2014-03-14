package dbunit;

import com.engagepoint.university.messaging.dto.SmsDTO;
import com.engagepoint.university.messaging.service.repository.SmsService;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SmsDAOTest extends DBUnitContextInit{

    @Autowired
    private SmsService smsService;

    @Test
    @DatabaseSetup("insertDataSmsDAO.xml")
    public void testSave() throws Exception {

        List<SmsDTO> smsList = smsService.getAll();
        for(SmsDTO smsTemp: smsList)
            System.out.println(smsTemp);

        assertEquals(1, smsList.size());
        assertEquals("Mona", smsList.get(0).getSender());
    }
}
