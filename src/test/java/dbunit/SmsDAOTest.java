package dbunit;

import com.engagepoint.university.messaging.dao.repository.SmsDAO;
import com.engagepoint.university.messaging.dto.SmsDTO;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SmsDAOTest extends DBUnitContextInit {

    @Autowired
    private SmsDAO smsDAO;

    @Test
    @DatabaseSetup("sampleData.xml")
    public void testSave() throws Exception {

        List<SmsDTO> smsList = smsDAO.getAll();
        for(SmsDTO smsTemp: smsList)
            System.out.println(smsTemp);

        assertEquals(1, smsList.size());
        assertEquals("Mona", smsList.get(0).getSender());
    }
}
