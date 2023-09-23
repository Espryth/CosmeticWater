package me.espryth.cosmeticwater.potion.watcher;

public class EmptyWatcher implements PotionWatcher {
  @Override
  public void onWatch() {
    // do nothing
  }

  @Override
  public void onTick() {
    // do nothing
  }

  @Override
  public boolean isFinished() {
    return true; // always finished
  }
}
