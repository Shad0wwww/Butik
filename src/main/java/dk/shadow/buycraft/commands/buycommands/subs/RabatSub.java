package dk.shadow.buycraft.commands.buycommands.subs;

import dk.shadow.buycraft.Main;
import dk.shadow.buycraft.commands.ISubCommand;
import dk.shadow.buycraft.configuration.Messages;
import dk.shadow.buycraft.utils.IntUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class RabatSub extends ISubCommand {
    public RabatSub() {
        super("rabat");
    }

    @Override
    public void onCommand(CommandSender sender, String[] args, String paramString) {
        //IF PLAYER Doesn't HAVE PERMISSION
        if (!sender.hasPermission("buycraft.rabat")) {
            Messages.send(sender, "messages.reload_no_permissions");
            return;
        }
        //CHECKS THE LENGTH
        if (args.length == 2) {
            String rabat_navn = args[0];
            //CHECKS IF IT'S A INT
            if (!IntUtil.isInteger(args[1])) {
                Messages.send(sender, "messages.ikke-et-tal");
                return;
            }
            int rabat_procent = Integer.parseInt(args[1]);

            //IF THE NUMBER IS UNDER 0
            if (!(rabat_procent > 0)) {
                Messages.send(sender, "messages.minus-rabat");
                return;
            }
            //IF THE NUMBER IS ABOVE 100
            if (!(rabat_procent < 101)) {
                Messages.send(sender, "messages.over-hundred-rabat");
                return;
            }
            //CHECKS IF THE NAME MATCHES THE LIST
            if (!Arrays.asList("ranks", "kits", "andet", "keys").contains(rabat_navn.toLowerCase())) {
                Messages.send(sender, "messages.mather-ikke");
                return;
            }
            //SENDS THE MESSAGE
            Messages.send(sender, "messages.du-satte", "%rabat_navn%", rabat_navn.toLowerCase(), "%rabat_procent%", String.valueOf(rabat_procent));
            //FUNCTION THAT CHANGES THE DISCOUNT
            Main.getgetRabatManager().setRabat(rabat_navn, rabat_procent);

        } else {
            Messages.send(sender,"messages.correct-rabat");
        }



    }
}
