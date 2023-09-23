package me.espryth.cosmeticwater.cauldron;

import me.espryth.cosmeticwater.potion.Potion;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public record Cauldron(
    @NotNull Block block,
    @NotNull Potion potion
) {}
