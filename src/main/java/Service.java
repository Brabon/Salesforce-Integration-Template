/**
 * Created by vanrysss on 12/29/14.
 */

//import your Enterprise JAR here
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Service {

    static EnterpriseConnection connection;
    private static ConnectorConfig enterpriseConfig = new ConnectorConfig();
    static Properties prop = new Properties();
    static final Logger logger = LogManager.(Service.class.getName());

    public static void main(String[] args) {
        logger.debug("Starting Replacement Service");
        try {
            prop = setupProperties(prop);
            connection = setupConnection(connection, enterpriseConfig, prop);
        } catch (ConnectionException ce) {
            logger.error(ce.toString());
            System.exit(-1);
        }


    }
    public static EnterpriseConnection setupConnection(EnterpriseConnection connection, ConnectorConfig conConfig, Properties properties)
            throws ConnectionException {
        conConfig.setPassword(properties.getProperty("PASSWORD"));
        conConfig.setUsername(properties.getProperty("USERNAME"));
        conConfig.setAuthEndpoint(properties.getProperty("ENDPOINT"));
        logger.info("creating SF connection");

        // If we're behind a proxy creating a connection might not work the first "x" times.
        // We'll retry for 25 seconds, then quit
        connection = new EnterpriseConnection(conConfig);
        return connection;
    }

    public static Properties setupProperties(Properties properties) {

        try {
            properties.load(new FileInputStream("config.properties"));
        } catch (IOException ioe) {
            logger.error("Unable to load config file " + ioe.getMessage());
            System.exit(-1);
        } catch (NullPointerException npe) {
            logger.error(" Null Pointer Error while setting up properties: " + npe.toString() + npe.getMessage());
            // System.exit(-1);
        }
        return properties;
    }
}
