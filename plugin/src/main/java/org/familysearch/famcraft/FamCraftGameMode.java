package org.familysearch.famcraft;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.material.Door;

/**
 * Responsible for allowing player to switch between game modes.
 *
 * @author John Sumsion
 */
public interface FamCraftGameMode {

  default void onItemCrouch(Player player, Block block) {
  }

  default void onDoorCrouch(Player player, Block block, Door door) {
  }

  default void onChestCrouch(Player player, Block block, Chest chest) {
  }

  default void onDoorOpen(Player player, Block block, Door door) {
  }

  default void onInventoryItemPlace(Player player, Material material) {
  }
}
