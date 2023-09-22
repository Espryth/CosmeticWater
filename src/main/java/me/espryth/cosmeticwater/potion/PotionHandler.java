package me.espryth.cosmeticwater.potion;

import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class PotionHandler {

  public void consume(
      final @NotNull Player player,
      final @NotNull Potion potion
  ) {
    player.getPersistentDataContainer().set(
        Potion.KEY,
        PersistentDataType.STRING,
        potion.identifier()
    );



  }
}
