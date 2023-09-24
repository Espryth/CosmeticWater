package me.espryth.cosmeticwater.cauldron;

import com.google.common.collect.Maps;
import me.espryth.cosmeticwater.potion.Potion;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Levelled;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CauldronManager {

  private static final String FILE_NAME = "cauldrons.yml";

  private final Map<Location, Cauldron> cauldrons = Maps.newHashMap();
  private final File file;
  private final YamlConfiguration configuration;
  private final Logger logger;

  public CauldronManager(
          final @NotNull Plugin plugin,
          final @NotNull Logger logger
  ) {
    this.logger = logger;

    ConfigurationSerialization.registerClass(Cauldron.class);

    file = new File(plugin.getDataFolder(), "cauldrons.yml");

    if (!file.exists()) {
      try {
        try {
          Files.copy(
                  getClass().getClassLoader().getResourceAsStream(FILE_NAME),
                  file.toPath()
          );
        } catch (IOException exception) {
          logger.error("Failed to copy {}!", FILE_NAME, exception);
          file.createNewFile();
        }
      } catch (IOException exception) {
        logger.error("Failed to create {}!", FILE_NAME, exception);
      }
    }

    try {
      configuration = YamlConfiguration.loadConfiguration(
              Files.newBufferedReader(file.toPath())
      );
    } catch (IOException exception) {
      logger.error("Failed to load {}!", FILE_NAME, exception);
      throw new RuntimeException(exception);
    }

    loadCauldrons();
  }

  private void loadCauldrons() {
    for (final var object : configuration.getList("cauldrons", List.of())) {
      final var cauldron = (Cauldron) object;
      cauldrons.put(
          cauldron.block().getLocation(),
          cauldron
      );
    }
  }

  public @Nullable Cauldron asCauldron(final @NotNull Block block) {
    return cauldrons.get(block.getLocation());
  }

  public void placeCauldron(
      final @NotNull Block block,
      final @NotNull Potion potion
  ) {
    block.setType(
        Material.CAULDRON
    );
    final var blockData = (Levelled) block.getBlockData();
    blockData.setLevel(blockData.getMaximumLevel());
    cauldrons.put(block.getLocation(), new Cauldron(block, potion));
    save();
  }

  public void breakCauldron(final @NotNull Block block) {
    final var cauldron = cauldrons.remove(block.getLocation());

    if (cauldron != null) {
      save();
    }
  }

  private void save() {
    try {
      configuration.set("cauldrons", new ArrayList<>(
                cauldrons.values()
      ));
      configuration.save(file);
    } catch (IOException exception) {
        logger.error("Failed to save {}!", FILE_NAME, exception);
    }
  }

}
