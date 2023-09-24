package me.espryth.cosmeticwater.potion;

import me.espryth.cosmeticwater.potion.watcher.NearBlockPotionWatcher;
import me.espryth.cosmeticwater.potion.watcher.SimplePotionWatcher;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class Potions {

  public static final Potion FLORA = new Potion(
      "flora",
      LegacyComponentSerializer.legacyAmpersand().deserialize("<pink>Flora Potion"),
      Collections.emptyList(),
      Color.FUCHSIA,
      List.of(
          PotionEffectType.SPEED
      ),
      1000,
      (player, potion) -> new NearBlockPotionWatcher(
          Set.of(
              Material.ROSE_BUSH
          ),
          player,
          potion
      )
  );

  public static final Potion FUNGAL = new Potion(
      "fungal",
      MiniMessage.miniMessage().deserialize("<green>Fungal Potion"),
      Collections.emptyList(),
      Color.GREEN,
      List.of(
         PotionEffectType.SLOW,
          PotionEffectType.JUMP
      ),
      1000,
          SimplePotionWatcher::new
  );

  public static Potion getByName(String name) {
    return switch (name) {
      case "flora" -> FLORA;
      case "fungal" -> FUNGAL;
      default -> null;
    };
  }

}
