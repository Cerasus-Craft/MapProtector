package fr.cerasus.mapprotector;

import fr.cerasus.servermessenger.ServerMessenger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MapProtector extends JavaPlugin {
    public static MapProtector INSTANCE;

    public static ProtectedMapsConfig PROTECTED_CONFIG;

    public static ServerMessenger MESSENGER;

    @Override
    public void onEnable() {
        INSTANCE = this;
        PROTECTED_CONFIG = new ProtectedMapsConfig();
        MESSENGER = new ServerMessenger(this);

        MESSENGER.sendPositiveMessage(Bukkit.getConsoleSender(), "Plugin enabled");

        this.getCommand("mapprotector").setExecutor(new MapProtectorCommand());
        this.getServer().getPluginManager().registerEvents(new MapProtectorListener(), this);
    }
}
