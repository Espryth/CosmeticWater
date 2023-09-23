package me.espryth.cosmeticwater.potion.watcher;

public interface PotionWatcher {

  void onWatch();

  void onTick();

  boolean isFinished();

}
