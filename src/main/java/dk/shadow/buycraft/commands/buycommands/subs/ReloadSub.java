package dk.shadow.buycraft.commands.buycommands.subs;

import dk.shadow.buycraft.Main;
import dk.shadow.buycraft.commands.ISubCommand;
import dk.shadow.buycraft.configuration.Messages;
import org.bukkit.command.CommandSender;

public class ReloadSub extends ISubCommand {
    public ReloadSub() {
        super("reload");
    }

    @Override
    public void onCommand(CommandSender sender, String[] args, String paramString) {
        //Checks, if player have permission.
        if (!sender.hasPermission("buycraft.reload")) {
            Messages.send(sender, "messages.reload_no_permissions");
            return;
        }

        Messages.send(sender, "messages.reload_starting");
        try {
            Main.getInstance().reload();
            Messages.send(sender, "messages.reload_succes");
        } catch (Exception e) {
            Messages.send(sender, "messages.reload_error");
            e.printStackTrace();
        }

    }
}
