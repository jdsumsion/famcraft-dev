package org.familysearch.famcraft;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class for famcraft plugin.
 *
 * @author John Sumsion
 */
public class FamCraft extends JavaPlugin implements Listener {

  @Override
  public void onEnable() {
    Bukkit.getPluginManager().registerEvents(this, this);
  }

  @Override
  public void onDisable() {
  }

  @EventHandler
  public void onChat(AsyncPlayerChatEvent e) {
    Bukkit.getScheduler().runTask(this, () -> {
      e.getPlayer().sendMessage("Back atcha!");
    });
  }
}
