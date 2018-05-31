package org.familysearch.famcraft;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

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

  long lastWarningMillis = System.currentTimeMillis();

  @EventHandler
  public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent e) {
    Player player = e.getPlayer();
    if (e.isSneaking() || !player.isOnGround()) {
      return;
    }
    Location playerLocation = player.getLocation();
    List<Block> lastTwoTargetBlocks = player.getLastTwoTargetBlocks(null, 5);
    Block preTargetBlock = lastTwoTargetBlocks.get(0);
    Block targetBlock = lastTwoTargetBlocks.get(1);
    BlockFace face = targetBlock.getFace(preTargetBlock);
    boolean targetIsSolid = targetBlock.getType().isSolid();
    boolean targetIsVertical = face != BlockFace.DOWN && face != BlockFace.UP;
    boolean targetIsVisibleAtEyeLevel = Math.abs(playerLocation.getBlockY() - preTargetBlock.getY()) <= 2;
    if (targetIsSolid && targetIsVertical && targetIsVisibleAtEyeLevel) {
      Location preTargetBlockLocation = preTargetBlock.getLocation();
      preTargetBlock.setType(Material.WALL_SIGN);
      org.bukkit.material.Sign signMaterial = new org.bukkit.material.Sign(Material.WALL_SIGN);
      signMaterial.setFacingDirection(face);
      Sign sign = (Sign) preTargetBlock.getState();
      sign.setData(signMaterial);
      sign.setLine(1, "Clue");
      sign.update(true, false);
      player.playSound(preTargetBlockLocation, Sound.ENTITY_ARROW_HIT, SoundCategory.BLOCKS, 80, 1);
      Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
        preTargetBlock.setType(Material.AIR);
        player.playSound(preTargetBlockLocation, Sound.BLOCK_CLOTH_BREAK, SoundCategory.BLOCKS, 20, 1);
      }, 30);
    }
    else {
      lastWarningMillis = System.currentTimeMillis();
      if (System.currentTimeMillis() - lastWarningMillis > 3_000) {
        player.sendMessage(ChatColor.GOLD + "Cannot place clue here");
      }
      player.playSound(playerLocation, Sound.ENTITY_VILLAGER_NO, SoundCategory.BLOCKS, 20, 1);
    }
  }

  @EventHandler
  public void onPlayerInteractionEvent(PlayerInteractEvent e) {
//    if (isDoor(e.getMaterial()) && isRightClick(e)) {
//      e.getAction()
//    }
  }

  @EventHandler
  public void onInventoryClickEvent(InventoryClickEvent e) {
//    e.getAction()getSlotType()
  }

  private String formatPos(Location location) {
    return String.format("% 4.1f % 4.1f % 4.1f", location.getX(), location.getY(), location.getZ());
  }
}
