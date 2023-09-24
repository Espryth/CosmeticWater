package me.espryth.cosmeticwater.cauldron;

import me.espryth.cosmeticwater.potion.Potion;
import me.espryth.cosmeticwater.potion.Potions;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public record Cauldron(
    @NotNull Block block,
    @NotNull Potion potion
) implements ConfigurationSerializable {

    public static final NamespacedKey KEY = new NamespacedKey("cosmetic-water", "cauldron");

    public Cauldron(@NotNull Map<String, Object> map) {
        this(
                ((Location) map.get("block")).getBlock(),
                Potions.getByName((String) map.get("potion"))
        );
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        return Map.of(
                "block", block.getLocation(),
                "potion", potion.identifier()
        );
    }
}
