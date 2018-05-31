package org.familysearch.famcraft;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.material.Door;

/**
 * Allows game modes to be implemented without having all the code mixed together in the root plugin.
 *
 * @author John Sumsion
 */
public interface FamCraftGameMode {

  // LOCATION: allows clue locations to be identified (double-crouch for picture walls)
  // HUNT: allows hidden clues to be uncovered
  default void onBlockCrouch(Player player, Block block) {
  }

  // ANCESTOR: allows pictures, stories, events to be identified when setting up parameters for a given ancestor
  default void onBlockBreak(Player player, Block block) {
  }

  // ANCESTOR: allows pictures, stories, events to be identified when setting up parameters for a given ancestor
  default void onPickupItemSelection(Player player, Material material) {
  }

  // LOCATION: allows boundary door locations to be identified (for notifying about clues inside)
  default void onDoorCrouch(Player player, Block block, Door door) {
  }

  // HUNT: allows clue counts to be displayed that are guarded by this door
  default void onDoorOpen(Player player, Block block, Door door) {
  }

  // LOCATION: allows clue locations to be tied to a door (those clues that were selected between the door crouch and the close)
  default void onDoorClose(Player player, Block block, Door door) {
  }

  // HUNT: allows for sounds to be played if there is a clue inside a chest
  default void onChestOpen(Player player, Chest chest) {
  }

  // HUNT: allows fact/story clues to be presented to the user as special records are discovered in chests
  default void onInventoryItemSelection(Player player, Material material) {
  }

  // HUNT: allows fact/story clues to be presented to the user as special records are discovered in chests
  default void onDiscPlay(Player player, Material material) {
  }
}
