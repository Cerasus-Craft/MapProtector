package fr.dams4k.mapprotector;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class MapProtectorCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) return true;
        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;
        switch (args[0].toLowerCase(Locale.ROOT)) {
            case "on":
                MapProtector.PROTECTED_CONFIG.protectMap(player.getWorld());
                break;
            case "off":
                MapProtector.PROTECTED_CONFIG.unprotectMap(player.getWorld());
                break;
        }

        return true;
    }
}
