package me.nicka.advancedreactions;

import lombok.Getter;
import me.nicka.advancedreactions.commands.*;
import me.nicka.advancedreactions.files.IgnoreList;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdvancedReactions extends JavaPlugin {

    @Getter
    private static AdvancedReactions plugin;

    @Getter
    private final String prefix = "&8[&6AR&8]";

    @Override
    public void onEnable() {
        plugin=this;
        getConfig().options().copyDefaults();
        this.saveDefaultConfig();

        IgnoreList.setup();
        IgnoreList.get().options().copyDefaults();
        IgnoreList.save();

        double version = 1.1;

        if(getConfig().getInt("ConfigVersion") != version){
            Bukkit.getLogger().warning("[Advanced Reactions] OUT-OF-DATE CONFIG. DELETE OLD ONE AND RESTART SERVER");
        }

        Bukkit.getLogger().info("------------AdvancedReactions HAS BEEN ENABLED!!!------------");

        new Kiss();
        new Slap();
        new Pinch();
        new Punch();
        new Slap();
        new Stare();
        new Hug();
        new Lick();
        new Stab();
        new Highfive();
        new Pet();

        new ARIgnore();
        new ARList();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("------------AdvancedReactions HAS BEEN DISABLED!!!------------");
    }

}
