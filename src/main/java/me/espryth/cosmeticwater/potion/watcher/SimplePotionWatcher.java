package me.espryth.cosmeticwater.potion.watcher;

import me.espryth.cosmeticwater.potion.Potion;
import me.espryth.cosmeticwater.potion.PotionExecutor;
import org.bukkit.entity.Player;

public class SimplePotionWatcher implements PotionWatcher {

    private final Player watcher;
    private final Potion potion;

    public SimplePotionWatcher(Player watcher, Potion potion) {
        this.watcher = watcher;
        this.potion = potion;
    }

    @Override
    public void onWatch() {
        for (final var effect : potion.effects()) {
            PotionExecutor.REAL.execute(
                    watcher,
                    effect.createEffect(
                            potion.duration(),
                            0
                    )
            );
        }
    }

    @Override
    public void onTick() {
        // do nothing
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
