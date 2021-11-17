package edu.bbte.idde.zdim1981.backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class ConfigFactory {
    private static Config config;
    public static final Logger LOG = LoggerFactory.getLogger(ConfigFactory.class);

    public static synchronized Config getConfig() {
        if (config == null) {
            InputStream input = Config.class.getResourceAsStream(getConfigFileName());
            try {
                config = new ObjectMapper(new YAMLFactory()).readValue(input,Config.class);
                LOG.info(config.toString());
            } catch (IOException e) {
                config = new Config();
                config.setDaoType("mem");
                LOG.error("Error yaml");
            }
        }
        return config;
    }

    private static String getConfigFileName() {
        final StringBuilder sb = new StringBuilder("/application");
        String profile = System.getenv("PROFILE");
        if (profile != null && !profile.isEmpty()) {
            LOG.info("Detected profile {}", profile);
            sb.append('-').append(profile);
        }
        LOG.info("Detected profile {}", profile);
        return sb.append(".yaml").toString();
    }
}
