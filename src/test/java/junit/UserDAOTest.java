package junit;

import com.engagepoint.university.messaging.dao.specific.UserDAO;
import com.engagepoint.university.messaging.dao.specific.impl.UserDAOImpl;
import com.engagepoint.university.messaging.dto.UserDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;

public class UserDAOTest {
    private static final Logger LOG = LoggerFactory.getLogger(EmailDAOTest.class);

    @Inject
    UserDAO userDAOImpl;

    @Before
    public void setUp() {
        userDAOImpl = new UserDAOImpl();
    }

    @Test
    public void shouldGetAllUsers() {

        UserDTO userDTO = new UserDTO();
        userDTO.setName("engage-point-user-name");
        userDTO.setEmail("engage-point-user-email");
        userDTO.setPassword("passprase");
        userDTO.setPhoneNumber("274368723642");

        UserDTO userDTO1 = new UserDTO();
        userDTO1.setName("engage-point-user-name-1");
        userDTO1.setEmail("engage-point-user-email-1");
        userDTO1.setPassword("passprase-1");
        userDTO1.setPhoneNumber("274368723642-1");

        userDAOImpl.save(userDTO);
        userDAOImpl.save(userDTO1);
        Assert.assertEquals(2, userDAOImpl.getAll().size());

    }
}
