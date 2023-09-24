package me.espryth.cosmeticwater.cauldron.listener;

import me.espryth.cosmeticwater.cauldron.CauldronManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

public class CauldronBreakListener implements Listener {

    private final CauldronManager cauldronManager;

    public CauldronBreakListener(
            final @NotNull CauldronManager cauldronManager
    ) {
        this.cauldronManager = cauldronManager;
    }

    @EventHandler(ignoreCancelled = true)
    public void onBreakBlock(final BlockBreakEvent event) {
        final var block = event.getBlock();
        final var cauldron = cauldronManager.asCauldron(block);
        if (cauldron == null) {
            return;
        }
        cauldronManager.breakCauldron(block);
    }
}
