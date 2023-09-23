package me.espryth.cosmeticwater;

import me.espryth.cosmeticwater.potion.PotionHandler;
import me.espryth.cosmeticwater.potion.Potions;
import me.espryth.cosmeticwater.potion.listener.ConsumePotionListener;
import me.espryth.cosmeticwater.potion.watcher.PotionWatcherHandler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CosmeticWaterPlugin extends JavaPlugin {

  @Override
  public void onEnable() {
    final var potionWatcherManager = new PotionWatcherHandler(this);
    final var potionHandler = new PotionHandler(potionWatcherManager);
    final var pluginManager = getServer().getPluginManager();
    pluginManager.registerEvents(new ConsumePotionListener(potionHandler), this);
    getCommand("potion").setExecutor(
        (sender, command, label, args) -> {

          final var player = (Player) sender;
          player.getInventory().addItem(Potions.FLORA.asItemStack());

          return true;
        }
    );
  }
}
