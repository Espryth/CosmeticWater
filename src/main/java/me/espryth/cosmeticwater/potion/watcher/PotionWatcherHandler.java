package me.espryth.cosmeticwater.potion.watcher;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class PotionWatcherHandler {

  private final Plugin plugin;

  public PotionWatcherHandler(
      final @NotNull Plugin plugin
  ) {
    this.plugin = plugin;
  }

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
