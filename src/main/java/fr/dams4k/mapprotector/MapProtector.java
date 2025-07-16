package fr.dams4k.mapprotector;

import fr.dams4k.servermessager.ServerMessager;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class MapProtector extends JavaPlugin {
    public static MapProtector INSTANCE;

    public static ProtectedMapsConfig PROTECTED_CONFIG;

    public static ServerMessager MESSAGER;

    @Override
    public void onEnable() {
        INSTANCE = this;
        PROTECTED_CONFIG = new ProtectedMapsConfig();
        MESSAGER = new ServerMessager(this);

        MESSAGER.sendPositiveMessage(Bukkit.getConsoleSender(), "Plugin enabled");

        this.getCommand("mapprotector").setExecutor(new MapProtectorCommand());
        this.getServer().getPluginManager().registerEvents(new MapProtectorListener(), this);
    }
}
