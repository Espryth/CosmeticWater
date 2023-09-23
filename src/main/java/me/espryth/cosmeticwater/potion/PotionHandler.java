package me.espryth.cosmeticwater.potion;

import me.espryth.cosmeticwater.potion.watcher.PotionWatcherHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class PotionHandler {

  private final PotionWatcherHandler potionWatcherHandler;

  public PotionHandler(final @NotNull PotionWatcherHandler potionWatcherHandler) {
    this.potionWatcherHandler = potionWatcherHandler;
  }

  public void consume(
      final @NotNull Player player,
      final @NotNull Potion potion
  ) {
    player.getPersistentDataContainer().set(
        Potion.KEY,
        PersistentDataType.STRING,
        potion.identifier()
    );

    final var executor = potion.executor();

    if (executor != null) {
      for (final var effect : potion.effects()) {
        executor.execute(
            player, effect.createEffect(potion.duration(), 1)
        );
      }
    }

    potionWatcherHandler.watch(
        potion.watcherFactory().apply(player, potion)
    );
  }
}
