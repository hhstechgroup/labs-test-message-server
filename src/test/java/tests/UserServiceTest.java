package tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.engagepoint.university.messaging.dao.repository.UserDAO;
import com.engagepoint.university.messaging.dao.repository.impl.UserDAOImpl;
import com.engagepoint.university.messaging.dto.UserDTO;
import com.engagepoint.university.messaging.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class UserServiceTest {

    @Autowired
    private UserDAO userDAO;

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
