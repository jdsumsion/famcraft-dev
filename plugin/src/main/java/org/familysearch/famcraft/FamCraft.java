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

  @EventHandler
  public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent e) {
    Player player = e.getPlayer();
    if (!e.isSneaking() && player.isOnGround()) {
      String playerPos = formatPos(player.getLocation());
      List<Block> lastTwoTargetBlocks = player.getLastTwoTargetBlocks(null, 5);
      Block targetBlock = lastTwoTargetBlocks.get(1);
      String blockPos = formatPos(targetBlock.getLocation());
      if (targetBlock.getType().isSolid()) {
        Block preTargetBlock = lastTwoTargetBlocks.get(0);
        preTargetBlock.setType(Material.WALL_SIGN);
        preTargetBlock.getState().setType(Material.WALL_SIGN);
        Sign sign = (Sign) preTargetBlock.getState();
        //noinspection deprecation
        sign.setRawData(faceCode(targetBlock.getFace(preTargetBlock)));
//        sign.setLine(1, "Clue");
//        sign.update();
        player.sendMessage(String.format("player pos %s block pos %s", playerPos, blockPos));
        player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT, SoundCategory.BLOCKS, 100, 1);
      }
      else {
        player.sendMessage(String.format("solid block too far away %s", blockPos));
      }
    }
  }

  private byte faceCode(BlockFace face) {
    switch (face) {
      case NORTH:
        return 0x04;
      case SOUTH:
        return 0x05;
      case EAST:
        return 0x02;
      case WEST:
        return 0x03;
      default:
        throw new IllegalStateException("unsupported face: " + face.name());
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
