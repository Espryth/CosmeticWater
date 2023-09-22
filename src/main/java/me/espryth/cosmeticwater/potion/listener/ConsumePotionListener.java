package me.espryth.cosmeticwater.potion.listener;

import me.espryth.cosmeticwater.potion.Potion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.persistence.PersistentDataType;

public class ConsumePotionListener
    implements Listener {

  @EventHandler
  public void handle(final PlayerItemConsumeEvent event) {

    final var player = event.getPlayer();
    final var item = event.getItem();

    final var meta = item.getItemMeta();

    if(meta == null) {
      return;
    }

    final var potionIdentifier = meta.getPersistentDataContainer().get(
        Potion.KEY,
        PersistentDataType.STRING
    );

    // consumed a custom potion
    if (potionIdentifier != null) {
      // set the potion identifier to the player
      player.getPersistentDataContainer().set(
          Potion.KEY,
          PersistentDataType.STRING,
          potionIdentifier
      );
    }
  }
}
