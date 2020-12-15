package pt.mleiria;

import pt.mleiria.security.gdrive.DriveStart;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@WebListener
public class HomeListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(HomeListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        final String imageDB = ConfigFileReader.INSTANCE.getImageDBFolder();
        new Thread(() -> updateDataset(imageDB)).start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private void updateDataset(final String imageDB) {
        final String[] folders = ConfigFileReader.INSTANCE.getImageDBFolderToUpdate().split(";");
        for(final String folder : folders) {
            final String completePath = ConfigFileReader.INSTANCE.getImageDBFolder() + folder;
            LOG.info("Updating Dataset: " + completePath);
            final Set<String> excludedFiles = DriveStart.getFilesFromFolder(completePath);
            boolean res = DriveStart.downloadFilesFromFolder(folder, imageDB + "/" + folder + "/", Optional.of(excludedFiles));
            LOG.info("Finish Updating Dataset: " + res);
        }
    }
}
