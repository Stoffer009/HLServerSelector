package com.hotmail.stoffer009;

import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class GUI implements Listener {
    private final HeadDatabaseAPI api = new HeadDatabaseAPI();
    private final JavaPlugin plugin;
    public GUI(JavaPlugin plugin){
        this.plugin = plugin;
    }
    public void create(Player player){
        int slots = 45;
        if(player.hasPermission("staff")) {
            slots = 54;
        }
        Inventory inventory = Bukkit.createInventory(null, slots, "§aServerVælger");
        ItemStack prison = new ItemStack(Material.IRON_DOOR);
        ItemMeta prisonmeta = prison.getItemMeta();
        ItemStack testserver = api.getItemHead("26414");
        ItemMeta testservermeta = testserver.getItemMeta();
        ItemStack barrier = new ItemStack(Material.BARRIER);
        ItemMeta barriermeta = barrier.getItemMeta();
        ItemStack glas = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        barriermeta.setDisplayName(" ");
        barrier.setItemMeta(barriermeta);
        prisonmeta.setDisplayName("§aPrison");
        List<String> lore = new ArrayList<>();
        lore.add("§7Klik her for at joine §fPrison.");
        prisonmeta.setLore(lore);
        prison.setItemMeta(prisonmeta);
        inventory.setItem(22, prison);
        for(int x = 0; x <= 9; x++ ) {
            inventory.setItem(x,glas );
        }
        for(int x = 35; x <= 44; x++) {
            inventory.setItem(x,glas);
        }
        if(player.hasPermission("staff")) {
            inventory.setItem(45, glas);
            inventory.setItem(46, barrier);
            inventory.setItem(47, barrier);
            testservermeta.setDisplayName("§cTestServer1");
            testserver.setItemMeta(testservermeta);
            inventory.setItem(48, testserver);
            inventory.setItem(49, barrier);
            testservermeta.setDisplayName("§cTestServer2");
            testserver.setItemMeta(testservermeta);
            inventory.setItem(50, testserver);
            inventory.setItem(51, barrier);
            inventory.setItem(52, barrier);
            inventory.setItem(53, glas);
        }
        inventory.setItem(17, glas);
        inventory.setItem(18, glas);
        inventory.setItem(26, glas);
        inventory.setItem(27, glas);
        player.openInventory(inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        if (event.getClickedInventory() == null) return;
        if (event.getCurrentItem() == null) return;

        Player player = (Player) event.getWhoClicked();
        if(!player.getGameMode().equals(GameMode.CREATIVE)) {
            event.setCancelled(true);
        }
        if (event.getView().getTitle().equals("§aServerVælger")) {
            event.setCancelled(true);
            event.setResult(Event.Result.DENY);

            int clickedslot = event.getRawSlot();

            switch (clickedslot) {
                case 22:
                    player.sendMessage("§7Du bliver sendt til §aPrison");
                    sendToServer(player, "prison");
                    break;
                case 48:
                    player.sendMessage("§7Du bliver sendt til §cTest1");
                    sendToServer(player, "test1");
                    break;
                case 50:
                    player.sendMessage("§7Du bliver sendt til §cTest2");
                    sendToServer(player, "test2");
                    break;
            }
        }
    }

    private void sendToServer(Player player, String serverName) {
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(byteArray);

            try {
                out.writeUTF("Connect");
                out.writeUTF(serverName);
            } catch (IOException e) {
                e.printStackTrace();
            }

            player.sendPluginMessage(plugin, "BungeeCord", byteArray.toByteArray());
        }
}
