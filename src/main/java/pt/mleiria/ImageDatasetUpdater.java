package pt.mleiria;

import pt.mleiria.security.gdrive.DriveStart;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@Singleton
@Startup
public class ImageDatasetUpdater {
    private static final Logger LOG = Logger.getLogger(ImageDatasetUpdater.class.getName());

    @Timeout
    @Schedule(minute = "0", hour = "0", dayOfMonth = "*", month = "*", dayOfWeek = "*")
    public void updateDataset(){
        final String imageDB = ConfigFileReader.INSTANCE.getImageDBFolder();
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
