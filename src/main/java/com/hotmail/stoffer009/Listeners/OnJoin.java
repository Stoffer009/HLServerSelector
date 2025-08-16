package com.hotmail.stoffer009.Listeners;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        player.getInventory().clear();
        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta compassmeta = compass.getItemMeta();
        compassmeta.setDisplayName("§aServer Vælger");
        compass.setItemMeta(compassmeta);
        player.getInventory().setItem(4, compass);
        player.getInventory().setHeldItemSlot(4);
    }
}
