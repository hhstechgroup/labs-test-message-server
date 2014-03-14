package dbunit;

import com.engagepoint.university.messaging.service.repository.EmailService;
import com.engagepoint.university.messaging.dto.EmailDTO;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EmailDAOTest extends DBUnitContextInit{
    @Autowired
    private EmailService emailService;

    @Test
    @DatabaseSetup("insertDataEmailDAO.xml")
    @DatabaseTearDown(value="insertDataEmailDAO.xml", type=DatabaseOperation.DELETE_ALL)
    public void testSave() throws Exception {

        List<EmailDTO> emailList = emailService.getAll();
        assertEquals(4, emailList.size());
        assertEquals("Mona", emailList.get(0).getSender());
    }

    @Test
    @DatabaseSetup("insertDataEmailDAO.xml")
    @DatabaseTearDown(value="insertDataEmailDAO.xml", type=DatabaseOperation.DELETE_ALL)
    public void testDeleteList() throws Exception {
        List<EmailDTO> emailList = emailService.getAll();
        assertEquals(4, emailList.size());
        List<Long> deleteList = Arrays.asList(1L, 2L, 4L);
        emailService.deleteIdList(deleteList);
        emailList = emailService.getAll();
        for (EmailDTO temp: emailList)
            System.out.println(temp);
        assertEquals(1, emailList.size());
        assertEquals("Slava Ukraine", emailList.get(0).getBody());
    }


}
