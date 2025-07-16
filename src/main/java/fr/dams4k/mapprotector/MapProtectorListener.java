package fr.dams4k.mapprotector;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.LazyMetadataValue;

public class MapProtectorListener implements Listener {
    public static final String MP_METADATA_KEY = "mpPlayer";

    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent event) {
        Block block = event.getBlock();
        World world = block.getWorld();
        if (!MapProtector.PROTECTED_CONFIG.isProtected(world)) return;

        block.setMetadata(MP_METADATA_KEY, new FixedMetadataValue(MapProtector.INSTANCE, event.getPlayer()));
    }

    @EventHandler
    public void onBlockBroke(BlockBreakEvent event) {
        Block block = event.getBlock();
        World world = event.getBlock().getWorld();
        if (!MapProtector.PROTECTED_CONFIG.isProtected(world)) return;

        event.setCancelled(!block.hasMetadata(MP_METADATA_KEY));
    }
}
