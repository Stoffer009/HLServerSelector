package com.hotmail.stoffer009;

import com.hotmail.stoffer009.Listeners.OnJoin;
import com.hotmail.stoffer009.Listeners.RightClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;

public class ServerSelector extends JavaPlugin {

    public static ServerSelector instance;
    public void onEnable() {
        instance = this;
        OnJoin onJoin = new OnJoin();
        Bukkit.getPluginManager().registerEvents(onJoin, this);
        GUI gui = new GUI(this);
        Bukkit.getPluginManager().registerEvents(gui, this);
        RightClickEvent rightClickEvent = new RightClickEvent(gui);
        Bukkit.getPluginManager().registerEvents(rightClickEvent, this);
        Messenger messenger = getServer().getMessenger();
        messenger.registerOutgoingPluginChannel(this, "BungeeCord");

    }
    public void onDisable() {

    }
}

