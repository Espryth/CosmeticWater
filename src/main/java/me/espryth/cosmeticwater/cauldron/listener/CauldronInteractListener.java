package me.espryth.cosmeticwater.cauldron.listener;

import me.espryth.cosmeticwater.cauldron.CauldronManager;
import org.bukkit.Material;
import org.bukkit.block.data.Levelled;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CauldronInteractListener
    implements Listener {

  private final CauldronManager cauldronManager;

  public CauldronInteractListener(
      final @NotNull CauldronManager cauldronManager
  ) {
    this.cauldronManager = cauldronManager;
  }

  @EventHandler
  public void onInteract(final PlayerInteractEvent event) {

    final var player = event.getPlayer();
    final var block = event.getClickedBlock();
    final var item = event.getItem();

    if (
        event.getAction() != Action.RIGHT_CLICK_BLOCK ||
        block == null || item == null || item.getType() != Material.GLASS_BOTTLE
    ) {
      return;
    }

    final var cauldron = cauldronManager.asCauldron(block);

    // block is not a custom cauldron!
    if (cauldron == null) {
      return;
    }

    final var blockData = (Levelled) block.getBlockData();

    // cauldron is empty!
    if (blockData.getLevel() == blockData.getMinimumLevel()) {
      return;
    }

    player.getInventory().setItem(
        Objects.requireNonNull(event.getHand()),
        cauldron.potion().asItemStack()
    );
  }
}
