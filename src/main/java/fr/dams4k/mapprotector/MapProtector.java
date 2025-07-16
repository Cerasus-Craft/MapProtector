package fr.dams4k.mapprotector;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class MapProtector extends JavaPlugin {
    public static MapProtector INSTANCE;

    public static ProtectedMapsConfig PROTECTED_CONFIG;

    @Override
    public void onEnable() {
        INSTANCE = this;
        PROTECTED_CONFIG = new ProtectedMapsConfig();

        ConsoleCommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage("[MapProtector] Plugin enabled");

        this.getCommand("mapprotector").setExecutor(new MapProtectorCommand());

        this.getServer().getPluginManager().registerEvents(new MapProtectorListener(), this);
    }
}
