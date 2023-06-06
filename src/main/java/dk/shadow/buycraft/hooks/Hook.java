package dk.shadow.buycraft.hooks;


import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public abstract class Hook implements IHook {
    private final String name;

    private final dk.shadow.buycraft.enums.Hook hook;

    private final boolean isEnabled;

    public Hook(String paramString, dk.shadow.buycraft.enums.Hook paramHook) {
        this.name = paramString;
        this.hook = paramHook;
        if (paramHook.isBuiltIn()) {
            this.isEnabled = true;
        } else {
            this.isEnabled = (Bukkit.getPluginManager().getPlugin(getName()) != null && Bukkit.getPluginManager().getPlugin(getName()).isEnabled());
        }
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    @NotNull
    public dk.shadow.buycraft.enums.Hook getEnum() {
        return this.hook;
    }
}