import com.sforce.ws.ConnectorConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

/**
 * Created by vanrysss on 12/29/14.
 */
public class testService {
    static Properties prop = new Properties();
    static EnterpriseConnection connection;
    private static ConnectorConfig enterpriseConfig = new ConnectorConfig();

    @Before
    public void setUp() throws Exception{
        prop = Service.setupProperties(prop);
        connection = Service.setupConnection(connection,enterpriseConfig,prop);

    }

    @Test
    public void runTests(){
        try{
            //tests
        }catch (Exception ce){
            System.out.println(ce.toString());
        }
    }

    @After
    public void tearDown() throws Exception{}
}
