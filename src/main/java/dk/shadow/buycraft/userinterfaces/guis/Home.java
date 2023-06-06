package dk.shadow.buycraft.userinterfaces.guis;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dk.shadow.buycraft.configuration.Guis;
import dk.shadow.buycraft.configuration.Messages;
import dk.shadow.buycraft.userinterfaces.SubGui;
import dk.shadow.buycraft.utils.ColorUtils;
import dk.shadow.buycraft.utils.GUI;
import dk.shadow.buycraft.utils.GlassColor;

import dk.shadow.coins.database.SQLITEConnector;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
public class Home implements SubGui {

    @Override
    public void open(Player paramPlayer) {

        //TITLE
        String title = Guis.get("home.title")[0];
        //CREATE THE GUI
        Gui gui = Gui.gui().title(Component.text(title)).rows(5).disableAllInteractions().create();
        //GLASS
        String top_row = Guis.get("home.toprow")[0];
        String bottom_row = Guis.get("home.bottomrow")[0];
        GuiItem top_row_item = ItemBuilder.from(GUI.createItemGlass(Material.STAINED_GLASS_PANE, GlassColor.getGlassColor(ColorUtils.plain(top_row)), "&f")).name(Component.text(ColorUtils.getColored("&7"))).asGuiItem();
        GuiItem bottom_row_item = ItemBuilder.from(GUI.createItemGlass(Material.STAINED_GLASS_PANE, GlassColor.getGlassColor(ColorUtils.plain(bottom_row)), "&f")).name(Component.text(ColorUtils.getColored("&7"))).asGuiItem();
        GUI.fillTopRow(gui, top_row_item);
        GUI.fillBottomRow(gui, bottom_row_item);

        //TOS
        ItemStack tos = GUI.getSkull(Guis.get("home.tos.head")[0]);
        String[] tos_lore = Guis.get("home.tos.lore");
        GuiItem tos_head = ItemBuilder.from(tos).name(Component.text(ColorUtils.getColored(Guis.get("home.tos.name")[0]))).setLore(ColorUtils.getColored(tos_lore)).asGuiItem();
        gui.setItem(Integer.parseInt(Guis.get("home.tos.slot")[0]), tos_head);

        //COINS
        ItemStack coin_head_link = GUI.getSkull(Guis.get("home.coin.head")[0]);
        //String.valueOf(SQLITEConnector.getAccountManager().getBalance(paramPlayer.getUniqueId()) != null ? SQLITEConnector.getAccountManager().getBalance(paramPlayer.getUniqueId()) : 0))
        String[] coin_lore = Guis.get("home.coin.lore", "%coins-amount%", String.valueOf(SQLITEConnector.getAccountManager().getBalance(paramPlayer.getUniqueId()) != null ? SQLITEConnector.getAccountManager().getBalance(paramPlayer.getUniqueId()).getAmount() : 0));
        GuiItem coin_head = ItemBuilder.from(coin_head_link).name(Component.text(ColorUtils.getColored(Guis.get("home.coin.name")[0]))).setLore(ColorUtils.getColored(coin_lore)).asGuiItem(event -> {
            Messages.send(paramPlayer, "messages.spiller_buy_coins");
            Messages.sendToStaffs(paramPlayer, "messages.admin_buy_coins", "%player%", paramPlayer.getDisplayName());
            paramPlayer.playSound(paramPlayer.getLocation(), Sound.NOTE_PLING, 1, 5);
            paramPlayer.closeInventory();
        });
        gui.setItem(Integer.parseInt(Guis.get("home.coin.slot")[0]), coin_head);

        //RANKS
        gui.setItem(Integer.parseInt(Guis.get("home.rank.slot")[0]), GUI.guiCrafter(paramPlayer, "home.rank.head", "home.rank.lore", "home.rank.name","ranks"));

        //KIT
        gui.setItem(Integer.parseInt(Guis.get("home.kit.slot")[0]), GUI.guiCrafter(paramPlayer, "home.kit.head", "home.kit.lore", "home.kit.name","kits"));

        //KEYS
        gui.setItem(Integer.parseInt(Guis.get("home.key.slot")[0]), GUI.guiCrafter(paramPlayer, "home.key.head", "home.key.lore", "home.key.name","keys"));

        //ANDET
        gui.setItem(Integer.parseInt(Guis.get("home.andet.slot")[0]), GUI.guiCrafter(paramPlayer, "home.andet.head", "home.andet.lore", "home.andet.name","andet"));



        //OPENS THE GUI FOR THE PLAYER
        gui.open(paramPlayer);

    }
}
