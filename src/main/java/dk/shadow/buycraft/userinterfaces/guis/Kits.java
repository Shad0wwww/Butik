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

public class Kits implements SubGui {
    @Override
    public void open(Player paramPlayer) {
        //TITLE
        String title = Guis.get("kit.title")[0];
        //CREATE THE GUI
        Gui gui = Gui.gui().title(Component.text(title)).rows(5).disableAllInteractions().create();
        //GLASS
        String top_row = Guis.get("kits.toprow")[0];
        String bottom_row = Guis.get("kits.bottomrow")[0];
        GuiItem top_row_item = ItemBuilder.from(GUI.createItemGlass(Material.STAINED_GLASS_PANE, GlassColor.getGlassColor(ColorUtils.plain(top_row)), "&f")).name(Component.text(ColorUtils.getColored("&7"))).asGuiItem();
        GuiItem bottom_row_item = ItemBuilder.from(GUI.createItemGlass(Material.STAINED_GLASS_PANE, GlassColor.getGlassColor(ColorUtils.plain(bottom_row)), "&f")).name(Component.text(ColorUtils.getColored("&7"))).asGuiItem();
        GUI.fillTopRow(gui, top_row_item);
        GUI.fillBottomRow(gui, bottom_row_item);

        gui.setItem(Integer.parseInt(Guis.get("kits.tilbage.slot")[0]), GUI.tilbageCrafter(paramPlayer, "kits.tilbage.head", "kits.tilbage.lore", "kits.tilbage.name"));

        gui.open(paramPlayer);
    }
}
