package me.espryth.cosmeticwater.cauldron;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CauldronManager {

  public @Nullable Cauldron asCauldron(final @NotNull Block block) {

  }

  public @NotNull ItemStack createPotion(final @NotNull Cauldron cauldron) {

    final var potionProperties = cauldron.potionProperties();

    final var itemStack = new ItemStack(Material.POTION);
    final var itemMeta = (PotionMeta) itemStack.getItemMeta();

    itemMeta.displayName(potionProperties.name());
    itemMeta.lore(potionProperties.lore());
    itemMeta.setColor(potionProperties.color());

    for (final var effect : potionProperties.effects()) {
      itemMeta.addCustomEffect(effect, true);
    }

    itemStack.setItemMeta(itemMeta);
    return itemStack;
  }

}
