package pt.mleiria;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public enum ConfigFileReader {

    INSTANCE;

    private Properties props;

    private static final Logger LOG = Logger.getLogger(ConfigFileReader.class.getName());

    ConfigFileReader() {
        final String configDir = "/home/manuel/Elements/homeWebManager/conf";
        final File file = new File(configDir, "config.properties");

        props = new Properties();
        try (final InputStream configFile = new FileInputStream(file)) {
            props.load(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getImageDBFolder(){
        final String resource = props.getProperty("image.db.root");
        LOG.info("{img.db.root : " + resource + "}");
        return resource;
    }

    public String getCluedoImageDBFolder(){
        final String resource = props.getProperty("image.db.cluedo");
        LOG.info("{image.db.cluedo : " + resource + "}");
        return resource;
    }

    public String getImageDBFolderToUpdate() {
        final String resource = props.getProperty("image.db.folders.to.update");
        LOG.info("{image.db.folders.to.update : " + resource + "}");
        return resource;
    }
}
