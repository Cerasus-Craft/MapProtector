package fr.cerasus.mapprotector;

import fr.cerasus.cerasuscommands.*;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class MapProtectorCommand implements CCommand {
    public enum SubCommand implements SubCommandEnum {
        ON(ci -> protectWorld(ci.sender, ci.world)),
        OFF(ci -> unprotectWorld(ci.sender, ci.world));

        CommandCallback callback;

        SubCommand(CommandCallback callback) {
            this.callback = callback;
        }

        @Override
        public CommandCallback getCallback() {
            return callback;
        }
    };

    @Override
    public boolean emptyCallback(CallbackInfo ci) {
        String worldStatus = MapProtector.PROTECTED_CONFIG.isProtected(ci.world) ? "protected" : "unprotected";
        MapProtector.MESSENGER.sendWarningMessage(ci.sender, String.format("This world is currently %s", worldStatus));
        return true;
    }

    public static void protectWorld(CommandSender sender, World world) {
        if (MapProtector.PROTECTED_CONFIG.isProtected(world)) {
            MapProtector.MESSENGER.sendWarningMessage(sender, "This world is already protected");
            return;
        }

        MapProtector.PROTECTED_CONFIG.protectMap(world);
        MapProtector.MESSENGER.sendPositiveMessage(sender, "This world is now protected");
    }

    public static void unprotectWorld(CommandSender sender, World world) {
        if (!MapProtector.PROTECTED_CONFIG.isProtected(world)) {
            MapProtector.MESSENGER.sendWarningMessage(sender, "This world is already not protected");
            return;
        }

        MapProtector.PROTECTED_CONFIG.unprotectMap(world);
        MapProtector.MESSENGER.sendNegativeMessage(sender, "This world is no longer protected");
    }

    @Override
    public Class getClazz() {
        return SubCommand.class;
    }
}
