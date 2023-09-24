package me.espryth.cosmeticwater;

import me.espryth.cosmeticwater.cauldron.CauldronManager;
import me.espryth.cosmeticwater.cauldron.command.CauldronCommand;
import me.espryth.cosmeticwater.cauldron.listener.CauldronBreakListener;
import me.espryth.cosmeticwater.cauldron.listener.CauldronInteractListener;
import me.espryth.cosmeticwater.cauldron.listener.CauldronPlaceListener;
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

    final var cauldronManager = new CauldronManager(this, getSLF4JLogger());

    pluginManager.registerEvents(new ConsumePotionListener(potionHandler), this);
    pluginManager.registerEvents(new CauldronBreakListener(cauldronManager), this);
    pluginManager.registerEvents(new CauldronPlaceListener(cauldronManager, getSLF4JLogger()), this);
    pluginManager.registerEvents(new CauldronInteractListener(cauldronManager), this);

    getCommand("cauldron").setExecutor(new CauldronCommand());
  }
}
