package fr.cerasus.mapprotector;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MapProtectorConfig {
    protected File configFile;
    protected FileConfiguration config;

    protected final String fileName;
    protected final String folderName;

    public MapProtectorConfig(String baseFileName, String folderName) {
        this.folderName = folderName;
        this.fileName = getFileName(baseFileName);

        configFile = new File(getFolder(folderName), this.fileName);
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
        }

        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void save() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFileName(String fileName) {
        return fileName + ".yml";
    }

    public static File getFolder(String folderName) {
        File dataFolder = MapProtector.INSTANCE.getDataFolder();
        File generatorsFolder = new File(dataFolder, folderName);

        return generatorsFolder;
    }
}
