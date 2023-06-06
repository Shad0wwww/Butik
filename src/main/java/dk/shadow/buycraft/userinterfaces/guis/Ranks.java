package dk.shadow.buycraft.userinterfaces.guis;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dk.shadow.buycraft.configuration.Guis;
import dk.shadow.buycraft.userinterfaces.SubGui;
import dk.shadow.buycraft.utils.ColorUtils;
import dk.shadow.buycraft.utils.GUI;
import dk.shadow.buycraft.utils.GlassColor;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Ranks implements SubGui {
    @Override
    public void open(Player paramPlayer) {
        //TITLE
        String title = Guis.get("ranks.title")[0];
        //CREATE THE GUI
        Gui gui = Gui.gui().title(Component.text(title)).rows(5).disableAllInteractions().create();
        //GLASS
        String top_row = Guis.get("ranks.toprow")[0];
        String bottom_row = Guis.get("ranks.bottomrow")[0];
        GuiItem top_row_item = ItemBuilder.from(GUI.createItemGlass(Material.STAINED_GLASS_PANE, GlassColor.getGlassColor(ColorUtils.plain(top_row)), "&f")).name(Component.text(ColorUtils.getColored("&7"))).asGuiItem();
        GuiItem bottom_row_item = ItemBuilder.from(GUI.createItemGlass(Material.STAINED_GLASS_PANE, GlassColor.getGlassColor(ColorUtils.plain(bottom_row)), "&f")).name(Component.text(ColorUtils.getColored("&7"))).asGuiItem();
        GUI.fillTopRow(gui, top_row_item);
        GUI.fillBottomRow(gui, bottom_row_item);

        gui.setItem(Integer.parseInt(Guis.get("ranks.tilbage.slot")[0]), GUI.tilbageCrafter(paramPlayer, "ranks.tilbage.head", "ranks.tilbage.lore", "ranks.tilbage.name"));
        gui.setItem(Integer.parseInt(Guis.get("ranks.rank1.slot")[0]), GUI.itemsCrafter(paramPlayer, "ranks.rank1.head", "ranks.rank1.name", "ranks.rank1.lore", Integer.valueOf(Guis.get("ranks.rank1.pris")[0])));

        gui.open(paramPlayer);
    }
}
