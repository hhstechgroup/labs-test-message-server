package tests;

import com.engagepoint.university.messaging.dao.repository.UserDAO;
import com.engagepoint.university.messaging.dao.repository.impl.UserDAOImpl;
import com.engagepoint.university.messaging.dto.UserDTO;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class UserServiceTest {

//    @Autowired
//    changed because it was an error that there is no beans for userDao
    private UserDAO userDAO = new UserDAOImpl();

    @Test
    @DatabaseSetup("sampleData.xml")
    public void testSave() throws Exception {
        /*
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Kate");
        userDTO.setPassword("12345");
        userDTO.setEmail("kate@gmail.com");
        userDTO.setPhoneNumber("sdf");

        userDAO.save(userDTO);
        */

        List<UserDTO> userList = userDAO.getAll();
        for(UserDTO userTemp: userList)
            System.out.println(userTemp);

        assertEquals(1, userList.size());
        assertEquals("jonny", userList.get(0).getName());
    }
}
