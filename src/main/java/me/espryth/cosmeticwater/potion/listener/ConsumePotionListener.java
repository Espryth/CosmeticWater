package me.espryth.cosmeticwater.potion.listener;

import me.espryth.cosmeticwater.potion.Potion;
import me.espryth.cosmeticwater.potion.PotionHandler;
import me.espryth.cosmeticwater.potion.Potions;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.persistence.PersistentDataType;

public class ConsumePotionListener
    implements Listener {

  private final PotionHandler potionHandler;

  public ConsumePotionListener(final PotionHandler potionHandler) {
    this.potionHandler = potionHandler;
  }

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
      potionHandler.consume(
          player,
          Potions.getByName(potionIdentifier)
      );
    }
  }
}
