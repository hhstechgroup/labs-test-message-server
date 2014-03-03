package dbunit;

import com.engagepoint.university.messaging.dao.repository.EmailDAO;
import com.engagepoint.university.messaging.dto.AttachmentDTO;
import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.entities.Email;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RelationTest extends DBUnitContextInit{

    @Autowired
    EmailDAO emailDAO;

    @Test
    public void testManyToManyAnnotation() {

        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setSender("engagepoint-sender-email");
        emailDTO.setSubject("engagepoint-sender-subject");
        emailDTO.setBody("engagepoint-sender-body");
        emailDTO.setSendDate(new Date());
        emailDTO.setDeliveryDate(new Date());

        AttachmentDTO attachmentDTO = new AttachmentDTO();
        attachmentDTO.setName("engage-point-attachment");
        attachmentDTO.setContent("engage-point-attachment-content");

        AttachmentDTO attachmentDTO1 = new AttachmentDTO();
        attachmentDTO1.setName("engage-point-attachment-1");
        attachmentDTO1.setContent("engage-point-attachment-content-1");

        Collection<AttachmentDTO> attachmentCollection = new HashSet<AttachmentDTO>();

        attachmentCollection.add(attachmentDTO);
        attachmentCollection.add(attachmentDTO1);

        emailDTO.setAttachmentCollection(attachmentCollection);

        emailDAO.save(emailDTO);

        List<EmailDTO> emailList = emailDAO.getAll();
        assertEquals(1, emailList.size());
        assertEquals("engagepoint-sender-email", emailList.get(0).getSender());
    }

}
