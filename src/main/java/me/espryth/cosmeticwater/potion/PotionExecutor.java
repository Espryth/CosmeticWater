package me.espryth.cosmeticwater.potion;

import me.espryth.cosmeticwater.packet.PacketSender;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

/**
 * This enum is used to execute the potion effect.
 * <p>
 * There are two executors:
 * <p>
 * - FAKE: This executor will send a packet to the player
 * with the potion effect, but it won't apply it.
 * <p>
 * - REAL: This executor will apply the potion effect to the player
*/
@SuppressWarnings({
        "ConstantConditions",
        "deprecation"
})
public enum PotionExecutor {

  /**
   * This is a fake executor, it will send a packet to the player
   * with the potion effect, but it won't apply it.
   */
  FAKE(
      (player, effect) -> {
        PacketSender.sendPacket(
            player,
            new ClientboundUpdateMobEffectPacket(
                player.getEntityId(),
                new MobEffectInstance(
                    MobEffect.byId(effect.getType().getId()),
                    effect.getDuration(),
                    effect.getAmplifier()
                )
            )
        );
      }
  ),

  /**
   * This executor will apply the potion effect to the player
   */
  REAL(LivingEntity::addPotionEffect);

  private final BiConsumer<Player, PotionEffect> execute;

  PotionExecutor(final BiConsumer<Player, PotionEffect> execute) {
    this.execute = execute;
  }

  public void execute(final @NotNull Player player, final @NotNull PotionEffect effect) {
    execute.accept(player, effect);
  }
}
