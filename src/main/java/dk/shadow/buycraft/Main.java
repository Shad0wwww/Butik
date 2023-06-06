package dk.shadow.buycraft;

import dk.shadow.buycraft.commands.CommandManager;
import dk.shadow.buycraft.configuration.Config;
import dk.shadow.buycraft.configuration.Guis;
import dk.shadow.buycraft.configuration.Messages;
import dk.shadow.buycraft.enums.Hook;
import dk.shadow.buycraft.hooks.AreaShopHook;
import dk.shadow.buycraft.hooks.IHook;
import dk.shadow.buycraft.listeners.Register;
import dk.shadow.buycraft.managers.rabat.RabatManager;
import dk.shadow.buycraft.userinterfaces.GuiManager;
import dk.shadow.buycraft.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;

public final class Main extends JavaPlugin {
    private static Main instance;
    private static RabatManager rabatManager;
    public static ConsoleCommandSender log;
    private static final HashMap<Hook, Boolean> HOOKS = new HashMap<>();


    @Override
    public void onEnable() {
        log = Bukkit.getConsoleSender();
        long timestampBeforeLoad = System.currentTimeMillis();
        instance = this;

        Register.registerEvents(this);
        CommandManager.initialise(this);
        reload();

        log.sendMessage(ColorUtils.getColored(new String[] { "", "  &2Hooking into integrations" }));
        initialiseHooks();

        log.sendMessage(ColorUtils.getColored("", "  &fBuycraft has been enabled!", "    &aVersion: &f" +
                        getDescription().getVersion(), "    &aAuthors: &f" +
                        getDescription().getAuthors(), "",
                "  &2Took &a" + ( System.currentTimeMillis() - timestampBeforeLoad) + " millis &2to load!", "", "&8&m---------------------------------&r"));
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

    private void initialiseHooks() {
        IHook[] hooks = {
                new AreaShopHook()
        };
        for (IHook hook : hooks) {
            HOOKS.put(hook.getEnum(), hook.init(this));
        }
    }

    public static boolean isHookInitialised(Hook paramHook) {
        return HOOKS.getOrDefault(paramHook, Boolean.FALSE);
    }


}
