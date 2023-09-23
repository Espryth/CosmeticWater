package me.espryth.cosmeticwater.cauldron;

import com.google.common.collect.Maps;
import me.espryth.cosmeticwater.potion.Potion;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Levelled;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class CauldronManager {

  private final Map<Location, Cauldron> cauldrons = Maps.newHashMap();

  private void loadCualdrons() {

  }

  public @Nullable Cauldron asCauldron(final @NotNull Block block) {
    return cauldrons.get(block.getLocation());
  }

  public void createCauldron(
      final @NotNull Block block,
      final @NotNull Potion potion
  ) {
    block.setType(
        Material.CAULDRON
    );
    final var blockData = (Levelled) block.getBlockData();
    blockData.setLevel(blockData.getMaximumLevel());
    cauldrons.put(block.getLocation(), new Cauldron(block, potion));
  }

}
