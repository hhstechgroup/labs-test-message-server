package junit;

import com.engagepoint.university.messaging.dao.specific.AttachmentDAO;
import com.engagepoint.university.messaging.dao.specific.impl.AttachmentDAOImpl;
import com.engagepoint.university.messaging.dto.AttachmentDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;

public class AttachmentDAOTest {
    private static final Logger LOG = LoggerFactory.getLogger(EmailDAOTest.class);

    @Inject
    AttachmentDAO attachmentDAOimpl;

    @Before
    public void setUp() {
        attachmentDAOimpl = new AttachmentDAOImpl();
    }

    @Test
    public void shouldGetAllAttachments() {

        AttachmentDTO attachmentDTO = new AttachmentDTO();
        attachmentDTO.setName("engage-point-attachment");
        attachmentDTO.setContent("engage-point-attachment-content");

        AttachmentDTO attachmentDTO1 = new AttachmentDTO();
        attachmentDTO1.setName("engage-point-attachment-1");
        attachmentDTO1.setContent("engage-point-attachment-content-1");

        attachmentDAOimpl.save(attachmentDTO);
        attachmentDAOimpl.save(attachmentDTO1);
        Assert.assertEquals(2, attachmentDAOimpl.getAll().size());

    }
}
