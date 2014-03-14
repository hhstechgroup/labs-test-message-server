package dbunit;


import com.engagepoint.university.messaging.dao.repository.AttachmentDAO;
import com.engagepoint.university.messaging.dto.AttachmentDTO;
import com.engagepoint.university.messaging.service.repository.AttachmentService;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AttacmentDAOTest extends DBUnitContextInit{

    @Autowired
    private AttachmentService attachmentService;
//    @Autowired
//    private AttachmentDAO attachmentDAO;

    @Test
    @DatabaseSetup("insertDataAttachmentDAO.xml")
    public void testSave() throws Exception {

        List<AttachmentDTO> attachmentList = attachmentService.getAll();
        assertEquals(1, attachmentList.size());
        assertEquals("Mona", attachmentList.get(0).getName());
    }
}
