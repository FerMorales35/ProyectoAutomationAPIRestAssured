import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

import static org.apache.logging.log4j.core.util.Loader.getClassLoader;

public class ApplicationProperties {
    private static Properties instance = null;

    private static final String APPLICATION_PREFIX = "application";
    private static final String APPLICATION_SUFFIX = "properties";
    private static final Logger logger = LogManager.getLogger(ApplicationProperties.class);

    public static synchronized Properties getInstance() {
        if (instance == null) {
            instance = loadPropertiesFile();
        }
        return instance;
    }

    private ApplicationProperties(){
    }

    private static Properties loadPropertiesFile() {

        String environment = Optional.ofNullable(System.getenv("env"))
                .orElse("prod");

        String fileName = String.format("%s-%s.%s", APPLICATION_PREFIX, environment, APPLICATION_SUFFIX);

        logger.info("Property file to read {}", fileName);

        Properties properties = new Properties();

        try {
            properties.load(getClassLoader().getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Unable to load the file {}", fileName);
        }

        return properties;
    }

}
