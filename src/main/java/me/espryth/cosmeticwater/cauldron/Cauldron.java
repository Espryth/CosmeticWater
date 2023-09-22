package me.espryth.cosmeticwater.cauldron;

import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.block.Block;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record Cauldron(
    @NotNull Block block,
    @NotNull PotionProperties potionProperties
) {

  public record PotionProperties(
      @NotNull Component name,
      @NotNull List<Component> lore,
      @NotNull Color color,
      @NotNull List<PotionEffect> effects
  ) {}
}
