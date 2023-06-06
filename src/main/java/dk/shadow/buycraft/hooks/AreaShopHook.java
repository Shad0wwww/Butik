package dk.shadow.buycraft.hooks;

import dk.shadow.buycraft.Main;

import dk.shadow.buycraft.utils.ColorUtils;
import me.wiefferink.areashop.AreaShop;
import me.wiefferink.areashop.managers.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class AreaShopHook extends Hook {

    private static AreaShop Instance = null;
    private static FileManager manager;
    public AreaShopHook() {
        super("Areashop", dk.shadow.buycraft.enums.Hook.AREASHOP);
    }

    public boolean init(JavaPlugin paramJavaPlugin) {
        if(!isEnabled()) return false;
        Instance = (AreaShop) Bukkit.getPluginManager().getPlugin("Areashop");
        Main.log.sendMessage(ColorUtils.getColored("&aAreaShop HAS BEEN HOOKED"));
        manager = getInstance().getFileManager();

        System.out.println("manager.getRents(); " + manager.getRents());
        return true;
    }


    public void makePlayerRent(Player player, String cellname) {
        manager.getRent(cellname).rent(player);


    }




    public static AreaShop getInstance() {
        return Instance;
    }


}
