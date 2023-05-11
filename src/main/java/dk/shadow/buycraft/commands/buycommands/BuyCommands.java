package dk.shadow.buycraft.commands.buycommands;

import dk.shadow.buycraft.commands.ICommand;


import dk.shadow.buycraft.commands.ISubCommand;
import dk.shadow.buycraft.commands.buycommands.subs.RabatSub;
import dk.shadow.buycraft.commands.buycommands.subs.ReloadSub;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class BuyCommands extends ICommand {

    //TODO: Removecommand, paycommand, gui with all players bal where u also can remove and add coins.
    public BuyCommands(JavaPlugin plugin, String command) {
        super(plugin, command);

        setDefaultCommand(new DefaultCommand());
        addSubCommands(
                new ReloadSub(), new RabatSub()
        );

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length <= 0 && getDefaultCommand() != null) {
            execute(sender, getDefaultCommand(), args);
        } else if (args.length > 0) {
            ISubCommand subCommand = findSubCommand(args[0]);
            if (subCommand != null) {
                execute(sender, subCommand, args);
            }
            return true;
        }
        return false;
    }
}
