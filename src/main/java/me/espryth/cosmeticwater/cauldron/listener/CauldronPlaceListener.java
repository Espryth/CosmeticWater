package me.espryth.cosmeticwater.cauldron.listener;

import me.espryth.cosmeticwater.cauldron.Cauldron;
import me.espryth.cosmeticwater.cauldron.CauldronManager;
import me.espryth.cosmeticwater.potion.Potions;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

public class CauldronPlaceListener implements Listener {

    private final CauldronManager cauldronManager;
    private final Logger logger;

    public CauldronPlaceListener(
            final @NotNull CauldronManager cauldronManager,
            final @NotNull Logger logger
    ) {
        this.cauldronManager = cauldronManager;
        this.logger = logger;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlaceBlock(final BlockPlaceEvent event) {
        final var block = event.getBlock();
        final var item = event.getItemInHand();

        if (block.getType() != Material.CAULDRON) {
            return;
        }

        final var meta = item.getItemMeta();
        final var potionName = meta.getPersistentDataContainer()
                .get(Cauldron.KEY, PersistentDataType.STRING);

        if (potionName == null) {
            return;
        }

        final var potion = Potions.getByName(potionName);

        if (potion == null) {
            logger.warn("Potion {} not found!", potionName);
            return;
        }

        cauldronManager.placeCauldron(
                block,
                potion
        );

    }

}
