package dk.shadow.buycraft.managers.rabat;

import dk.shadow.buycraft.Main;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import java.util.HashMap;

import java.util.Map;

public class RabatManager {
    private static HashMap<String,Rabat> rabat = new HashMap<>();

    public int getRabat(String key) {
        Rabat value = rabat.get(key);
        if (value != null) {
            return value.getProcent();
        }
        return 0;
    }

    public void setRabat(String key, int value) {
        Rabat rabatObject = new Rabat(value);
        rabatObject.setProcent(value);
        rabat.put(key, rabatObject);
    }

    public int calculateProcent(String key, int init) {
        Rabat rabatObject = rabat.get(key);
        if (rabatObject != null) {
            int procent = rabatObject.getProcent();
            double result = (init * procent) / 100.0;
            return (int) Math.ceil(init-result);
        } else {
            return 0;
        }
    }



    public void saveRabat() {
        File file = new File(Main.getInstance().getDataFolder(), "config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        ConfigurationSection rabatSection = config.getConfigurationSection("rabat");
        if (rabatSection != null) {
            rabatSection.getKeys(false).forEach(key -> config.set("rabat." + key, null));
        }

        for (Map.Entry<String, Rabat> entry : rabat.entrySet()) {
            String key = entry.getKey();
            Rabat value = entry.getValue();

            config.set("rabat." + key, value.getProcent());
        }

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadRabat() {
        File file = new File(Main.getInstance().getDataFolder(), "config.yml");
        System.out.println("file -" + file);
        System.out.println("Main.getInstance().getDataFolder() -" + Main.getInstance().getDataFolder());

        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        System.out.println("config -" + config);
        ConfigurationSection rabatSection = config.getConfigurationSection("rabat");
        if (!config.contains("rabat")) {
            System.out.println("rabat section not found in config.yml");
            return;
        }
        if (rabatSection != null) {
            for (String key : rabatSection.getKeys(false)) {
                System.out.println("key -" + key);
                int value = rabatSection.getInt(key);
                Rabat rabatObject = new Rabat(value);
                System.out.println("rabatObject -" + rabatObject);
                System.out.println("rabatObject -" + rabatObject.getProcent());
                rabat.put(key, rabatObject);
            }
        }

    }


}
