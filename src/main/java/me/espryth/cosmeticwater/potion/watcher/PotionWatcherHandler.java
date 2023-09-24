package me.espryth.cosmeticwater.potion.watcher;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * The handler of the potion watcher
 * able to start to watch a potion watcher.
 */
public class PotionWatcherHandler {

  private final Plugin plugin;

  public PotionWatcherHandler(
      final @NotNull Plugin plugin
  ) {
    this.plugin = plugin;
  }

  /**
   * Starts to watch a potion watcher.
   *
   * @param watcher the watcher to watch.
   */
  public void watch(
      final PotionWatcher watcher
  ) {
    watcher.onWatch();
    Bukkit.getScheduler()
        .runTaskTimer(plugin, task -> {
          watcher.onTick();
          if (watcher.isFinished()) {
            task.cancel();
          }
        }, 0, 20);
  }

}
