package dk.shadow.buycraft.userinterfaces;

import dk.shadow.buycraft.userinterfaces.guis.*;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class GuiManager {
    public static HashMap<String, SubGui> guis = new HashMap<>();

    public static void openMenu(Player player, String name) {
        if (guis.containsKey(name))
            ((SubGui)guis.get(name)).open(player);
    }


    public static void addGui(String name, SubGui gui) {
        guis.put(name, gui);
    }
    public static void initialise() {

        guis.clear();
        addGui("home", new Home());
        addGui("rank", new Ranks());
        addGui("kit", new Kits());
        addGui("key", new Keys());
        addGui("andet", new Andet());

    }
}
