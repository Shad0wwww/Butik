package dk.shadow.buycraft.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dk.shadow.buycraft.Main;
import dk.shadow.buycraft.configuration.Guis;
import dk.shadow.buycraft.userinterfaces.GuiManager;
import net.kyori.adventure.text.Component;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class GUI {
    public static ItemStack createItemStack(final ItemStack item, final String name, final String... lore) {
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ColorUtils.getColored(name));
        List<String> coloredLore = new ArrayList<>();
        for (String line : lore) {
            coloredLore.add(ColorUtils.getColored(line));
        }

        meta.setLore(coloredLore);

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack createItemGlass(Material material, int GlassColor, String displayName, String... loreString) {
        List<String> lore = new ArrayList<>();
        ItemStack item = new ItemStack(material, 1, (short) GlassColor);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtils.getColored(displayName));

        for (String s : loreString)
            lore.add(ColorUtils.getColored(s));

        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getSkull(String url) {
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        if(url.isEmpty())return head;


        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }
        head.setItemMeta(headMeta);
        return head;
    }


    public static ItemStack getPlayerSkull(String name) {
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setOwner(name);
        head.setItemMeta(meta);
        return head;
    }

    public static void fillTopRow(Gui inv, GuiItem glass) {
        int[] topRowSlots = {0, 1, 2, 6, 7, 8, 9, 17};
        for (int slot : topRowSlots) {
            inv.setItem(slot, glass);
        }
    }
    public static void fillBottomRow(Gui inv, GuiItem glass) {
        int[] bottomRowSlots = {27, 35, 36, 37, 38, 42, 43, 44};
        for (int slot : bottomRowSlots) {
            inv.setItem(slot, glass);
        }
    }

    public static GuiItem guiCrafter(Player player, String head_path, String lore_path, String name_path, String rabat_repalcement) {
        ItemStack kit = GUI.getSkull(Guis.get(head_path)[0]);
        String[] kit_lore = Guis.get(lore_path, "%rabat-procent%", String.valueOf(Main.getgetRabatManager().getRabat(rabat_repalcement)));
        return ItemBuilder.from(kit).name(Component.text(ColorUtils.getColored(Guis.get(name_path)[0]))).setLore(ColorUtils.getColored(kit_lore)).asGuiItem(event -> {
            player.playSound(player.getLocation(), Sound.CLICK, 1,5);
            String[] components = head_path.split("\\."); // Split the string at each dot
            String name = components[components.length - 2]; // Get the last component
            GuiManager.openMenu(player, name);

        });
    }

    public static GuiItem tilbageCrafter(Player player, String head_path, String lore_path, String name_path) {
        ItemStack tilbage = GUI.getSkull(Guis.get(head_path)[0]);
        String[] tilbage_lore = Guis.get(lore_path);
        return ItemBuilder.from(tilbage).name(Component.text(ColorUtils.getColored(Guis.get(name_path)[0]))).setLore(ColorUtils.getColored(tilbage_lore)).asGuiItem(event -> {
            player.playSound(player.getLocation(), Sound.CLICK, 1,5);
            GuiManager.openMenu(player, "home");

        });
    }

}
