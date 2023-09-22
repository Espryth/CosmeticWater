package me.espryth.cosmeticwater.potion;

import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record Potion(
    @NotNull String identifier,
    @NotNull Component displayItem,
    @NotNull List<Component> lore,
    @NotNull Color color,
    @NotNull List<PotionEffect> effects,
    @NotNull PotionExecutor executor
) {

  public static final NamespacedKey KEY = new NamespacedKey("cosmetic-water", "potion");


  public ItemStack asItemStack() {
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

    return null;
  }

}
