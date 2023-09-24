package me.espryth.cosmeticwater.cauldron.command;

import me.espryth.cosmeticwater.cauldron.Cauldron;
import me.espryth.cosmeticwater.potion.Potion;
import me.espryth.cosmeticwater.potion.Potions;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class CauldronCommand implements CommandExecutor {

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String[] args
    ) {

        if (!(sender instanceof Player player)) {
            sender.sendPlainMessage("Only players can execute this command!");
            return true;
        }

        if (args.length < 1) {
            player.sendPlainMessage("Usage: /cauldron <potion>");
            return true;
        }

        final var potionName = args[0];
        final var potion = Potions.getByName(potionName);

        if (potion == null) {
            player.sendPlainMessage("Potion " + potionName + " not found! potions: flora or fungal");
            return true;
        }

        final var cauldronItem = createCauldronItem(potion);
        player.getInventory().addItem(cauldronItem);
        return true;
    }

    private ItemStack createCauldronItem(
            final @NotNull Potion potion
    ) {

        final var item = new ItemStack(Material.CAULDRON);

        item.editMeta(meta -> {
            meta.displayName(
                    Component.text("Cauldron of " + potion.identifier())
            );

            meta.getPersistentDataContainer().set(
                    Cauldron.KEY,
                    PersistentDataType.STRING,
                    potion.identifier()
            );
        });

        return item;
    }
}
