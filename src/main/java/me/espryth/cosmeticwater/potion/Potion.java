package me.espryth.cosmeticwater.potion;

import me.espryth.cosmeticwater.potion.watcher.PotionWatcher;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BiFunction;

/**
 * This class represents a potion.
 *
 * @param identifier The identifier of the potion.
 * @param displayName The display name of the potion item.
 * @param lore The lore of the potion item.
 * @param color The color of the potion item.
 * @param effects The effects of the potion.
 * @param duration The duration of the potion.
 * @param watcherFactory The factory of the potion watcher.
 */
public record Potion(
    @NotNull String identifier,
    @NotNull Component displayName,
    @NotNull List<Component> lore,
    @NotNull Color color,
    @NotNull List<PotionEffectType> effects,
    int duration,
    @NotNull BiFunction<Player, Potion, PotionWatcher> watcherFactory
) {

  public static final NamespacedKey KEY = new NamespacedKey("cosmetic-water", "potion");

  public @NotNull ItemStack asItemStack() {
    final var itemStack = new ItemStack(Material.POTION);
    final var itemMeta = (PotionMeta) itemStack.getItemMeta();

    itemMeta.getPersistentDataContainer()
            .set(
                KEY, PersistentDataType.STRING,
                identifier
            );

    itemMeta.displayName(displayName);
    itemMeta.lore(lore);
    itemMeta.setColor(color);

    itemStack.setItemMeta(itemMeta);

    return itemStack;
  }

}
