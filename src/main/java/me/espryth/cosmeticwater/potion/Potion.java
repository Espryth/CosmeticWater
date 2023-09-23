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
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public record Potion(
    @NotNull String identifier,
    @NotNull Component displayItem,
    @NotNull List<Component> lore,
    @NotNull Color color,
    @NotNull List<PotionEffectType> effects,
    int duration,
    @Nullable PotionExecutor executor,
    BiFunction<Player, Potion, PotionWatcher> watcherFactory
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

    itemMeta.displayName(displayItem());
    itemMeta.lore(lore());
    itemMeta.setColor(color());

    itemStack.setItemMeta(itemMeta);

    return itemStack;
  }

}
