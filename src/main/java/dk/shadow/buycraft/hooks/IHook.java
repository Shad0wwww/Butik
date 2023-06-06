package dk.shadow.buycraft.hooks;

import dk.shadow.buycraft.enums.Hook;
import org.bukkit.plugin.java.JavaPlugin;

public interface IHook {
    String getName();

    Hook getEnum();

    boolean isEnabled();

    boolean init(JavaPlugin paramJavaPlugin);
}