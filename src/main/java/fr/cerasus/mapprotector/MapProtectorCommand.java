package fr.cerasus.mapprotector;

import org.bukkit.World;
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
        World world = player.getWorld();
        switch (args[0].toLowerCase(Locale.ROOT)) {
            case "on":
                protectWorld(sender, world);
                break;
            case "off":
                unprotectWorld(sender, world);
                break;
        }

        return true;
    }

    public void protectWorld(CommandSender sender, World world) {
        if (MapProtector.PROTECTED_CONFIG.isProtected(world)) {
            MapProtector.MESSENGER.sendWarningMessage(sender, "This world is already protected");
            return;
        }

        MapProtector.PROTECTED_CONFIG.protectMap(world);
        MapProtector.MESSENGER.sendPositiveMessage(sender, "This world is now protected");
    }

    public void unprotectWorld(CommandSender sender, World world) {
        if (!MapProtector.PROTECTED_CONFIG.isProtected(world)) {
            MapProtector.MESSENGER.sendWarningMessage(sender, "This world is already not protected");
            return;
        }

        MapProtector.PROTECTED_CONFIG.unprotectMap(world);
        MapProtector.MESSENGER.sendNegativeMessage(sender, "This world is no longer protected");
    }
}
