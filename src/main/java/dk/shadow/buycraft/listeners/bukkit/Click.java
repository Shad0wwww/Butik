package dk.shadow.buycraft.listeners.bukkit;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Click implements Listener {

    @EventHandler
    public void onClick(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        System.out.println("message -" + message);
        for (BaseComponent component : TextComponent.fromLegacyText(message)) {
            if (component.getClickEvent() != null && component.getClickEvent().getAction() == ClickEvent.Action.RUN_COMMAND) {
                Player player = event.getPlayer();
                String command = component.getClickEvent().getValue();
                String clickableText = component.toPlainText();
                System.out.println("clickableText -" + clickableText);
                System.out.println("command -" + command);
                System.out.println("player -" + player.getName());
                if (command.startsWith("/msg")) {
                    player.sendMessage("You clicked on the clickable text in the chat message.");
                }
            }
        }

    }
}
