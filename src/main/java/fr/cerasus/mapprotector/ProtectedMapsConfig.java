package fr.cerasus.mapprotector;

import org.bukkit.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProtectedMapsConfig extends MapProtectorConfig {
    public static final String WORLDS_NAME_PATH = "worlds";

    private List<String> worldsName = new ArrayList<>();

    public ProtectedMapsConfig() {
        super("protected", "");
    }

    public void protectMap(World world) {
        if (isProtected(world)) return;

        worldsName.add(world.getName());
        config.set(WORLDS_NAME_PATH, worldsName);
        save();
    }

    public void unprotectMap(World world) {
        worldsName.removeIf(word -> word.equals(world.getName()));
        config.set(WORLDS_NAME_PATH, worldsName);
        save();
    }

    public List<String> getProtectedWorldsName() {
        List<String> configValue = config.getStringList(WORLDS_NAME_PATH);
        if (configValue != null) worldsName = configValue;
        return worldsName;
    }

    public boolean isProtected(World world) {
        return getProtectedWorldsName().contains(world.getName());
    }
}
