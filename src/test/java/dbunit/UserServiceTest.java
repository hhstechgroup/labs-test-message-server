package dbunit;

import static org.junit.Assert.assertEquals;

import java.util.List;
import com.engagepoint.university.messaging.dao.repository.UserDAO;
import com.engagepoint.university.messaging.dto.UserDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.springtestdbunit.annotation.DatabaseSetup;

public class UserServiceTest extends DBUnitContextInit{

    @Autowired
    private UserDAO userDAO;

    @Test
    @DatabaseSetup("sampleData.xml")
    public void testSave() throws Exception {

        List<UserDTO> userList = userDAO.getAll();
        for(UserDTO userTemp: userList)
            System.out.println(userTemp);

        assertEquals(1, userList.size());
        assertEquals("jonny", userList.get(0).getName());
    }

}
