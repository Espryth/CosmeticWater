package me.espryth.cosmeticwater.potion.event;

import me.espryth.cosmeticwater.potion.Potion;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class PotionConsumeEvent extends PlayerEvent {

  private final Potion potion;

  public PotionConsumeEvent(@NotNull Player who, Potion potion) {
    super(who);
    this.potion = potion;
  }

  @Override
  public @NotNull HandlerList getHandlers() {
    return null;
  }
}
