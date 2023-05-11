package dk.shadow.buycraft.commands;




import dk.shadow.buycraft.commands.buycommands.BuyCommands;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandManager {
    public static void initialise(JavaPlugin instance) {
        new BuyCommands(instance, "buy");

    }
}