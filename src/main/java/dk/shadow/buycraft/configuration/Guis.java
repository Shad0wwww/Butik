package dk.shadow.buycraft.configuration;

import dk.shadow.buycraft.Main;
import dk.shadow.buycraft.utils.ColorUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.*;

public class Guis {
    private static HashMap<String, String[]> messages;

    public static void initialise(JavaPlugin plugin) {
        File folder = new File(Main.getInstance().getDataFolder(), "guis");
        if (!folder.exists())
            folder.mkdirs();
        File file = new File(folder, "guis.yml");

        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        messages = new HashMap<>();
        for (String key : config.getConfigurationSection("").getKeys(true)) {
            if (config.isConfigurationSection(key))
                continue;
            if (config.getStringList(key) != null && config.isList(key)) {
                List<String> stringList = ColorUtils.getColored(config.getStringList(key));
                messages.put(key, stringList.toArray(new String[0]));
                continue;
            }
            if (config.getString(key) != null) {
                List<String> stringList = Collections.singletonList(ColorUtils.getColored(config.getString(key)));
                messages.put(key, stringList.toArray(new String[0]));
            }
        }
        if (messages.containsKey("prefix"))
            for (Map.Entry<String, String[]> entry : messages.entrySet()) {
                for (int i = 0; i < entry.getValue().length; i++)
                    entry.getValue()[i] = ((String[])entry.getValue())[i].replaceAll("%prefix%", ((String[])messages.get("prefix"))[0]);
            }

    }

    public static String[] get(String path) {
        if (messages.containsKey(path))
            return messages.get(path);
        System.out.println("messages.containsKey is null - 43");
        return new String[] { "" };
    }

    public static String[] get(String path, String... replacements) {
        if (messages.containsKey(path)) {
            String[] messages = get(path);
            List<String> messageList = new ArrayList<>();
            for (String message : messages) {
                for (int i = 0; i < replacements.length; i += 2)
                    message = message.replaceAll(replacements[i], replacements[i + 1]);
                messageList.add(message);
            }
            return messageList.<String>toArray(new String[0]);
        }
        return new String[] { "" };
    }


}
