package me.espryth.cosmeticwater.potion.watcher;

import me.espryth.cosmeticwater.packet.PacketSender;
import me.espryth.cosmeticwater.potion.Potion;
import me.espryth.cosmeticwater.potion.PotionExecutor;
import net.minecraft.network.protocol.game.ClientboundRemoveMobEffectPacket;
import net.minecraft.world.effect.MobEffect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Set;

public class NearBlockWatcher
    implements PotionWatcher {

  private static final int RADIUS = 5;

  private final Set<Material> nearBlocks;
  private final Player watcher;
  private final Potion potion;
  private int currentSeconds;
  private boolean finished;

  public NearBlockWatcher(
      final Set<Material> nearBlocks,
      final Player watcher,
      final Potion potion
  ) {
    this.nearBlocks = nearBlocks;
    this.watcher = watcher;
    this.potion = potion;
  }


  @Override
  public void onWatch() {
    for (final var effect : potion.effects()) {
      PotionExecutor.FAKE.execute(
          watcher, createEffect(effect)
      );
    }
  }

  @Override
  public void onTick() {
    if((currentSeconds * 20) == potion.duration()) {
      watcher.getPersistentDataContainer()
          .remove(Potion.KEY);

      for (final var effect : potion.effects()) {
        watcher.removePotionEffect(effect);

        PacketSender.sendPacket(
            watcher,
            new ClientboundRemoveMobEffectPacket(
                watcher.getEntityId(),
                MobEffect.byId(effect.getId())
            )
        );
      }

      finished = true;
      return;
    }

    if (hasNearBlock(RADIUS)) {
      for (final var effect : potion.effects()) {
        if (watcher.hasPotionEffect(effect)) {
          continue;
        }
        watcher.addPotionEffect(
            createEffect(effect)
        );
      }
    } else {
      for (final var effect : potion.effects()) {
        if (watcher.hasPotionEffect(effect)) {
          watcher.removePotionEffect(effect);
          PotionExecutor.FAKE.execute(
              watcher, createEffect(effect)
          );
        }
      }
    }

    currentSeconds++;
  }

  private PotionEffect createEffect(
      final PotionEffectType effect
  ) {
    return effect.createEffect(potion.duration() - (currentSeconds * 20), 1);
  }

  private boolean hasNearBlock(final int radius) {
    for (int x = -radius; x <= radius; x++) {
      for (int y = -radius; y <= radius; y++) {
        for (int z = -radius; z <= radius; z++) {
          final var block = watcher.getLocation()
              .getBlock()
              .getRelative(x, y, z);
          if (nearBlocks.contains(block.getType())) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public boolean isFinished() {
    return finished;
  }
}
