package dk.shadow.buycraft.commands.buycommands;



import dk.shadow.buycraft.commands.ISubCommand;

import dk.shadow.buycraft.configuration.Messages;
import dk.shadow.buycraft.userinterfaces.GuiManager;
import dk.shadow.buycraft.utils.ColorUtils;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DefaultCommand extends ISubCommand {

    public DefaultCommand() {
        super("default");
    }

    @Override
    public void onCommand(CommandSender sender, String[] args, String label) {
        if (!(sender instanceof Player)) {
            Messages.send(sender,"messages.only_player_command");
        } else {
            Player player = (Player) sender;
            player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 5);
            GuiManager.openMenu(player, "home");
        }



    }
}
