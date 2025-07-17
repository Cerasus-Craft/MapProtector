package fr.cerasus.mapprotector;

import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

public class ProtectedMapsConfig extends MapProtectorConfig {
    public static final String WORLDS_NAME_PATH = "worlds";

    private List<String> worldsName = new ArrayList<>();

    public ProtectedMapsConfig() {
        super("protected", "");
    }

    public void protectMap(World world) {
        String worldName = world.getName();
        if (worldsName.contains(worldName)) return;

        worldsName.add(worldName);
        config.set(WORLDS_NAME_PATH, worldsName);
        save();
    }

    public void unprotectMap(World world) {
        worldsName.removeIf(e -> e.equals(world.getName()));
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
