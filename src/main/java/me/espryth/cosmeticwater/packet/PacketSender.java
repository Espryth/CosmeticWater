package me.espryth.cosmeticwater.packet;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PacketSender {

  public static void sendPacket(
      final @NotNull Player player,
      final @NotNull Packet<ClientGamePacketListener> packet
  ) {
    ((CraftPlayer) player)
        .getHandle()
        .connection
        .connection
        .send(packet);
  }
}
