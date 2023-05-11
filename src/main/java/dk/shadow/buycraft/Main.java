package dk.shadow.buycraft;

import dk.shadow.buycraft.commands.CommandManager;
import dk.shadow.buycraft.configuration.Config;
import dk.shadow.buycraft.configuration.Guis;
import dk.shadow.buycraft.configuration.Messages;
import dk.shadow.buycraft.listeners.Register;
import dk.shadow.buycraft.managers.rabat.RabatManager;
import dk.shadow.buycraft.userinterfaces.GuiManager;
import dk.shadow.coins.Coins;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin {
    private static Main instance;
    private static RabatManager rabatManager;


    @Override
    public void onEnable() {
        instance = this;

        Register.registerEvents(this);
        CommandManager.initialise(this);
        reload();

    }

     @Override
     public void onDisable() {
        rabatManager.saveRabat();
     }


    public void reload() {
        initialiseConfigs();
        GuiManager.initialise();
        rabatManager = new RabatManager();
        rabatManager.loadRabat();


    }

    private void initialiseConfigs() {
        saveDefaultConfig();
        File messages = new File(getDataFolder(), "messages.yml");
        File config = new File(getDataFolder(), "config.yml");

        File folder = new File(getDataFolder(), "guis");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        if (!(new File(getDataFolder(), "guis/guis.yml")).exists())
            saveResource("guis/guis.yml", false);

        if (!config.exists()) {
            saveResource("config.yml", false);
        }
        if (!messages.exists()) {
            saveResource("messages.yml", false);
        }

        Messages.initialise(this);
        Config.initialise(this);
        Guis.initialise(this);

    }


    public static Main getInstance() {
        return instance;
    }
    public static RabatManager getgetRabatManager() {
        return rabatManager;
    }


}
