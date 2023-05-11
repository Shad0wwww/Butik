package dk.shadow.buycraft.listeners;

import dk.shadow.buycraft.listeners.bukkit.Click;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Register {

    public static void registerEvents(JavaPlugin plugin) {
        Bukkit.getServer().getPluginManager().registerEvents(new Click(), plugin);
    }
}
