package dbunit.tests;

import com.engagepoint.university.messaging.dao.specific.EmailDAO;
import com.engagepoint.university.messaging.dao.specific.impl.EmailDAOImpl;
import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.util.EntityManagerUtil;
import dbunit.config.DBUnitConfig;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.List;

public class EmailTest extends DBUnitConfig{
    private EmailDAO service = new EmailDAOImpl();
    private EntityManager em;

    @BeforeClass
    public void init()throws Exception {

    }

    @Before
    public void setUp() throws Exception {
        EntityManagerUtil.getEntityManager();
        super.setUp();
        beforeData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("dbunit/email-data.xml"));
        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public EmailTest(String name) {
        super(name);
    }

    @Test
    public void testGetAll() throws Exception {
        List<EmailDTO> persons = service.getAll();

        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("dbunit/email-data.xml"));

        IDataSet actualData = tester.getConnection().createDataSet();
        String[] ignore = {"id"};
        Assertion.assertEqualsIgnoreCols(expectedData, actualData, "email", ignore);
        Assert.assertEquals(expectedData.getTable("email").getRowCount(), persons.size());
    }

    @Test
    public void testSave() throws Exception {
        EmailDTO email = new EmailDTO();
        email.setSender("Lilia");
        email.setSubject("Kril");
        email.setBody("hello");

        service.save(email);

        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("dbunit/email-data-save.xml"));

        IDataSet actualData = tester.getConnection().createDataSet();

        String[] ignore = {"id","delivery_date","send_date"};
        Assertion.assertEqualsIgnoreCols(expectedData, actualData, "email", ignore);
    }

    //others tests
}
