package me.espryth.cosmeticwater.potion.watcher;

/**
 * Represents a watcher for a potion
 * who has a methods to watch, tick and check if is finished.
 */
public interface PotionWatcher {

  /**
   * Called when the watcher starts to watch.
   */
  void onWatch();

  /**
   * Called every second.
   */
  void onTick();

  /**
   * @return true if the watcher is finished.
   */
  boolean isFinished();

}
