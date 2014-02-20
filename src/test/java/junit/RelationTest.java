package junit;

import com.engagepoint.university.messaging.dao.specific.EmailDAO;
import com.engagepoint.university.messaging.dao.specific.impl.EmailDAOImpl;
import com.engagepoint.university.messaging.dto.AttachmentDTO;
import com.engagepoint.university.messaging.dto.EmailDTO;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class RelationTest {
    private static final Logger LOG = LoggerFactory.getLogger(RelationTest.class);

    @Inject
    EmailDAO emailDAOImpl;

    @Before
    public void setUp() {
        emailDAOImpl = new EmailDAOImpl();
    }

    @Test
    public void shouldManyToManyAnnotationWork() {

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

        emailDAOImpl.save(emailDTO);

    }

}
